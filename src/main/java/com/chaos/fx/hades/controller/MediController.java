package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.InfoMeta;
import com.chaos.fx.hades.util.Api;
import com.chaos.fx.hades.util.DataSaver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import okhttp3.Response;

import java.io.IOException;

import static com.chaos.fx.hades.util.Dialog.makeAlert;

/**
 * Created by zcfrank1st on 28/12/2016.
 */
public class MediController {
    @FXML
    private Button createBtn;


    @FXML
    public void createAction () throws IOException {
        InfoMeta meta = DataSaver.INSTANCE.getInfoMeta();
        Response createResponse = Api.init(meta.getPrivateKey(), meta.getUserName(), meta.getProjectName(), meta.getProjectPath());

        if (createResponse.isSuccessful()) {
            Stage stage = (Stage) createBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(ClassLoader.getSystemResource("second.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } else {
            makeAlert(" Error ! ", "初始化项目配置失败");
        }
    }

    @FXML
    public void initialize() {
        String role = DataSaver.INSTANCE.getData("role");

        if (!role.equals("0")) {
            createBtn.setDisable(true);
        }
    }


}
