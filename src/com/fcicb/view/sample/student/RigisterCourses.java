package com.fcicb.view.sample.student;

import com.fcicb.model.service.impl.StudentCourseService;
import com.fcicb.model.service.impl.StudentService;
import com.fcicb.view.sample.Login;
import com.fcicb.view.sample.logout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import static com.fcicb.jaas.authentication.InMemoryLoginModule.userPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class  RigisterCourses implements Initializable , logout {
    @FXML private ComboBox Courses;
    List<String> coursesList = new ArrayList<>();
    List<Integer> coursesIDS = new ArrayList<>();
    @FXML
    ListView listView ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ArrayList<String>
         ArrayList<String> options;
       StudentCourseService studentCourseService = new StudentCourseService();
        options= studentCourseService.showAvailableCourses(studentCourseService.getLevel(userPrincipal));
        Courses.getItems().addAll(options);
       // listView.getItems().addAll("menna", "mariam","mohamed");
       /* for (String s : coursesList ) {


       listView.getItems().add(s);
    }*/
    }




    public void reviewCourses(ActionEvent actionEvent) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("../student/reviewCourses.fxml"));
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
        StudentService studentService = new StudentService();
        studentCourseService.registerCourses((ArrayList<Integer>) coursesIDS,studentService.getId(userPrincipal));
        Set<String> hash_Set
                = new HashSet<>();
        for (String s : coursesList) {
            hash_Set.add(s);
        }
        listView.getItems().clear();
        for (String s : hash_Set) {

            listView.getItems().add(s);
        }
    }

    public void downloadTranscript(ActionEvent actionEvent) {
        StudentService studentService = new StudentService();
        studentService.generateTranscript(studentService.getId(userPrincipal));
    }


    public void student(ActionEvent actionEvent) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("rigisterCourses.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();
    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        Login login= new Login();
        login.logoutevent(event);
    }
}
