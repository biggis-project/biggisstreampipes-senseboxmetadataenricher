package de.fzi.ipe.biggis.streampipes.flink;

import de.fzi.cep.sepa.client.init.DeclarersSingleton;
import de.fzi.cep.sepa.client.standalone.init.StandaloneModelSubmitter;
import de.fzi.ipe.biggis.streampipes.flink.enrich.location.SenseboxLocationController;
import de.fzi.ipe.biggis.streampipes.flink.enrich.location.SenseboxLocationProgram;
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

        DeclarersSingleton.getInstance().setPort(8096);
        new FlinkInit().init();
    }
}
