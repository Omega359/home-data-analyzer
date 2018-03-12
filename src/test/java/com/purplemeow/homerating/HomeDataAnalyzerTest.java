package com.purplemeow.homerating;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class HomeDataAnalyzerTest {

    @Test
    public void testAddAndAnalyze() throws Exception {
        HomeDataAnalyzer analyzer = new HomeDataAnalyzer();


        List<String> lines = IOUtils.readLines(this.getClass().getResourceAsStream("/input.txt"), StandardCharsets.UTF_8);

        for (String line : lines) {
            if ("".equals(line.trim())) {
                break;
            }
            
            List<String> tokens = LineParser.parse(line);
            analyzer.add(new HomeData(tokens.get(0), tokens.get(1), tokens.get(2)));
        }

        analyzer.analyze();

        assertEquals("\"John Doe1\" \"Canada/Ontario/Toronto\" 1", analyzer.query("John Doe1", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe2\" \"Canada/Ontario/Toronto\" 1", analyzer.query("John Doe2", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe3\" \"Canada/Ontario/Toronto\" 2", analyzer.query("John Doe3", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe4\" \"Canada/Ontario/Toronto\" 2", analyzer.query("John Doe4", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe5\" \"Canada/Ontario/Toronto\" 3", analyzer.query("John Doe5", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe6\" \"Canada/Ontario/Toronto\" 3", analyzer.query("John Doe6", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe7\" \"Canada/Ontario/Toronto\" 4", analyzer.query("John Doe7", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe8\" \"Canada/Ontario/Toronto\" 4", analyzer.query("John Doe8", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe9\" \"Canada/Ontario/Toronto\" 5", analyzer.query("John Doe9", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe10\" \"Canada/Ontario/Toronto\" 5", analyzer.query("John Doe10", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe11\" \"Canada/Ontario/Toronto\" 6", analyzer.query("John Doe11", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe12\" \"Canada/Ontario/Toronto\" 6", analyzer.query("John Doe12", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe13\" \"Canada/Ontario/Toronto\" 7", analyzer.query("John Doe13", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe14\" \"Canada/Ontario/Toronto\" 7", analyzer.query("John Doe14", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe15\" \"Canada/Ontario/Toronto\" 8", analyzer.query("John Doe15", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe16\" \"Canada/Ontario/Toronto\" 8", analyzer.query("John Doe16", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe17\" \"Canada/Ontario/Toronto\" 9", analyzer.query("John Doe17", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe18\" \"Canada/Ontario/Toronto\" 9", analyzer.query("John Doe18", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe19\" \"Canada/Ontario/Toronto\" 10", analyzer.query("John Doe19", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe20\" \"Canada/Ontario/Toronto\" 10", analyzer.query("John Doe20", "Canada/Ontario/Toronto"));
        assertEquals("\"John Doe2\" \"Canada/Ontario/Ottawa\" 1", analyzer.query("John Doe2", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe22\" \"Canada/Ontario/Ottawa\" 1", analyzer.query("John Doe22", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe23\" \"Canada/Ontario/Ottawa\" 2", analyzer.query("John Doe23", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe24\" \"Canada/Ontario/Ottawa\" 2", analyzer.query("John Doe24", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe25\" \"Canada/Ontario/Ottawa\" 3", analyzer.query("John Doe25", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe26\" \"Canada/Ontario/Ottawa\" 3", analyzer.query("John Doe26", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe27\" \"Canada/Ontario/Ottawa\" 4", analyzer.query("John Doe27", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe28\" \"Canada/Ontario/Ottawa\" 4", analyzer.query("John Doe28", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe29\" \"Canada/Ontario/Ottawa\" 5", analyzer.query("John Doe29", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe30\" \"Canada/Ontario/Ottawa\" 5", analyzer.query("John Doe30", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe31\" \"Canada/Ontario/Ottawa\" 6", analyzer.query("John Doe31", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe32\" \"Canada/Ontario/Ottawa\" 6", analyzer.query("John Doe32", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe33\" \"Canada/Ontario/Ottawa\" 7", analyzer.query("John Doe33", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe34\" \"Canada/Ontario/Ottawa\" 7", analyzer.query("John Doe34", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe35\" \"Canada/Ontario/Ottawa\" 8", analyzer.query("John Doe35", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe36\" \"Canada/Ontario/Ottawa\" 8", analyzer.query("John Doe36", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe37\" \"Canada/Ontario/Ottawa\" 9", analyzer.query("John Doe37", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe38\" \"Canada/Ontario/Ottawa\" 9", analyzer.query("John Doe38", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe39\" \"Canada/Ontario/Ottawa\" 10", analyzer.query("John Doe39", "Canada/Ontario/Ottawa"));
        assertEquals("\"John Doe40\" \"Canada/Ontario/Ottawa\" 10", analyzer.query("John Doe40", "Canada/Ontario/Ottawa"));

        assertEquals("\"John Doe1\" \"Canada/Ontario\" 1", analyzer.query("John Doe1", "Canada/Ontario"));
        assertEquals("\"John Doe2\" \"Canada/Ontario\" 1", analyzer.query("John Doe2", "Canada/Ontario"));
        assertEquals("\"John Doe3\" \"Canada/Ontario\" 1", analyzer.query("John Doe3", "Canada/Ontario"));
        assertEquals("\"John Doe4\" \"Canada/Ontario\" 1", analyzer.query("John Doe4", "Canada/Ontario"));
        assertEquals("\"John Doe5\" \"Canada/Ontario\" 2", analyzer.query("John Doe5", "Canada/Ontario"));
        assertEquals("\"John Doe6\" \"Canada/Ontario\" 2", analyzer.query("John Doe6", "Canada/Ontario"));
        assertEquals("\"John Doe7\" \"Canada/Ontario\" 2", analyzer.query("John Doe7", "Canada/Ontario"));
        assertEquals("\"John Doe8\" \"Canada/Ontario\" 2", analyzer.query("John Doe8", "Canada/Ontario"));
        assertEquals("\"John Doe9\" \"Canada/Ontario\" 3", analyzer.query("John Doe9", "Canada/Ontario"));
        assertEquals("\"John Doe10\" \"Canada/Ontario\" 3", analyzer.query("John Doe10", "Canada/Ontario"));
        assertEquals("\"John Doe11\" \"Canada/Ontario\" 3", analyzer.query("John Doe11", "Canada/Ontario"));
        assertEquals("\"John Doe12\" \"Canada/Ontario\" 3", analyzer.query("John Doe12", "Canada/Ontario"));
        assertEquals("\"John Doe13\" \"Canada/Ontario\" 4", analyzer.query("John Doe13", "Canada/Ontario"));
        assertEquals("\"John Doe14\" \"Canada/Ontario\" 4", analyzer.query("John Doe14", "Canada/Ontario"));
        assertEquals("\"John Doe15\" \"Canada/Ontario\" 4", analyzer.query("John Doe15", "Canada/Ontario"));
        assertEquals("\"John Doe16\" \"Canada/Ontario\" 4", analyzer.query("John Doe16", "Canada/Ontario"));
        assertEquals("\"John Doe17\" \"Canada/Ontario\" 5", analyzer.query("John Doe17", "Canada/Ontario"));
        assertEquals("\"John Doe18\" \"Canada/Ontario\" 5", analyzer.query("John Doe18", "Canada/Ontario"));
        assertEquals("\"John Doe19\" \"Canada/Ontario\" 5", analyzer.query("John Doe19", "Canada/Ontario"));
        assertEquals("\"John Doe20\" \"Canada/Ontario\" 5", analyzer.query("John Doe20", "Canada/Ontario"));
        // the following has dups, picks first one
        assertEquals("\"John Doe2\" \"Canada/Ontario\" 1", analyzer.query("John Doe2", "Canada/Ontario"));
        assertEquals("\"John Doe22\" \"Canada/Ontario\" 6", analyzer.query("John Doe22", "Canada/Ontario"));
        assertEquals("\"John Doe23\" \"Canada/Ontario\" 6", analyzer.query("John Doe23", "Canada/Ontario"));
        assertEquals("\"John Doe24\" \"Canada/Ontario\" 6", analyzer.query("John Doe24", "Canada/Ontario"));
        assertEquals("\"John Doe25\" \"Canada/Ontario\" 7", analyzer.query("John Doe25", "Canada/Ontario"));
        assertEquals("\"John Doe26\" \"Canada/Ontario\" 7", analyzer.query("John Doe26", "Canada/Ontario"));
        assertEquals("\"John Doe27\" \"Canada/Ontario\" 7", analyzer.query("John Doe27", "Canada/Ontario"));
        assertEquals("\"John Doe28\" \"Canada/Ontario\" 7", analyzer.query("John Doe28", "Canada/Ontario"));
        assertEquals("\"John Doe29\" \"Canada/Ontario\" 8", analyzer.query("John Doe29", "Canada/Ontario"));
        assertEquals("\"John Doe30\" \"Canada/Ontario\" 8", analyzer.query("John Doe30", "Canada/Ontario"));
        assertEquals("\"John Doe31\" \"Canada/Ontario\" 8", analyzer.query("John Doe31", "Canada/Ontario"));
        assertEquals("\"John Doe32\" \"Canada/Ontario\" 8", analyzer.query("John Doe32", "Canada/Ontario"));
        assertEquals("\"John Doe33\" \"Canada/Ontario\" 9", analyzer.query("John Doe33", "Canada/Ontario"));
        assertEquals("\"John Doe34\" \"Canada/Ontario\" 9", analyzer.query("John Doe34", "Canada/Ontario"));
        assertEquals("\"John Doe35\" \"Canada/Ontario\" 9", analyzer.query("John Doe35", "Canada/Ontario"));
        assertEquals("\"John Doe36\" \"Canada/Ontario\" 9", analyzer.query("John Doe36", "Canada/Ontario"));
        assertEquals("\"John Doe37\" \"Canada/Ontario\" 10", analyzer.query("John Doe37", "Canada/Ontario"));
        assertEquals("\"John Doe38\" \"Canada/Ontario\" 10", analyzer.query("John Doe38", "Canada/Ontario"));
        assertEquals("\"John Doe39\" \"Canada/Ontario\" 10", analyzer.query("John Doe39", "Canada/Ontario"));
        assertEquals("\"John Doe40\" \"Canada/Ontario\" 10", analyzer.query("John Doe40", "Canada/Ontario"));
    }
}
