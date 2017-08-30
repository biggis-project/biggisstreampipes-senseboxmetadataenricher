package de.fzi.ipe.biggis.streampipes.flink.enrich.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.libs.json.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.io.FileUtils.readFileToString;

/**
 * Created by lutz on 02.08.17.
 */
public class SenseboxLocationEnricher implements org.apache.flink.api.common.functions.FlatMapFunction<java.util.Map<String, Object>, Map<String, Object>> {
    private static final Logger LOG = LoggerFactory.getLogger(SenseboxLocationEnricher.class);

    class BoxMapEntry {
        double latitude;
        double longitude;
        double altitude;
        String osmId;

        BoxMapEntry(double latitude, double longitude, double altitude, String osmId) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.altitude = altitude;
            this.osmId = osmId;
        }

        double getLatitude() { return this.latitude; }
        double getLongitude() { return this.longitude; }
        double getAltitude() { return this.altitude; }
        String getOsmId() { return this.osmId; }
    };

    private static Map<String, BoxMapEntry> boxMap;

    @Override
    public void flatMap(Map<String, Object> in, Collector<Map<String, Object>> out) throws Exception {
        //TODO: hier könnte man das Alter der boxMap prüfen und die regelmäßig regenerieren
        //      solange die Daten aber aus einer JSON-Datei, die als Ressource mit im JAR liegt, kommen, lohnt das nicht

        in.put("latitude", "Hallo Welt");
        in.put("lonitude", "Hallo Welt");
        in.put("altitude", "Hallo Welt");
        in.put("openSenseMapID", "Hallo Welt");
        out.collect(in);
    }

    public static void main(String[] args) {
        LOG.info("This is main!");

        readBoxMap();
    }

    private static void readBoxMap() {
        //TODO: wir könnten die per HTTP holen und die in ein Volume legen, von dem die Nginx serviert. Dann wäre das wenigsten so ähnlich wie live bearbeitbar
        String jsonRaw;
        JsObject json;

        Map<String, BoxMapEntry> newBoxMap = new HashMap<String, BoxMapEntry>();

        ObjectMapper mapper = new ObjectMapper();

        try {
            jsonRaw = readBoxMapFromResourceFile();

            LOG.info(jsonRaw);

            newBoxMap = mapper.readValue(jsonRaw, Map.class);
/*
            json = (JsObject)Json.parse(jsonRaw);

            json.keys().foreach()
                    //(key -> { JsObject obj = json.get(key); });

            newBoxMap = new HashMap<>(json.keys().size());

            //TODO: validieren und in den Entry packen und den in die Map

            BoxMapEntry e = new BoxMapEntry();*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        boxMap = newBoxMap;
    }

    private static String readBoxMapFromResourceFile() throws IOException {
        ClassLoader classLoader = SenseboxLocationEnricher.class.getClassLoader();
        File file = new File(classLoader.getResource("sensebox-registry.json").getFile());

        return readFileToString(file, StandardCharsets.UTF_8);
    }
}
