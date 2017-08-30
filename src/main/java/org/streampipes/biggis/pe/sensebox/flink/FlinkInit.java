package org.streampipes.biggis.pe.sensebox.flink;

import org.streampipes.container.init.DeclarersSingleton;
import org.streampipes.container.standalone.init.StandaloneModelSubmitter;
import org.streampipes.biggis.pe.sensebox.flink.enrich.location.SenseboxLocationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lutz on 02.08.17.
 */
public class FlinkInit extends StandaloneModelSubmitter {
    private static final Logger LOG = LoggerFactory.getLogger(FlinkInit.class);

    public static void main(String[] args) {
        LOG.debug("Debug World");
        LOG.info("Info World");
        LOG.warn("Warn World");

        DeclarersSingleton.getInstance()
                .add(new SenseboxLocationController());

        DeclarersSingleton.getInstance().setHostName(Config.INSTANCE.getHost());
        DeclarersSingleton.getInstance().setPort(Config.INSTANCE.getPort());
        new FlinkInit().init();
    }
}
