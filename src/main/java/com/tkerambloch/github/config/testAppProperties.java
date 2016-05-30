package com.tkerambloch.github.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by tkerambloch on 06/01/16.
 */
@ConfigurationProperties(prefix = "testApp", ignoreUnknownFields = false)
public class testAppProperties {

    private final Metrics metrics = new Metrics();

    public Metrics getMetrics() {
        return metrics;
    }

    public static class Metrics {

        private final Graphite graphite = new Graphite();

        public Graphite getGraphite() {
            return graphite;
        }

        public static class Graphite {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 2003;

            private String prefix = "testApp";

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }
        }
    }
}
