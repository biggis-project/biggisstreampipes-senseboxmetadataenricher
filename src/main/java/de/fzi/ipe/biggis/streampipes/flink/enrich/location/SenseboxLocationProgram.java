package de.fzi.ipe.biggis.streampipes.flink.enrich.location;

import de.fzi.cep.sepa.flink.FlinkDeploymentConfig;
import de.fzi.cep.sepa.flink.FlinkSepaRuntime;
import org.apache.flink.streaming.api.datastream.DataStream;

import java.util.Map;

/**
 * Created by lutz on 02.08.17.
 */
public class SenseboxLocationProgram extends FlinkSepaRuntime<SenseboxLocationParameters> {
    public SenseboxLocationProgram(SenseboxLocationParameters params, FlinkDeploymentConfig config) {
        super(params, config);
    }

    public SenseboxLocationProgram(SenseboxLocationParameters params) {
        super(params);
    }

    @Override
    protected DataStream<Map<String, Object>> getApplicationLogic(DataStream<Map<String, Object>>[] messageStream) {
        return messageStream[0].flatMap(new SenseboxLocationEnricher());
    }
}
