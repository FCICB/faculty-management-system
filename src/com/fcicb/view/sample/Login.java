package com.fcicb.view.sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.fcicb.jaas.authentication.ConsoleCallbackHandler;
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


public class Login implements Initializable {



       @FXML private TextField userMail ,userPassword;

       public  static String userEmailCheck ,userPasswordCheck;
       @FXML private CheckBox admin;
       Alert a = new Alert(Alert.AlertType.NONE) ;
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

      private  boolean adminOrNot()
      {
          if (admin.isSelected())
              return true;
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
             assert loginContext != null;
             loginContext.login();
             Login login =new Login();
             if (adminOrNot())
             {
                 login.validateData(event);
             }
             else
             {
                 errorInLogin();
             }


         }
         catch (LoginException e)
         {

             errorInLogin();
         }




 }
    private void errorInLogin()
    {
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("Invalid Email or password ");
        a.show()  ;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.error_label.setText(" ");

    }

    public String getTestUser(){
          return  userEmailCheck;
    }
}
