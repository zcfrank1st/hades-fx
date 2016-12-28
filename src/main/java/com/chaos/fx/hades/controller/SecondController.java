package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.InfoMeta;
import com.chaos.fx.hades.model.Kv;
import com.chaos.fx.hades.util.Api;
import com.chaos.fx.hades.util.DataSaver;
import com.google.gson.Gson;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
public class SecondController {
    private static final Gson gson = new Gson();

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
    public void deleteAllAction() throws IOException {
        // TODO delete api
        InfoMeta meta = DataSaver.INSTANCE.getInfoMeta();
        Response deleteResponse = Api.deleteAll(meta.getPrivateKey(), meta.getUserName(), meta.getProjectName(), meta.getProjectPath());


    }

    @FXML
    public void addAction() {
        // TODO add api
        System.out.println("add action");
    }

    @FXML
    public void loadData() {
        if (devTab != null && devTab.isSelected()) {
            this.profile = 1;
            devKvTableView.setItems(this.queryData("dev"));
        }
        if (prdTab != null && prdTab.isSelected()) {
            this.profile = 0;
            prdKvTableView.setItems(this.queryData("prd"));
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

    private ObservableList<Kv> queryData (String profile) {
        ObservableList<Kv> lists = FXCollections.observableArrayList();
        if ("dev".equals(profile)) {
            // TODO get dev data
            lists.add(new Kv("1", "3"));
        } else if ("prd".equals(profile)){
            // TODO get prd data
            lists.add(new Kv("hh", "bb"));
        }
        return lists;
    }


    private class ButtonCell extends TableCell<Kv, Kv> {


        private Button cellButton;

        ButtonCell(){
            cellButton = new Button();
            cellButton.setOnAction(t -> {
                Kv kv = getItem();

                System.out.println(kv.getKey());
                System.out.println(kv.getValue());

                // TODO delete one
                // do something with record....
            });
        }

        @Override
        protected void updateItem(Kv kv, boolean empty) {
            super.updateItem(kv, empty);
            if(!empty){
                cellButton.setText("-");
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }
}
