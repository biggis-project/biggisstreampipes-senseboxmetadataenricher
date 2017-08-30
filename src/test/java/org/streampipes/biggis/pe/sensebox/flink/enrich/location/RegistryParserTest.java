package org.streampipes.biggis.pe.sensebox.flink.enrich.location;

import org.streampipes.biggis.pe.sensebox.flink.enrich.location.SenseboxLocationRegistryParser;

import java.util.Map;

/**
 * Created by Jochen Lutz on 2017-08-30.
 */
public class RegistryParserTest {
    private static final String registryUrl = "http://ipe-koi09.fzi.de/static-data/sensebox-registry.json";
    private static SenseboxLocationRegistry senseboxRegistry;

    public static void main(String[] args) {
        SenseboxLocationRegistryParser parser = new SenseboxLocationRegistryParser(registryUrl);

        senseboxRegistry = parser.parseSenseboxRegistry();

        System.out.println("Done.");
    }
}
