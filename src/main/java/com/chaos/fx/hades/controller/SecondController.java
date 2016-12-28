package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.ConfigContent;
import com.chaos.fx.hades.model.InfoMeta;
import com.chaos.fx.hades.model.Kv;
import com.chaos.fx.hades.model.Message;
import com.chaos.fx.hades.util.Api;
import com.chaos.fx.hades.util.DataSaver;
import com.chaos.fx.hades.util.Tool;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Map;

import static com.chaos.fx.hades.util.Dialog.makeAlert;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
public class SecondController {
    private static final Gson gson = Tool.GSON_INSTANCE;

    @FXML
    private Button backBtn;
    @FXML
    private Button deleteAllBtn;
    @FXML
    private TextField keyField;
    @FXML
    private TextField valueField;
    @FXML
    private Tab devTab;
    @FXML
    private Tab prdTab;
    @FXML
    private TableView<Kv> prdKvTableView;
    @FXML
    private TableColumn<Kv, String> prdKeyColumn;
    @FXML
    private TableColumn<Kv, String> prdValueColumn;
    @FXML
    private TableView<Kv> devKvTableView;
    @FXML
    private TableColumn<Kv, String> devKeyColumn;
    @FXML
    private TableColumn<Kv, String> devValueColumn;

    @FXML
    private TableColumn<Kv, Kv> devBtnColumn;
    @FXML
    private TableColumn<Kv, Kv> prdBtnColumn;

    private Integer profile = 1; // dev

    @FXML
    public void backAction() throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("first.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void deleteAllAction() throws IOException {
        InfoMeta meta = DataSaver.INSTANCE.getInfoMeta();
        Response deleteResponse = Api.deleteAll(meta.getPrivateKey(), meta.getUserName(), meta.getProjectName(), meta.getProjectPath());

        try {
            if (deleteResponse.isSuccessful()) {
                Stage stage = (Stage) deleteAllBtn.getScene().getWindow();
                Parent root = FXMLLoader.load(ClassLoader.getSystemResource("first.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } else {
                makeAlert(" ERROR ! ", "删除项目所有配置失败");
            }
        } finally {
            deleteResponse.body().close();
        }
    }

    @FXML
    public void addAction() throws IOException {
        String key = keyField.getText();
        String value = valueField.getText();

        if (key.equals("") || value.equals("")) {
            makeAlert(" Warning ! ", "新增配置缺少key或者value");
        } else {
            InfoMeta meta = DataSaver.INSTANCE.getInfoMeta();
            ConfigContent configContent = new ConfigContent();
            configContent.setEnv(this.profile);
            configContent.setKey(key);
            configContent.setProject(meta.getProjectName());
            configContent.setValue(value);
            Response addResponse = Api.addOne(meta.getPrivateKey(), meta.getUserName(), configContent, meta.getProjectPath());

            try {
                if (addResponse.isSuccessful()) {
                    this.dataReload(this.profile);
                } else {
                    makeAlert(" ERROR ! ", "新增配置失败，请重试");
                }
            } finally {
                addResponse.body().close();
            }
        }


    }

    @FXML
    public void loadData() throws IOException {
        if (devTab != null && devTab.isSelected()) {
            this.profile = 1;
            devKvTableView.setItems(this.queryData(this.profile));
        }
        if (prdTab != null && prdTab.isSelected()) {
            this.profile = 0;
            prdKvTableView.setItems(this.queryData(this.profile));
        }
    }

    @FXML
    private void initialize() {
        prdKeyColumn.setCellValueFactory(cellData -> cellData.getValue().getKey());
        prdValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValue());
        devKeyColumn.setCellValueFactory(cellData -> cellData.getValue().getKey());
        devValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValue());

        devBtnColumn.setCellFactory(cell -> new ButtonCell());
        devBtnColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));

        prdBtnColumn.setCellFactory(cell -> new ButtonCell());
        prdBtnColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
    }

    private ObservableList<Kv> queryData (Integer profile) throws IOException {
        ObservableList<Kv> lists = FXCollections.observableArrayList();

        InfoMeta meta = DataSaver.INSTANCE.getInfoMeta();
        Response response = Api.getAllThroughProfile(meta.getPrivateKey(), meta.getUserName(), meta.getProjectName(), profile, meta.getProjectPath());
        ResponseBody body = response.body();

        try {
            if (!response.isSuccessful()) {
                makeAlert(" Error! ", "获取项目配置失败");
            } else {
                Message<Map<String, String>> message = gson.fromJson(body.string(), new TypeToken<Message<Map<String, String>>>() {
                }.getType());
                Map<String, String> bodyMap = message.getBody();

                for (String key : bodyMap.keySet()) {
                    Kv kv = new Kv(key, bodyMap.get(key));
                    lists.add(kv);
                }
            }
            return lists;
        } finally {
            body.close();
        }
    }


    private class ButtonCell extends TableCell<Kv, Kv> {

        private Button cellButton;

        ButtonCell(){
            cellButton = new Button();
            cellButton.setOnAction(t -> {
                Kv kv = getItem();

                InfoMeta meta = DataSaver.INSTANCE.getInfoMeta();
                try {
                    Response response = Api.deleteOne(meta.getPrivateKey(), meta.getUserName(), meta.getProjectName(), profile, kv.getKey().getValueSafe(), meta.getProjectPath());

                    try {
                        if (response.isSuccessful()) {
                            dataReload(profile);
                        } else {
                            makeAlert(" ERROR! ", "删除单条配置失败");
                        }
                    } finally {
                        response.body().close();
                    }
                } catch (IOException e) {
                    makeAlert(" ERROR! " , "删除单条配置失败");
                }
            });
        }

        @Override
        protected void updateItem(Kv kv, boolean empty) {
            super.updateItem(kv, empty);
            if(!empty){
                cellButton.setText("delete");
                cellButton.setFont(Font.font(10));
                cellButton.setMinHeight(20);
                cellButton.setMaxHeight(20);
                cellButton.setPrefHeight(20);
                cellButton.setPrefWidth(80);
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }

    private void dataReload (Integer profile) throws IOException {
        if (profile == 0) {
            prdKvTableView.setItems(this.queryData(profile));
        } else {
            devKvTableView.setItems(this.queryData(profile));
        }
    }
}
