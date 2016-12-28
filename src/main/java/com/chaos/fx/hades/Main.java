package com.chaos.fx.hades;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by zcfrank1st on 27/12/2016.
 */
public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("first.fxml"));

        primaryStage.setTitle("Hades Fx");
        primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("hades.png").toExternalForm()));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
