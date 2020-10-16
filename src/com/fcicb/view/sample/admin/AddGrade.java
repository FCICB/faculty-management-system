package com.fcicb.view.sample.admin;

import com.fcicb.domain.Course;
import com.fcicb.domain.Student;
import com.fcicb.domain.StudentCourse;
import com.fcicb.model.service.impl.StudentCourseService;
import com.fcicb.model.service.impl.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddGrade implements Initializable , admin{
    @FXML
    TextField courseID,studentId,grade;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private final AdminDashBoard admin =new AdminDashBoard();
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

    public void addGrade(ActionEvent actionEvent) {
        int courseIDValue,studentIdValue;
        Float gradeValue;
        courseIDValue = Integer.parseInt(courseID.getText());
        studentIdValue = Integer.parseInt(studentId.getText());
        gradeValue =  Float.parseFloat(grade.getText());
        StudentCourseService studentCourseService =new StudentCourseService();
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setGrade(gradeValue);
        Course course = new Course();
        course.setId(courseIDValue);
        studentCourse.setCourse(course);
        Student std = new Student();
        std.setId(studentIdValue);
        studentCourse.setStudent(std);
        studentCourseService.update(studentCourse);


    }
}
