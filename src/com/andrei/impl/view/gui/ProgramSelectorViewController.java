package com.andrei.impl.view.gui;

import com.andrei.interfaces.domain.IStatement;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.util.List;

public class ProgramSelectorViewController {
    public ChoiceBox<IStatement> programList;
    public Button startButton;

    private final Stage interpreterStage = new Stage();

    public void initialize(List<IStatement> programs) {
        programList.setItems(FXCollections.observableArrayList(programs));
    }

    public void startProgram(ActionEvent actionEvent) throws Exception {

        if (programList.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No program selected");
            alert.setHeaderText("Select a program");
            alert.showAndWait();
            return;
        }

        interpreterStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterpreterView.fxml"));
        interpreterStage.setScene(new Scene(loader.load()));
        InterpreterViewController interpreterViewController = loader.getController();
        interpreterViewController.initialize(programList.getSelectionModel().getSelectedItem());
        interpreterStage.show();
    }
}
