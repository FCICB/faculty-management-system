package com.fcicb.view.sample.admin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowALLStudents implements Initializable ,admin{
    private final AdminDashBoard admin =new AdminDashBoard();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//    added_by

    }

    public void showStudents(MouseEvent mouseEvent) throws IOException {
        admin.showStudents(mouseEvent);
    }

    public void dashBoard(MouseEvent mouseEvent) throws IOException {
        admin.dashBoard(mouseEvent);
    }

    public void addNewCourse(ActionEvent actionEvent) throws IOException {
        admin.addNewCourse(actionEvent);
    }

    public void deactivateStudent(ActionEvent actionEvent) throws IOException {
        admin.deactivateStudent(actionEvent);
    }

    public void addGrades(ActionEvent actionEvent) throws IOException {
        admin.addGrades(actionEvent);
    }

    public void showALLStudents(ActionEvent actionEvent) throws IOException {
        admin.showALLStudents(actionEvent);
    }
}
