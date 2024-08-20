package fauzannazz.ahocorasick;

import javafx.scene.control.Label;

public class ErrorController {
    public Label ErrorMessage;

    public void setError(String message) {
        ErrorMessage.setText(message);
    }
}
