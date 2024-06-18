package ca.georgiancollege.javamidterm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {


        Student yeah = new Student();
        yeah.queryForData();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("student-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Students!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}