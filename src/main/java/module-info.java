module ca.georgiancollege.javamidterm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens ca.georgiancollege.javamidterm to javafx.fxml;
    exports ca.georgiancollege.javamidterm;
}