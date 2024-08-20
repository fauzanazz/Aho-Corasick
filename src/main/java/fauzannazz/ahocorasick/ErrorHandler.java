package fauzannazz.ahocorasick;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErrorHandler {
    public void onError(String error) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ErrorController.class.getResource("error.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 338, 156);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Error!");
        stage.setScene(scene);
        stage.show();

        ErrorController errorController = fxmlLoader.getController();
        errorController.setError(error);
    }
}
