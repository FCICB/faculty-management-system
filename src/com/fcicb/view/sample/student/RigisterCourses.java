package com.fcicb.view.sample.student;

import com.fcicb.model.service.impl.StudentCourseService;
import com.fcicb.model.service.impl.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import static com.fcicb.jaas.authentication.InMemoryLoginModule.userPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class  RigisterCourses implements Initializable {
    @FXML private ComboBox Courses;
    List<String> coursesList = new ArrayList<>();
    List<Integer> coursesIDS = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ArrayList<String>
         ArrayList<String> options;
       StudentCourseService studentCourseService = new StudentCourseService();
        options= studentCourseService.showAvailableCourses(studentCourseService.getLevel(userPrincipal));

       Courses.getItems().addAll(options);
    }




    public void reviewCourses(ActionEvent actionEvent) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("reviewCourses.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }

    public void addCourses(ActionEvent actionEvent) {

        StudentCourseService studentCourseService = new StudentCourseService();
        coursesList.add(Courses.getValue().toString());
        for (String s : coursesList )
        {
            coursesIDS.add(studentCourseService.getCourseId(s));
       int x= (studentCourseService.getCourseId(s));
          System.out.println(x);
        }



    }

    public void register(ActionEvent actionEvent) {
        StudentCourseService studentCourseService = new StudentCourseService();
        studentCourseService.registerCourses((ArrayList<Integer>) coursesIDS,36);
    }

    public void downloadTranscript(ActionEvent actionEvent) {
        StudentService studentService = new StudentService();
        studentService.generateTranscript(studentService.getId(userPrincipal));
    }



}
