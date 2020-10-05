package com.fcicb.view.sample.admin;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public interface admin {
    public void addNewCourse ( javafx.event.ActionEvent event)throws IOException;
    public void deactivateStudent(javafx.event.ActionEvent event)throws IOException;
    public void addGrades(javafx.event.ActionEvent event)throws IOException;
    public void dashBoard(MouseEvent mouseEvent) throws IOException;
    public void showALLStudents(javafx.event.ActionEvent event)throws IOException;
}
