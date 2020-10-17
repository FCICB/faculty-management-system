package com.fcicb.view.sample.student;

import com.fcicb.domain.StudentCourse;
import com.fcicb.model.service.impl.StudentCourseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;
import static com.fcicb.jaas.authentication.InMemoryLoginModule.userPrincipal;
public class ReviewCourses implements Initializable {
    @FXML
    TableView<StudentCourse> tableview;
    @FXML
    TableColumn<StudentCourse, Integer> grade;
    @FXML
    TableColumn<StudentCourse, String> code;
    @FXML
    TableColumn<StudentCourse, String> name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StudentCourseService studentCourseService = new StudentCourseService();
        int ids = studentCourseService.getId(userPrincipal);
        ObservableList<StudentCourse> allStudents;
        allStudents =FXCollections.observableArrayList(studentCourseService.reviewPassCourses(ids) );
        System.out.println(grade);
       code.setCellValueFactory(new PropertyValueFactory(("code")));
       name.setCellValueFactory(new PropertyValueFactory<StudentCourse, String>("name"));
        grade.setCellValueFactory(new PropertyValueFactory<StudentCourse, Integer>(("grade")));
        this.code.setCellFactory(TextFieldTableCell.forTableColumn());
        this.name.setCellFactory(TextFieldTableCell.forTableColumn());
      tableview.setItems(allStudents);

    }
}
