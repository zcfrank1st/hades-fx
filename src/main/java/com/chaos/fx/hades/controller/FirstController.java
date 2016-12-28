package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.InfoMeta;
import com.chaos.fx.hades.util.Api;
import com.chaos.fx.hades.util.DataSaver;
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
    private TextField privateKey;
    @FXML
    private TextField projectName;
    @FXML
    private TextField projectPath;
    @FXML
    private Button submitBtn;


    @FXML
    public void submitAction () throws IOException {
        String userNameText = userName.getText();
        String privateKeyText = privateKey.getText();
        String projectNameText = projectName.getText();
        String projectPathText = projectPath.getText();

        if (userNameText.equals("")
                || privateKeyText.equals("")
                || projectNameText.equals("")
                || projectPathText.equals("")) {

            makeAlert(" Warning ! ", "用户名，私钥,项目名或者项目路径中有未填写的");
        } else {
            DataSaver.INSTANCE.setData(DataSaver.USER_NAME, userNameText);
            DataSaver.INSTANCE.setData(DataSaver.PRIVATE_KEY, privateKeyText);
            DataSaver.INSTANCE.setData(DataSaver.PROJECT_NAME, projectNameText);
            DataSaver.INSTANCE.setData(DataSaver.PROJECT_PATH, projectPathText);

            // TODO check response to jump
            Api.exists(privateKeyText, userNameText, projectNameText, projectPathText);

            Stage stage = (Stage) submitBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(ClassLoader.getSystemResource("second.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }

    @FXML
    public void clearAction () {
        userName.setText("");
        privateKey.setText("");
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
