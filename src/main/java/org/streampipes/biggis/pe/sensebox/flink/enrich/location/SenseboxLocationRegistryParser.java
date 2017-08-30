package org.streampipes.biggis.pe.sensebox.flink.enrich.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import play.api.libs.json.JsObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jochen Lutz on 2017-08-30.
 */
public class SenseboxLocationRegistryParser {
    private String senseboxRegistryUrl;

    SenseboxLocationRegistryParser(String senseboxRegistryUrl) {
        this.senseboxRegistryUrl = senseboxRegistryUrl;
    }

    SenseboxLocationRegistry parseSenseboxRegistry() {
        ObjectMapper mapper = new ObjectMapper();

        SenseboxLocationRegistry map;
        try {
            map = mapper.readValue(new URL(senseboxRegistryUrl), SenseboxLocationRegistry.class);
        } catch (IOException e) {
            e.printStackTrace();
            map = new SenseboxLocationRegistry();//Fallback: leere Liste zurückgeben
        }

        return map;
    }
}
