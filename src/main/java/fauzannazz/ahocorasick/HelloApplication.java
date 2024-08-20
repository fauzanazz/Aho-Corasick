package fauzannazz.ahocorasick;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Aho-Corasick Algorithm");
        stage.setScene(scene);
        stage.show();
        System.setProperty("org.graphstream.ui", "javafx");
    }

    public static void main(String[] args) {
        launch(args);
    }
}