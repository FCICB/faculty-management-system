package com.fcicb.view.sample.admin;
import com.fcicb.domain.*;
import com.fcicb.model.service.impl.CourseService;
import com.fcicb.view.sample.Login;
import com.fcicb.view.sample.logout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class AddNewCourse implements Initializable ,admin, logout {

    private final AdminDashBoard admin =new AdminDashBoard();
    @FXML private javafx.scene.control.ComboBox comboxHours;
    @FXML private javafx.scene.control.ComboBox comboxlevels;
    @FXML private TextField courseCode,courseName ,addedBy ;
    @FXML private TextArea description;
    @FXML Label invalidinput;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboxHours.getItems().addAll("2", "3");
        comboxHours.setValue("3");

        comboxlevels.getItems().addAll("1","2", "3","4");
        comboxlevels.setValue("1");
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


    public void showAllcourses(ActionEvent actionEvent) throws IOException {

        Parent tableview = FXMLLoader.load(getClass().getResource("showALLCourses.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);
     /*   windows.setFullScreen(true);
       windows.setMaximized(true);*/

        windows.show();

    }

    public void addNewCourses(ActionEvent actionEvent) {
        String courseCodeValue, courseNameValue , addedByValue  , comboxHoursValue , comboxlevelsValue , descriptionValue;

        courseCodeValue =  courseCode.getText();
        courseNameValue =  courseName.getText();
        comboxHoursValue  =comboxHours.getValue().toString();
        comboxlevelsValue =comboxlevels.getValue().toString();
        addedByValue = addedBy.getText();
        descriptionValue = description.getText();

        if (!courseCodeValue.isEmpty()&&!courseNameValue.isEmpty()&&!comboxHoursValue.isEmpty()&&!comboxlevelsValue.isEmpty()&&!addedByValue.isEmpty()&&!descriptionValue.isEmpty())
        {
            final Course  course=new Course(courseCodeValue,courseNameValue,Integer.parseInt(comboxHoursValue),Integer.parseInt(comboxlevelsValue),descriptionValue,Integer.parseInt(addedByValue));
            CourseService courseService =new CourseService();
            courseService.add(course);

            invalidinput.setText("");
        }
        else
        {
            invalidinput.setText("Invalid input");
        }
        courseCode.setText("");
        courseName.setText("");
        addedBy.setText("");
        description.setText("");



    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        Login login= new Login();
        login.logoutevent(event);
    }
}
