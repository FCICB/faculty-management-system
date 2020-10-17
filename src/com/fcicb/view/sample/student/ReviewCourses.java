package com.fcicb.view.sample.student;

import com.fcicb.domain.StudentCourse;
import com.fcicb.model.service.impl.StudentCourseService;
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

import java.net.URL;
import java.util.ResourceBundle;
import static com.fcicb.jaas.authentication.InMemoryLoginModule.userPrincipal;
public class ReviewCourses implements Initializable {

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

    public void student(ActionEvent actionEvent) {
    }

    public void reviewCourses(ActionEvent actionEvent) {
    }

    public void DownloadTranscript(ActionEvent actionEvent) {
    }
}
