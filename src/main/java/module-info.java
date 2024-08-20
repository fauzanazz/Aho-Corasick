module fauzannazz.ahocorasick {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires gs.core;
    requires java.desktop;
    requires gs.ui.javafx;

    opens fauzannazz.ahocorasick to com.fasterxml.jackson.databind, javafx.fxml, javafx.graphics, javafx.controls;
    exports fauzannazz.ahocorasick;
}