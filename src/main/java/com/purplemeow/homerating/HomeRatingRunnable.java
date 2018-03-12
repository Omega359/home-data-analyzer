package com.purplemeow.homerating;

import com.google.common.base.Preconditions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class HomeRatingRunnable implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(HomeRatingRunnable.class);

    private static final long MAX_BYTES_TO_CONSUME = FileUtils.ONE_MB * 50;

    private final InputStream in;
    private final Writer out;

    private HomeDataAnalyzer dataAnalyzer = new HomeDataAnalyzer();

    HomeRatingRunnable(InputStream in, PrintStream out) {
        Preconditions.checkArgument(in != null, "InputStream cannot be null");
        Preconditions.checkArgument(out != null, "OutputStream cannot be null");

        this.in = in;
        this.out = new OutputStreamWriter(out, StandardCharsets.UTF_8);
    }

    public void run() {
        try {
            doRun();
        }
        catch (Exception e) {
            LOG.error("log-200: {}", e.getMessage(), e);
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }

    private void doRun() throws Exception {
        // don't allow things to go crazy. Users should chunk input to this tool.
        BoundedInputStream bis = new BoundedInputStream(in, MAX_BYTES_TO_CONSUME);

        // charset possibly could be something else but unless we have a
        // way to determine it via some mechanism this is a fairly safe value
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
        try (BufferedReader br = new BufferedReader(isr)) {
            boolean inQuery = false;
            String line;

            while ((line = br.readLine()) != null) {
                if ("".equals(line.trim())) {
                    // we've hit the termination of the data, next should be the queries
                    inQuery = true;
                    dataAnalyzer.analyze();
                }
                else if (!inQuery) {
                    List<String> tokens = LineParser.parse(line);
                    if (tokens.size() != 3) {
                        LOG.error("log-201: Unable to consume data line, skipping: '{}'", line);
                        continue;
                    }

                    HomeData homeData = new HomeData(tokens.get(0), tokens.get(1), tokens.get(2));
                    dataAnalyzer.add(homeData);
                }
                else {
                    List<String> tokens = LineParser.parse(line);
                    if (tokens.size() != 2) {
                        LOG.error("log-202: Unable to consume query line, skipping: '{}'", line);
                        continue;
                    }

                    String output = dataAnalyzer.query(tokens.get(0), tokens.get(1));
                    if (output != null) {
                        out.write(output);
                        out.write(System.lineSeparator());
                    }
                    else {
                        LOG.error("log-203: query data was not matched in source data, skipping: '{}'", line);
                    }
                }
            }
        }
    }
}
