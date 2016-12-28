package com.chaos.fx.hades;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by zcfrank1st on 28/12/2016.
 */
public class SplashScreenLoader extends Preloader {
    private ProgressBar bar;
    private Stage stage;

    public void start(Stage stage) throws Exception {
        this.stage = stage;

        bar = new ProgressBar();
        bar.setPrefWidth(600);
        bar.setPrefHeight(10);
        BorderPane p = new BorderPane();
        p.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResourceAsStream("hades.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        p.setBottom(bar);
        Scene scene = new Scene(p, 600, 400);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        bar.setProgress(pn.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

}
