package org.streampipes.biggis.pe.sensebox.flink.enrich.location;

import org.streampipes.wrapper.flink.AbstractFlinkAgentDeclarer;
import org.streampipes.wrapper.flink.FlinkSepaRuntime;
import org.streampipes.model.impl.EpaType;
import org.streampipes.model.impl.graph.SepaDescription;
import org.streampipes.model.impl.graph.SepaInvocation;
import org.streampipes.model.vocabulary.SO;
import org.streampipes.sdk.builder.ProcessingElementBuilder;
import org.streampipes.sdk.extractor.ProcessingElementParameterExtractor;
import org.streampipes.sdk.helpers.*;
import org.streampipes.biggis.pe.sensebox.flink.Config;
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
                .iconUrl(Config.INSTANCE.getIconUrl("enrich-timestamp-icon"))
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
        LOG.warn("Jar: " + Config.JAR_FILE + ", Flink: " + Config.INSTANCE.getFlinkHost() + ":" + Config.INSTANCE.getFlinkPort());

        ProcessingElementParameterExtractor extractor = ProcessingElementParameterExtractor.from(graph);

        SenseboxLocationParameters staticParam = new SenseboxLocationParameters(
                graph
        );

        /* ohne FlinkDeploymentConfig() wird das im Local Mode gestartet */
        return new SenseboxLocationProgram(staticParam);
        //return new SenseboxLocationProgram(staticParam, new FlinkDeploymentConfig(Config.JAR_FILE, Config.FLINK_HOST, Config.FLINK_PORT));
    }
}
