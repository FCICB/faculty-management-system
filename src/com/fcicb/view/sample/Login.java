package com.fcicb.view.sample;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.fcicb.jaas.authentication.ConsoleCallbackHandler;
import com.fcicb.model.service.impl.AdminService;
import com.fcicb.model.service.impl.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import static com.fcicb.jaas.authentication.InMemoryLoginModule.userPrincipal;


public class Login implements Initializable {



    @FXML private TextField userMail ,userPassword;

    public  static String userEmailCheck ,userPasswordCheck;
    @FXML public   CheckBox admin;
    public static boolean adminOrStudent  ;
    Alert a = new Alert(Alert.AlertType.NONE) ;
    Alert notActive = new Alert(Alert.AlertType.NONE) ;
    @FXML Label error_label=new Label("Valid");


    private void validateData(javafx.event.ActionEvent event) throws IOException
    {
        Parent tableview = FXMLLoader.load(getClass().getResource("admin/adminDashBoard.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }
    public void superAdminBoard(javafx.event.ActionEvent event) throws IOException
    {
        Parent tableview = FXMLLoader.load(getClass().getResource("admin/superAdminDashBard.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();

    }
    final   public   boolean adminOrNot()
    {
        if (admin.isSelected())
        {
            adminOrStudent= true;
            return true;
        }
        else
            return false;
    }



    // button action
    public  void loginButton(javafx.event.ActionEvent event) throws IOException {
        userEmailCheck = userMail.getText();
        userPasswordCheck = userPassword.getText();
        boolean test =false;
        //LoginContext
        System.setProperty("java.security.auth.login.config", "resources/jaas.config");
        LoginContext loginContext = null;
        try {
            loginContext = new LoginContext("loadloginmodule", new ConsoleCallbackHandler());

        } catch (LoginException e) {
            e.printStackTrace();
        }
        try
        {
            adminOrStudent = admin.isSelected();
            assert loginContext != null;
            loginContext.login();
            Login login =new Login();
            if (userPassword.getText().equals("P@ssw0rd")){
                StudentService studentService = new StudentService();

                if (studentService.ActiveOrNot(userPrincipal))
                {
                    login.newPassword(event);
                }
                else
                {
                    notActiveStudent();
                }
            }
            else if (adminOrNot())
            {
                AdminService adminService = new AdminService();
                if (adminService.superAdminOrAdmin(userPrincipal))
                    superAdminBoard(event) ;
                else
                login.validateData(event);
            }
            else
            {
                login.studentview(event);
            }



        }
        catch (LoginException e)
        {

            errorInLogin();
        }




    }

    private void newPassword(ActionEvent event) throws IOException
    {

        Parent tableview = FXMLLoader.load(getClass().getResource("../sample/student/newPassword.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();
    }
    public void studentview(ActionEvent event) throws IOException
    {
        Parent tableview = FXMLLoader.load(getClass().getResource("../sample/student/rigisterCourses.fxml"));
        Scene tablescene = new Scene(tableview);
        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setResizable(true);
        windows.setScene(tablescene);

        windows.show();
    }
    private void errorInLogin()
    {
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Invalid UserName or password ");
        a.show()  ;

    }

    private void notActiveStudent()
    {
        notActive.setAlertType(Alert.AlertType.ERROR);
        notActive.setContentText("you should activate your Account ");
        notActive.show()  ;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.error_label.setText(" ");


    }

    public String getTestUser(){
        return  userEmailCheck;
    }
}
