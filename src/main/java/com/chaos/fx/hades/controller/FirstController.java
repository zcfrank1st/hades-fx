package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.InfoMeta;
import com.chaos.fx.hades.model.Message;
import com.chaos.fx.hades.util.Api;
import com.chaos.fx.hades.util.Conf;
import com.chaos.fx.hades.util.DataSaver;
import com.chaos.fx.hades.util.Tool;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import okhttp3.Response;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.chaos.fx.hades.util.Dialog.makeAlert;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
public class FirstController {
    private static final Gson gson = Tool.GSON_INSTANCE;
    private static final String STORE_FILE = Conf.conf.getString("hades.filestore") + "hadesfx.yml";

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

            Response response = Api.exists(privateKeyText, userNameText, projectNameText, projectPathText);

            try {


                if (!response.isSuccessful()) {
                    makeAlert(" Error ! ", "用户名，私钥,项目名或者项目路径中有错误");
                } else {
                    Message message = gson.fromJson(response.body().string(), Message.class);
                    DataSaver.INSTANCE.setData("role", message.getCode() + "");
                    writeStoreFile(userNameText, privateKeyText, projectNameText, projectPathText);

                    Stage stage = (Stage) submitBtn.getScene().getWindow();

                    if ((Boolean) message.getBody()) {
                        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("second.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                    } else {
                        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("medi.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                    }
                }
            } finally {
                response.body().close();
            }

        }
    }

    @FXML
    private void initialize() throws IOException {
        InfoMeta meta = readStoreFile();
        if (null != meta) {
            userName.setText(meta.getUserName());
            privateKey.setText(meta.getPrivateKey());
            projectName.setText(meta.getProjectName());
            projectPath.setText(meta.getProjectPath());
        }
    }

    @FXML
    public void clearAction () {
        userName.setText("");
        privateKey.setText("");
        projectName.setText("");
        projectPath.setText("");
    }

    private void writeStoreFile (String userNameText, String privateKeyText, String projectNameText, String projectPathText) throws IOException {
        Yaml yaml = new Yaml();
        InfoMeta meta = new InfoMeta();
        meta.setUserName(userNameText);
        meta.setPrivateKey(privateKeyText);
        meta.setProjectName(projectNameText);
        meta.setProjectPath(projectPathText);
        yaml.dump(meta, new FileWriter(new File(STORE_FILE)));
    }

    private InfoMeta readStoreFile () throws IOException {
        if (!Files.exists(Paths.get(STORE_FILE))) {
            return null;
        }

        Yaml yaml = new Yaml();
        return yaml.loadAs(Files.newInputStream(Paths.get(STORE_FILE)), InfoMeta.class);
    }
}
