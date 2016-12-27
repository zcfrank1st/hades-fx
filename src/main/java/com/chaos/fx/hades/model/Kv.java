package com.chaos.fx.hades.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
@Data
public class Kv {
    private StringProperty key;
    private StringProperty value;

    public Kv() {
        this(null, null);
    }

    public Kv(String key, String value) {
        this.key = new SimpleStringProperty(key);
        this.value = new SimpleStringProperty(value);
    }
}
