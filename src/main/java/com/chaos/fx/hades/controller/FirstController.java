package com.chaos.fx.hades.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
public class FirstController {
    @FXML
    private TextField userName;
    @FXML
    private TextField primaryKey;
    @FXML
    private TextField projectName;
    @FXML
    private Button submitBtn;


    @FXML
    public void submitAction (ActionEvent event) throws IOException {
        if (userName.getText().equals("")
                || primaryKey.getText().equals("")
                || projectName.getText().equals("")) {

            makeAlert(" Warning ! ", "用户名，私钥或者项目名中有未填写的");
        } else {
            Stage stage = (Stage) submitBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(ClassLoader.getSystemResource("second.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }

    @FXML
    public void clearMetaAction (ActionEvent event) {
        userName.setText("");
        primaryKey.setText("");
        projectName.setText("");
    }

    private void makeAlert (String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
