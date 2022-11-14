import com.fasterxml.jackson.databind.ObjectMapper;
import core.Line;
import core.Station;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Json {
    private static final String PATH = "C:\\Users\\Yakov\\IdeaProjects\\HomeWork\\FilesAndNetwork\\homework5\\Map.json";
    private static final String REGEX_FOR_STATION = "[0-9][0-9]?.";
    private static Map<String, Line> lines;
    private static Map<Line, List<Station>> stations;

    public static void parser(String path) throws IOException {
        Document doc = Jsoup.connect(path).get();
        Element metrodata = doc.getElementById("metrodata");
        Elements elementsOfLines = metrodata.getElementsByClass("js-metro-line");
        addLines(elementsOfLines);
        Elements elementsOfStations = metrodata.getElementsByClass("js-metro-stations");
        addStations(elementsOfStations);
        createJson();
    }

    public static void addLines(Elements elements) {
        lines = new LinkedHashMap<>();
        for (Element element : elements) {
            Line line = new Line(element.text(), element.attr("data-line"));
            lines.put(line.getNumber(), line);
        }
    }

    public static void addStations(Elements elements) {
        stations = new LinkedHashMap<>();
        for (Element element : elements) {
            Line line = new Line(lines.get(element.attr("data-line")).getName(), lines.get(element.attr("data-line")).getNumber());
            String[] arrayStation = element.text().replaceAll(REGEX_FOR_STATION, "").split("\\s\\s");
            List<Station> stationList = new ArrayList<>();
            for (int i = 0; i < arrayStation.length; i++) {
                Station station = new Station(arrayStation[i], line);
                stationList.add(station);
            }
            stations.put(line, stationList);
        }
    }

    public static void createJson() throws IOException {
        FileWriter file = new FileWriter(PATH);
        JSONObject mainJson = new JSONObject();
        JSONObject jsonStations = new JSONObject();
        JSONArray jsonArrayLines = new JSONArray();

        Set<Line> linesInMapStation = stations.keySet();
        for (Line line : linesInMapStation) {
            JSONArray stationsArray = new JSONArray();
            List<Station> stationsList = stations.get(line);
            for (int j = 0; j < stationsList.size(); j++) {
                String station = stationsList.get(j).getName().trim();
                stationsArray.add(station);
            }
            jsonStations.put(line, stationsArray);
        }

        Set<String> lineNumber = lines.keySet();
        for (String number : lineNumber) {
            JSONObject buffer = new JSONObject();
            String lineName = lines.get(number).getName();
            buffer.put("Line number:", number);
            buffer.put("Line name:", lineName);
            jsonArrayLines.add(buffer);
        }
        mainJson.put("Station", jsonStations);
        mainJson.put("Line", jsonArrayLines);

        ObjectMapper mapper = new ObjectMapper();
        file.write(mainJson.toJSONString());
        file.close();
    }
}
