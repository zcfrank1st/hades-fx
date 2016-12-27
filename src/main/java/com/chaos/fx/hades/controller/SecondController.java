package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.Kv;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
public class SecondController {
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

    @FXML
    public void deleteAllAction() {
        // TODO delete api
        System.out.println("delete all");
    }

    @FXML
    public void addAction() {
        // TODO add api
        System.out.println("add action");
    }

    @FXML
    public void loadData() {
        if (devTab != null && devTab.isSelected()) {
            System.out.println("this is dev");
            devKvTableView.setItems(this.queryData("dev"));
        }
        if (prdTab != null && prdTab.isSelected()) {
            System.out.println("this is prd");
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
            // TODO get data api
            lists.add(new Kv("1", "3"));
        } else if ("prd".equals(profile)){
            // TODO
            lists.add(new Kv("hh", "bb"));
        }
        return lists;
    }


    private class ButtonCell extends TableCell<Kv, Kv> {


        private Button cellButton;

        ButtonCell(){
            cellButton = new Button();
            cellButton.setOnAction(t -> {
                // do something when button clicked
                Kv kv = getItem();

                System.out.println(kv.getKey());
                System.out.println(kv.getValue());
                // do something with record....
            });
        }

        //Display button if the row is not empty
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
