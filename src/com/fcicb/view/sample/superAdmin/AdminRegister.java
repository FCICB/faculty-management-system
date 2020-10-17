package com.fcicb.view.sample.superAdmin;

import com.fcicb.domain.Admin;
import com.fcicb.model.service.impl.AdminService;
import com.fcicb.validation.DataValidation;
import com.fcicb.view.sample.Login;
import com.fcicb.view.sample.logout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminRegister implements Initializable , logout {
    boolean first =false, last=false , user=false ,pass=false , emailboolean=false;
    boolean first2 =false, last2=false , user2=false ,pass2=false , emailboolean2=false;

    @FXML TextField  firstName, lastName, userName, email;
    @FXML PasswordField password;
    @FXML Label firstvalidation , lastvalidation ,uservalidation ,passwordvalidation ,emailvalidation ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void textFieldIsNullfirst(MouseEvent mouseEvent) {

        first=  DataValidation.textFieldIsNull(firstName, firstvalidation, "First name required");

    }
    public void textFieldIsNulllast(MouseEvent mouseEvent) {
        last= DataValidation.textFieldIsNull(lastName, lastvalidation, "last name required");
    }
    public void textFieldIsNulluser(MouseEvent mouseEvent) {
        user=  DataValidation.textFieldIsNull(userName, uservalidation, "user name required");
    }
    public void textFieldIsNullpassword(MouseEvent mouseEvent) {
        pass = DataValidation.textFieldIsNull(password, passwordvalidation, "password name required");

    }
    public void textFieldIsNullemail(MouseEvent mouseEvent) {
        emailboolean = DataValidation.textFieldIsNull(email, emailvalidation, "email name required");
    }


    public void fnameValidation(MouseEvent mouseEvent) {
       first2= DataValidation.nameValidation(firstName,firstvalidation,"start with capital letter");
    }
    public void lnameValidation(MouseEvent mouseEvent) {
       last2= DataValidation.nameValidation(lastName,lastvalidation,"start with capital letter");
    }

    public void userValidation(MouseEvent mouseEvent) {
       user2= DataValidation.userValidation(userName, uservalidation, "Length >=3 Valid characters: a-z, A-Z\n" +
               ", 0-9, points, dashes and underscores.");
    }

    public void passwordValidation(MouseEvent mouseEvent) {
      pass2=  DataValidation.passwordValidation(password, passwordvalidation, "(8-20) Valid : at least one (upper,lower,numbers) \n " +
              "No space.");
    }

    public void emailValidation(MouseEvent mouseEvent) {
      emailboolean2=  DataValidation.emailValidation(email, emailvalidation, "Format must be name@emailaddress.com");
    }
    public void Register(ActionEvent actionEvent) {

     if(first2 && last2 && user2 && pass2 && emailboolean2 ){

        String  firstNameValue, lastNameValue, userNameValue, emailValue, passwordValue;
        firstNameValue = firstName.getText();
        lastNameValue = lastName.getText();
        userNameValue = userName.getText();
        emailValue = email.getText();
        passwordValue = password.getText();

        if (!firstNameValue.isEmpty() && !lastNameValue.isEmpty()
                && !userNameValue.isEmpty() && !emailValue.isEmpty() && !passwordValue.isEmpty()) {
            final Admin admin = new Admin(firstNameValue, lastNameValue, userNameValue
                    , emailValue, passwordValue);
            AdminService adminService = new AdminService();
            adminService.add(admin);

            firstName.setText("");
            lastName.setText("");
            userName.setText("");
            email.setText("");
            password.setText("");

        }
    }else{

         Alert a = new Alert(Alert.AlertType.NONE);
         a.setAlertType(Alert.AlertType.ERROR);
         a.setContentText("Invalid Data ");
         a.show()  ;
     }


    }


    public void back(ActionEvent actionEvent) throws IOException {
        Login login = new Login();
        login.superAdminBoard(actionEvent);
    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        Login login= new Login();
        login.logoutevent(event);
    }
}
