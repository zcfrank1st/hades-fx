package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.DataSaver;
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
    public void submitAction () throws IOException {
        String userNameText = userName.getText();
        String primaryKeyText = primaryKey.getText();
        String projectNameText = projectName.getText();

        if (userNameText.equals("")
                || primaryKeyText.equals("")
                || projectNameText.equals("")) {

            makeAlert(" Warning ! ", "用户名，私钥或者项目名中有未填写的");
        } else {
            DataSaver.INSTANCE.setData("userName", userNameText);
            DataSaver.INSTANCE.setData("primaryKey", primaryKeyText);
            DataSaver.INSTANCE.setData("projectName", projectNameText);
            Stage stage = (Stage) submitBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(ClassLoader.getSystemResource("second.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }

    @FXML
    public void clearAction () {
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
