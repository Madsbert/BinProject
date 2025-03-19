module org.example.binproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens org.example.binproject to javafx.fxml;
    opens org.example.binproject.Controllers to javafx.fxml;
    opens org.example.binproject.GUI to javafx.fxml;

    exports org.example.binproject;
    exports org.example.binproject.Controllers;
    exports org.example.binproject.GUI;
}