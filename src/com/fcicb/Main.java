package com.fcicb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

  Parent root = FXMLLoader.load(getClass().getResource("../../sample/login.fxml"));

        primaryStage.setScene(new Scene(root ));
        primaryStage.setTitle("FMS");
        primaryStage.setResizable(false);
        primaryStage.show();


    }
    public static void main(String[] args) {
        // write your code here
        launch(args);
    }
}
