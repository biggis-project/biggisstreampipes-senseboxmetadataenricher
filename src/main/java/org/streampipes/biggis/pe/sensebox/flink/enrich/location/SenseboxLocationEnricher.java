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
    private static int senseboxRegistryMaxAge;
    private static SenseboxLocationRegistry senseboxRegistry;

    public SenseboxLocationEnricher(String senseboxRegistryUrl, int senseboxRegistryMaxAge) {
        this.senseboxRegistryMaxAge = senseboxRegistryMaxAge;
        this.registryParser = new SenseboxLocationRegistryParser(senseboxRegistryUrl);

        senseboxRegistry = registryParser.parseSenseboxRegistry();
    }

    @Override
    public void flatMap(Map<String, Object> in, Collector<Map<String, Object>> out) throws Exception {
        //TODO: hier könnte man das Alter der boxMap prüfen und die regelmäßig regenerieren
        //TODO: dann entsprechend Timestamp lastRead hinzufügen und vergleichen

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
