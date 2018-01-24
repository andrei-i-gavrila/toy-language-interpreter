package com.andrei.impl.view.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.andrei.impl.view.Programs.PROGRAMS;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgramSelectorView.fxml"));
        stage.setScene(new Scene(loader.load()));
        ProgramSelectorViewController controller = loader.getController();
        controller.initialize(PROGRAMS);
        stage.show();
    }
}
