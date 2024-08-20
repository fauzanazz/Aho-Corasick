package fauzannazz.ahocorasick;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HelloController {
    @FXML
    public Button fileOpenButton;
    public Button solveButton;
    public TextArea Pattern;
    public Label ChosenFileLink;
    public TextArea Text;

    private File chosenFile;
    private final JsonParser jsonParser = new JsonParser();
    private final ErrorHandler errorHandler = new ErrorHandler();
    private final Ahocorasick ahocorasick = new Ahocorasick();
    private String text;
    private String[] patterns;
    private String outputText;

    public void initialize() {
        Pattern.setEditable(false);
        Text.setEditable(false);
    }

    @FXML
    protected void onFileOpenButtonClick() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        chosenFile = fileChooser.showOpenDialog(fileOpenButton.getScene().getWindow());

        if (chosenFile != null) {
            ChosenFileLink.setText("File chosen: " + chosenFile.getName());
            jsonParser.parseJson(chosenFile);
            Pattern.setText(jsonParser.getPatterns().toString());
            Text.setText(jsonParser.getText());
            text = jsonParser.getText();
            patterns = jsonParser.getPatterns().toArray(new String[0]);
        } else {
            ChosenFileLink.setText("No file chosen");
        }
    }

    @FXML
    protected void onSolveButtonClick() throws Exception {
        if (chosenFile != null) {

            if (text == null || patterns == null) {
                errorHandler.onError("Error parsing JSON file");
                return;
            }

            StringBuilder outputTextBuilder = new StringBuilder();
            for (int i = 0; i < patterns.length; i++) {
                ahocorasick.addPattern(patterns[i], i);
            }
            ahocorasick.build();
            Map<String, List<int[]>> matches = ahocorasick.search(text, patterns);

            for (Map.Entry<String, List<int[]>> entry : matches.entrySet()) {
                String pattern = entry.getKey();
                List<int[]> indices = entry.getValue();
                outputTextBuilder.append("Pola \"")
                        .append(pattern)
                        .append("\" ditemukan ")
                        .append(indices.size())
                        .append("x, ditemukan pada indeks ")
                        .append(Arrays.deepToString(indices.toArray()))
                        .append("\n");
            }
            outputText = outputTextBuilder.toString();
            SolveWindowSpawner();
        } else {
            errorHandler.onError("No file chosen");
        }
    }

    private void SolveWindowSpawner() throws Exception {
        showText();
        showGraph();
    }

    private void showText() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SolveWindowController.class.getResource("solve-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Solve Window");
        stage.setScene(scene);
        stage.show();

        SolveWindowController solveWindowController = fxmlLoader.getController();
        solveWindowController.setOutputText(outputText);
    }

    private void showGraph() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphShow.class.getResource("graph-show.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Graph Show");
        stage.setScene(scene);
        stage.show();

        GraphShow graphShow = fxmlLoader.getController();
        graphShow.start(ahocorasick);
    }
}