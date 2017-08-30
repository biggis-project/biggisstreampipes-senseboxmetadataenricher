package de.fzi.ipe.biggis.streampipes.flink;

import de.fzi.cep.sepa.commons.config.ClientConfiguration;

/**
 * Created by Jochen Lutz on 2017-08-02.
 * Based on (or copied from) semantic-epa-parent/semantic-epa-flink-samples/src/main/java/de/fzi/cep/sepa/flink/samples/Config.java
 */
public class Config {
    //public static final String JAR_FILE = "./biggis-streampipes-flink-processing-elements-1.0-SNAPSHOT.jar";
    public static final String JAR_FILE = "./target/SenseboxMetadataEnricher-1.0-SNAPSHOT.jar";

    public static final String FLINK_HOST = ClientConfiguration.INSTANCE.getFlinkHost();

    public static final int FLINK_PORT = ClientConfiguration.INSTANCE.getFlinkPort();

    public static final String iconBaseUrl = ClientConfiguration.INSTANCE.getIconUrl() +"/img";

    public static final String getIconUrl(String pictureName) {
        return iconBaseUrl +"/" +pictureName +".png";
    }
}
