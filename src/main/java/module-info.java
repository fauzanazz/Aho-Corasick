module fauzannazz.ahocorasick {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires gs.core;
    requires java.desktop;
    requires gs.ui.javafx;

    opens fauzannazz.ahocorasick to com.fasterxml.jackson.databind, javafx.fxml;
    exports fauzannazz.ahocorasick;
}