module com.example.progettocasotto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.progettocasotto to javafx.fxml;
    exports com.example.progettocasotto;
    exports com.example.progettocasotto.DataBase;
    opens com.example.progettocasotto.DataBase to javafx.fxml;
    exports com.example.progettocasotto.View;
    opens com.example.progettocasotto.View to javafx.fxml;
}