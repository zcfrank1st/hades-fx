package com.chaos.fx.hades.util;

import com.chaos.fx.hades.model.InfoMeta;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
public enum DataSaver {
    INSTANCE;

    public static final String USER_NAME = "userName";
    public static final String PRIVATE_KEY = "privateKey";
    public static final String PROJECT_NAME = "projectName";
    public static final String PROJECT_PATH = "projectPath";

    private Map<String, String> mapStore = new HashMap<>();

    public void setData(String key, String value) {
        mapStore.put(key, value);
    }

    public String getData(String key) {
        return mapStore.get(key);
    }

    public InfoMeta getInfoMeta () {
        InfoMeta meta = new InfoMeta();
        meta.setPrivateKey(DataSaver.INSTANCE.getData(PRIVATE_KEY));
        meta.setProjectName(DataSaver.INSTANCE.getData(PROJECT_NAME));
        meta.setUserName(DataSaver.INSTANCE.getData(USER_NAME));
        meta.setProjectPath(DataSaver.INSTANCE.getData(PROJECT_PATH));
        return meta;
    }
}
