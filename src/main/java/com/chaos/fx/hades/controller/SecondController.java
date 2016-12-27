package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.DataSaver;
import com.chaos.fx.hades.model.Kv;
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
            System.out.println(DataSaver.INSTANCE.getData("hello"));
        }
        if (prdTab != null && prdTab.isSelected()) {
            System.out.println("this is prd");
        }
    }
}
