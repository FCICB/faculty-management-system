package com.fcicb.view.sample.admin;

import com.fcicb.model.service.impl.CourseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.fcicb.domain.*;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowALLCourses implements Initializable {
    @FXML
    private javafx.scene.control.TableView<Course> tableview;

    @FXML  private TableColumn<Course, String> code;
    @FXML  private TableColumn<Course, String> name;
    @FXML  private TableColumn<Course, Integer> hours;
    @FXML  private TableColumn<Course, Integer> level;
    @FXML  private TableColumn<Course, String> description;
    @FXML  private TableColumn<Course, Integer> added;
  @FXML  TextField nameUpdate ,hoursUpdate,levelUpdate,descriptionUpdate;


    private final AdminDashBoard admin =new AdminDashBoard();
    public void dashBoard(MouseEvent mouseEvent) throws IOException {
        admin.dashBoard(mouseEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        CourseService courseService = new CourseService();
        ObservableList<Course>allCourses =  FXCollections.observableArrayList( courseService.getAll());

        code.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
        name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        hours.setCellValueFactory(new PropertyValueFactory<Course, Integer>("hours"));
        level.setCellValueFactory(new PropertyValueFactory<Course, Integer>("level"));
        description.setCellValueFactory(new PropertyValueFactory<Course, String>("description"));
        //added.setCellValueFactory(new PropertyValueFactory<Course, Integer>("added"));
        tableview.setItems(allCourses);


    }

    public void Back(ActionEvent actionEvent) throws IOException {

        Parent tableview = FXMLLoader.load(getClass().getResource("addNewCourse.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();


    }
    Course course = new Course();
    public void update(ActionEvent actionEvent) {
        CourseService courseService =new CourseService();

        Course course2 = new Course(course.getCode(), nameUpdate.getText(), Integer.parseInt(hoursUpdate.getText()),Integer.parseInt(levelUpdate.getText()),descriptionUpdate.getText(),course.getAddedBy());
        courseService.update(course2);
    }

    public void selected(MouseEvent mouseEvent) {

      course = tableview.getSelectionModel().getSelectedItem();
        nameUpdate.setText(course.getName());
        hoursUpdate.setText(String.valueOf(course.getHours()));
        levelUpdate.setText(String.valueOf(course.getLevel()));
        descriptionUpdate.setText(course.getDescription());
    }
}
