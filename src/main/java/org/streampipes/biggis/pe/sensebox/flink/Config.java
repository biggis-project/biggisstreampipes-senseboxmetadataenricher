package org.streampipes.biggis.pe.sensebox.flink;

import org.streampipes.config.SpConfig;

/**
 * Created by Jochen Lutz on 2017-08-02.
 * Based on (or copied from) semantic-epa-parent/semantic-epa-flink-samples/src/main/java/de/fzi/cep/sepa/flink/samples/Config.java
 */
public enum Config {
    INSTANCE;

    private SpConfig config;
    private final static String HOST = "host";
    private final static String PORT = "port";
    private final static String KAFKA_HOST = "kafka_host";
    private final static String KAFKA_PORT = "kafka_port";
    private final static String FLINK_HOST = "flink_host";
    private final static String FLINK_PORT = "flink_port";
    private final static String SENSEBOX_REGISTRY_URL = "sensebox_registry_url";
    private final static String SENSEBOX_REGISTRY_MAX_AGE = "sensebox_registry_max_age";

    public final static String serverUrl;
    public final static String iconBaseUrl;

    public static final String JAR_FILE = "./target/SenseboxMetadataEnricher-1.0-SNAPSHOT.jar";

    Config() {
        config = SpConfig.getSpConfig("pe/org.streampipes.biggis.pe.sensebox.flink");
        config.register(HOST, "sensebox-metadata-enricher", "Hostname for the sensebox flink pe integration");
        config.register(PORT, 8090, "Port for the sensebox flink pe integration");
        config.register(KAFKA_HOST, "kafka", "Host for kafka of the pe sinks project");
        config.register(KAFKA_PORT, 9092, "Port for kafka of the pe sinks project");
        config.register(FLINK_HOST, "flink", "Host for flink of the pe sinks project");
        config.register(FLINK_PORT, 6123, "Port for flink of the pe sinks project");
        config.register(SENSEBOX_REGISTRY_URL, "http://ipe-koi09.fzi.de/static-data/sensebox-registry.json", "The URL where to sensebox-registry.json");
        config.register(SENSEBOX_REGISTRY_MAX_AGE, 600, "Maximum age (in seconds) of cached sensebox registry data");
    }

    static {
        serverUrl = Config.INSTANCE.getHost() + ":" + Config.INSTANCE.getPort();
        iconBaseUrl = Config.INSTANCE.getHost() + ":" + Config.INSTANCE.getPort() +"/img";
    }

    public String getHost() {
        return config.getString(HOST);
    }

    public int getPort() {
        return config.getInteger(PORT);
    }

    public String getKafkaHost() {
        return config.getString(KAFKA_HOST);
    }

    public int getKafkaPort() {
        return config.getInteger(KAFKA_PORT);
    }

    public String getFlinkHost() {
        return config.getString(FLINK_HOST);
    }

    public int getFlinkPort() {
        return config.getInteger(FLINK_PORT);
    }

    public String getSenseboxRegistryUrl() { return config.getString(SENSEBOX_REGISTRY_URL); }

    public int getSenseboxRegistryMaxAge() { return config.getInteger(SENSEBOX_REGISTRY_MAX_AGE); }

    public String getIconUrl(String icon) { return iconBaseUrl + "/" + icon; }
}
