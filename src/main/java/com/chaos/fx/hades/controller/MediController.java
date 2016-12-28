package com.chaos.fx.hades.controller;

import com.chaos.fx.hades.model.InfoMeta;
import com.chaos.fx.hades.util.Api;
import com.chaos.fx.hades.util.DataSaver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import okhttp3.Response;

/**
 * Created by zcfrank1st on 28/12/2016.
 */
public class MediController {
    @FXML
    private Button createBtn;


    @FXML
    public void createAction () {
        InfoMeta meta = DataSaver.INSTANCE.getInfoMeta();
        Response createResponse = Api.init(meta.getPrivateKey(), meta.getUserName(), meta.getProjectName());
    }
}
