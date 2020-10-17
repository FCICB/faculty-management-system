package com.fcicb.view.sample.admin;

import com.fcicb.model.service.impl.*;
import com.fcicb.domain.Student;
import com.fcicb.view.sample.Login;
import com.fcicb.view.sample.logout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


public class AdminDashBoard  implements Initializable ,admin , logout {

    @FXML
    private javafx.scene.control.ComboBox comboxicon;
    @FXML  Label invalid;
    @FXML
    TextField id, userName, firstName, lastName, password, email;
    @FXML
    DatePicker datePicker;
    @FXML
    TableView<Student> tableview;
    @FXML
    TableColumn<Student, String> userNameTableColumn;
    @FXML
    TableColumn<Student, String> firstNameTableColumn;
    @FXML
    TableColumn<Student, String> lastNameTableColumn;
    @FXML
    TableColumn<Student, String> passwordTableColumn;
    @FXML
    TableColumn<Student, String> emailTableColumn;
    @FXML
    TableColumn<Student, Integer> idTableColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboxicon.getItems().addAll("1", "2", "3", "4");
        comboxicon.setValue("1");

        password.setText("P@ssw0rd");
        password.setDisable(false);
        password.setEditable(false);

        StudentService studentService = new StudentService();
        ObservableList<Student> allStudents = FXCollections.observableArrayList(studentService.getAllStudents());

        idTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        userNameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("username"));
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("fname"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lname"));
        passwordTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("password"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

        tableview.setItems(allStudents);
    }

    public void addNewCourse(javafx.event.ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("addNewCourse.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void deactivateStudent(javafx.event.ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("reactivate.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void addGrades(javafx.event.ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("addGrade.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }


    public void dashBoard(MouseEvent mouseEvent) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("superAdminDashBard.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void showStudents(MouseEvent mouseEvent) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("showALLStudents.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void showALLStudents(javafx.event.ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("showALLStudents.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    private void addStudent()
    {
        String idValue, userNameValue, firstNameValue, lastNameValue, passwordValue, level, emailValue;
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        userNameValue = userName.getText();
        firstNameValue = firstName.getText();
        lastNameValue = lastName.getText();
        emailValue = email.getText();

        level = comboxicon.getValue().toString();
        Date date;
        // date=new java.sql.Date( 2008 ,11,11);
        date = java.sql.Date.valueOf(datePicker.getValue());

        if (  !userNameValue.isEmpty() && !firstNameValue.isEmpty() && !lastNameValue.isEmpty()  && !level.isEmpty() && !emailValue.isEmpty()&&(email.getText()).matches(pattern)) {
            Student student = new Student( userNameValue, 0, date, Student.studentState.DEACTIVATED, Integer.parseInt(level), 0, firstNameValue, lastNameValue, emailValue, password.getText(), -1, 2021);

            StudentService studentService = new StudentService();
            studentService.add(student);
            invalid.setText("");
            userName.setText("");
            firstName.setText("");
            password.setText("");
            email.setText("");
            lastName.setText("");
            datePicker.setValue(null);
        } else {
            invalid.setText("invalid Email format");
        }



    }

    public void addNewStudent(ActionEvent actionEvent)
    {
        addStudent();

    }


    public void delete(ActionEvent actionEvent)
    {
        ObservableList<Student> allStudents, selectedStudent;
        // all persons
        allStudents = tableview.getItems();
        // what i want to delete
        selectedStudent = tableview.getSelectionModel().getSelectedItems();

        for (Student student : selectedStudent)
        {
            allStudents.remove(student);
            StudentService studentService = new StudentService();
            studentService.delete(student);
        }

    }

    public void search(ActionEvent actionEvent) {
    }


    @Override
    public void logout(ActionEvent event) throws IOException {
        Login login= new Login();
        login.logoutevent(event);
    }
}