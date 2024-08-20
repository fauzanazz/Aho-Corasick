package fauzannazz.ahocorasick;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser {

    private String text;
    private List<String> patterns;
    private ErrorHandler errorHandler = new ErrorHandler();

    public JsonParser() {
        this.text = "";
        this.patterns = null;
    }

    public void parseJson(File file) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonParser jsonParser = objectMapper.readValue(file, JsonParser.class);
            this.text = jsonParser.text;
            this.patterns = jsonParser.patterns;
        } catch (IOException e) {
            errorHandler.onError("Error parsing JSON file");
        }
    }

    public String getText() {
        return text;
    }

    public List<String> getPatterns() {
        return patterns;
    }
}