package com.example.cs213_pizzaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MainApplication sets up the main stage for the main menu and shows it.
 * @author Alexis Wilson, James Alba
 */
public class MainApplication extends Application {
    /**
     * Creates a scene for the given stage and shows it
     * @param stage stage object from controller
     * @throws IOException IO exception on faulty stage object
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Launches the application
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}