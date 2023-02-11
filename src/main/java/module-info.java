module app.javafxgym {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens app.javafxgym to javafx.fxml;
    exports app.javafxgym;
}