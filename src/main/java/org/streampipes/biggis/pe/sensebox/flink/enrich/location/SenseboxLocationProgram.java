package org.streampipes.biggis.pe.sensebox.flink.enrich.location;

import org.streampipes.biggis.pe.sensebox.flink.Config;
import org.streampipes.wrapper.flink.FlinkDeploymentConfig;
import org.streampipes.wrapper.flink.FlinkSepaRuntime;
import org.apache.flink.streaming.api.datastream.DataStream;

import java.util.Map;

/**
 * Created by Jochen Lutz on 2017-08-02.
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
        return messageStream[0].flatMap(new SenseboxLocationEnricher(Config.INSTANCE.getSenseboxRegistryUrl(), Config.INSTANCE.getSenseboxRegistryMaxAge()));
    }
}
