package fauzannazz.ahocorasick;
import javafx.scene.control.TextArea;


public class SolveWindowController {

    public TextArea outputText;

    private String filePath;
    private ErrorHandler errorHandler;

    public void initialize() {
        outputText.setEditable(false);
    }

    public void setOutputText(String text) {
        outputText.setText(text);
    }
}