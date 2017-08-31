package org.streampipes.biggis.pe.sensebox.flink.enrich.location;

import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * Created by Jochen Lutz on 2017-08-02.
 */
public class SenseboxLocationEnricher implements org.apache.flink.api.common.functions.FlatMapFunction<java.util.Map<String, Object>, Map<String, Object>> {
    private static final Logger LOG = LoggerFactory.getLogger(SenseboxLocationEnricher.class);

    private static SenseboxLocationRegistryParser registryParser;
    private static long senseboxRegistryMaxAge;
    private static long senseboxRegistryLastFetched = 0;
    private static SenseboxLocationRegistry senseboxRegistry;

    public SenseboxLocationEnricher(String senseboxRegistryUrl, int senseboxRegistryMaxAge) {
        this.senseboxRegistryMaxAge = senseboxRegistryMaxAge * 1000;
        this.registryParser = new SenseboxLocationRegistryParser(senseboxRegistryUrl);

        senseboxRegistry = registryParser.parseSenseboxRegistry();
        senseboxRegistryLastFetched = System.currentTimeMillis();
    }

    @Override
    public void flatMap(Map<String, Object> in, Collector<Map<String, Object>> out) throws Exception {
        if (System.currentTimeMillis() > senseboxRegistryLastFetched + senseboxRegistryMaxAge) {
            senseboxRegistry = registryParser.parseSenseboxRegistry();
            senseboxRegistryLastFetched = System.currentTimeMillis();
        }

        if (senseboxRegistry.containsKey(in.get("boxId"))) {
            SenseboxLocationRegistryEntry entry = senseboxRegistry.get(in.get("boxId"));

            in.put("latitude", entry.getLatitude());
            in.put("longitude", entry.getLongitude());
            in.put("altitude", entry.getAltitude());
            in.put("openSenseMapID", entry.getOpenSenseMapIDId());
        }
        out.collect(in);
    }
}
