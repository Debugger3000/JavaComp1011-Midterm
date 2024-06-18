module ca.georgiancollege.javamidterm {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.georgiancollege.javamidterm to javafx.fxml;
    exports ca.georgiancollege.javamidterm;
}