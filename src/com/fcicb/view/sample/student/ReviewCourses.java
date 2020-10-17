package com.fcicb.view.sample.student;

import com.fcicb.domain.StudentCourse;
import com.fcicb.model.service.impl.StudentCourseService;
import com.fcicb.model.service.impl.StudentService;
import com.fcicb.view.sample.Login;
import com.fcicb.view.sample.logout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.fcicb.jaas.authentication.InMemoryLoginModule.userPrincipal;
public class ReviewCourses implements Initializable , logout {

    @FXML
    ListView grade;
    @FXML
    ListView code;
    @FXML
    ListView name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StudentCourseService studentCourseService = new StudentCourseService();
        int ids = studentCourseService.getId(userPrincipal);
        ObservableList<StudentCourse> allStudents;
        allStudents =FXCollections.observableArrayList(studentCourseService.reviewPassCourses(ids) );

        for (StudentCourse s : allStudents) {

            name.getItems().add(s.getCourseName());
            code.getItems().add(s.getCourseCode());
            grade.getItems().add(s.getGrade());

        }


    }

    public void showStudents(MouseEvent mouseEvent) {

    }

    public void student(ActionEvent actionEvent) throws IOException {
        RigisterCourses rigisterCourses =new RigisterCourses();
        rigisterCourses.student(actionEvent);
    }

    public void reviewCourses(ActionEvent actionEvent) throws IOException {
        RigisterCourses rigisterCourses =new RigisterCourses();
        rigisterCourses.reviewCourses(actionEvent);
    }

    public void DownloadTranscript(ActionEvent actionEvent) {
        StudentService studentService = new StudentService();
        studentService.generateTranscript(studentService.getId(userPrincipal));
    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        Login login= new Login();
        login.logoutevent(event);
    }
}
