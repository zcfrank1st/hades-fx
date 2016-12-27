package com.chaos.fx.hades.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
public enum DataSaver {
    INSTANCE;

    private Map<String, String> mapStore = new HashMap<>();

    public void setData(String key, String value) {
        mapStore.put(key, value);
    }

    public String getData(String key) {
        return mapStore.get(key);
    }
}
