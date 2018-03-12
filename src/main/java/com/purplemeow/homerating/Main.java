package com.purplemeow.homerating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // args are ignored for now. In typical tooling we would likely have argus to parse - use args4j

        LOG.info("log-100: Home insulation rating tool starting...");

        // Grab stdin, stdout and pass those to the rating code
        HomeRatingRunnable homeRating = new HomeRatingRunnable(System.in, System.out);

        try {
            homeRating.run();
        }
        catch (Exception e) {
            LOG.error("log-666: {}", e.getMessage(), e);
            System.exit(1);
        }

        LOG.info("log-100: Home insulation rating tool finished");
        System.exit(0);
    }
    
}
