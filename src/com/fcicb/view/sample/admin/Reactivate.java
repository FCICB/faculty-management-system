package com.fcicb.view.sample.admin;

import com.fcicb.domain.Student;
import com.fcicb.model.service.impl.StudentService;
import com.fcicb.view.sample.Login;
import com.fcicb.view.sample.logout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Reactivate implements Initializable , admin , logout {
    @FXML  TableView<Student> tableview;
    @FXML  private TableColumn<Student, Integer> IDs;
    @FXML  private TableColumn<Student, String> userName;
    private final AdminDashBoard admin =new AdminDashBoard();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StudentService studentService = new StudentService();
        ObservableList<Student> allStudents=  FXCollections.observableArrayList( studentService.getAll());

        IDs.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        userName.setCellValueFactory(new PropertyValueFactory<Student, String>("username"));

        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableview.setItems(allStudents);
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

    public void reactivate(ActionEvent actionEvent)
    {
        ObservableList<Student> allStudentsInTableView, selectedStudents;

        allStudentsInTableView = tableview.getItems();

        selectedStudents = tableview.getSelectionModel().getSelectedItems();
        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        StudentService studentService = new StudentService();
        for (Student student : selectedStudents) {
            allStudentsInTableView.remove(student);
            studentService.reactivated(student);

        }
        tableview.setItems(allStudentsInTableView);
    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        Login login= new Login();
        login.logoutevent(event);
    }
}
