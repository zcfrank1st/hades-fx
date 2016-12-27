package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.Kv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableView<Kv> kvTableView;
    @FXML
    private TableColumn<Kv, String> keyColumn;
    @FXML
    private TableColumn<Kv, String> valueColumn;

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
            kvTableView.setItems(this.queryData("dev"));
        }
        if (prdTab != null && prdTab.isSelected()) {
            System.out.println("this is prd");
            kvTableView.setItems(this.queryData("prd"));
        }
    }

    private ObservableList<Kv> queryData(String profile) {
        ObservableList<Kv> kvObservableList = FXCollections.observableArrayList();
        kvObservableList.add(new Kv("key1", "value1"));
        kvObservableList.add(new Kv("key2", "value2"));
        kvObservableList.add(new Kv("key3", "value3"));
        kvObservableList.add(new Kv("key4", "value4"));
        kvObservableList.add(new Kv("key5", "value5"));
        kvObservableList.add(new Kv("key6", "value6"));
        kvObservableList.add(new Kv("key7", "value7"));
        kvObservableList.add(new Kv("key8", "value8"));
        return kvObservableList;
    }
}