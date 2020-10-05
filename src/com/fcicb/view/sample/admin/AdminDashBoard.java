package com.fcicb.view.sample.admin;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashBoard  implements Initializable ,admin {
    //@FXML private TableView<> tableview;
   // @FXML private TableView tableview;

    @FXML private javafx.scene.control.ComboBox comboxicon;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboxicon.getItems().addAll("1","2", "3","4");
        comboxicon.setValue("1");
    }

    public void addNewCourse ( javafx.event.ActionEvent event) throws IOException
    {
        Parent tableview = FXMLLoader.load(getClass().getResource("addNewCourse.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void deactivateStudent(javafx.event.ActionEvent event) throws IOException
    {
        Parent tableview = FXMLLoader.load(getClass().getResource("adminDashBoard.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void addGrades(javafx.event.ActionEvent event) throws IOException
    {
        Parent tableview = FXMLLoader.load(getClass().getResource("adminDashBoard.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }




    public void dashBoard(MouseEvent mouseEvent) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("adminDashBoard.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void showStudents(MouseEvent mouseEvent) throws IOException
    {
        Parent tableview = FXMLLoader.load(getClass().getResource("showALLStudents.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void showALLStudents(javafx.event.ActionEvent event) throws IOException
    {
        Parent tableview = FXMLLoader.load(getClass().getResource("showALLStudents.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

}
