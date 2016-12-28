package com.chaos.fx.hades.util;

import javafx.scene.control.Alert;

/**
 * Created by zcfrank1st on 28/12/2016.
 */
public class Dialog {

    public static void makeAlert (String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
