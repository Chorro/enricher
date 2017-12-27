package io.wizzie.ks.enricher;

import io.wizzie.bootstrapper.builder.Config;
import io.wizzie.ks.enricher.builder.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Enricher {
    private static final Logger log = LoggerFactory.getLogger(Enricher.class);

    public static void main(String args[]) throws Exception {
        if (args.length == 1) {
            Config config = new Config(args[0]);
            Builder builder = new Builder(config.clone());

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    builder.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                log.info("Stopped Enricher process.");
            }));


        } else {
            log.error("Execute: java -cp ${JAR_PATH} io.wizzie.ks.enricher.Enricher <config_file>");
        }

    }
}
