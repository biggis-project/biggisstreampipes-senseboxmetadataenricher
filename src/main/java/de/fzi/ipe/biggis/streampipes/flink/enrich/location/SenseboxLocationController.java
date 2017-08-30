package de.fzi.ipe.biggis.streampipes.flink.enrich.location;

import de.fzi.cep.sepa.flink.AbstractFlinkAgentDeclarer;
import de.fzi.cep.sepa.flink.FlinkDeploymentConfig;
import de.fzi.cep.sepa.flink.FlinkSepaRuntime;
import de.fzi.cep.sepa.model.impl.EpaType;
import de.fzi.cep.sepa.model.impl.graph.SepaDescription;
import de.fzi.cep.sepa.model.impl.graph.SepaInvocation;
import de.fzi.cep.sepa.model.vocabulary.SO;
import de.fzi.cep.sepa.sdk.builder.ProcessingElementBuilder;
import de.fzi.cep.sepa.sdk.extractor.ProcessingElementParameterExtractor;
import de.fzi.cep.sepa.sdk.helpers.*;
import de.fzi.ipe.biggis.streampipes.flink.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jochen Lutz on 2017-08-01.
 */
public class SenseboxLocationController extends AbstractFlinkAgentDeclarer<SenseboxLocationParameters> {
    private static final Logger LOG = LoggerFactory.getLogger(SenseboxLocationProgram.class);

    @Override
    public SepaDescription declareModel() {
        LOG.info("declareModel()");

        return ProcessingElementBuilder.create("enrich_sensebox_location", "Sensebox Location Enrichment",
                "Appends the location and the OpenSenseMap ID of SenseBoxes " +
                        "from the external SenseBox Metadata Store")
                .category(EpaType.ENRICH)
                .iconUrl(Config.getIconUrl("enrich-timestamp-icon"))
                .requiredPropertyStream1(EpRequirements.anyProperty())//TODO
                .outputStrategy(OutputStrategies.append(
                        EpProperties.doubleEp("latitude", SO.Latitude),
                        EpProperties.doubleEp("longitude", SO.Longitude),
                        EpProperties.doubleEp("altitude", SO.Elevation),
                        EpProperties.stringEp("openSenseMapID", SO.ServiceUrl) //TODO: gibt's was besseres?
                        )
                )
                .supportedProtocols(SupportedProtocols.kafka())
                .supportedFormats(SupportedFormats.jsonFormat())
                .build();
    }

    @Override
    protected FlinkSepaRuntime<SenseboxLocationParameters> getRuntime(SepaInvocation graph) {
        LOG.warn("Jar: " + Config.JAR_FILE + ", Flink: " + Config.FLINK_HOST + ":" + Config.FLINK_PORT);

        ProcessingElementParameterExtractor extractor = ProcessingElementParameterExtractor.from(graph);

        SenseboxLocationParameters staticParam = new SenseboxLocationParameters(
                graph
        );

        /* ohne FlinkDeploymentConfig() wird das im Local Mode gestartet */
        return new SenseboxLocationProgram(staticParam);
        //return new SenseboxLocationProgram(staticParam, new FlinkDeploymentConfig(Config.JAR_FILE, Config.FLINK_HOST, Config.FLINK_PORT));
    }
}
