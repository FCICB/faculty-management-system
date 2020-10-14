package com.fcicb.view.sample.superAdmin;

import com.fcicb.dataValidation.addAdminValidation;
import com.fcicb.domain.Admin;
import com.fcicb.model.service.impl.AdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminRegister implements Initializable {
    boolean first =false, last=false , user=false ,pass=false , emailboolean=false;

    @FXML TextField  firstName, lastName, userName, email;
    @FXML PasswordField password;
    @FXML Label firstvalidation , lastvalidation ,uservalidation ,passwordvalidation ,emailvalidation ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Register(ActionEvent actionEvent) {

     if(first && last && user && pass && emailboolean ){

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
    }
     else{
         JOptionPane.showMessageDialog(null,"Failed to register");
     }
    }

    public void textFieldIsNullfirst(MouseEvent mouseEvent) {

      first=  addAdminValidation.textFieldIsNull(firstName, firstvalidation, "First name required");

    }
    public void textFieldIsNulllast(MouseEvent mouseEvent) {
       last= addAdminValidation.textFieldIsNull(lastName, lastvalidation, "last name required");
    }
    public void textFieldIsNulluser(MouseEvent mouseEvent) {
      user=  addAdminValidation.textFieldIsNull(userName, uservalidation, "user name required");
    }
    public void textFieldIsNullpassword(MouseEvent mouseEvent) {
        pass = addAdminValidation.textFieldIsNull(password, passwordvalidation, "password name required");

    }
    public void textFieldIsNullemail(MouseEvent mouseEvent) {
     emailboolean = addAdminValidation.textFieldIsNull(email, emailvalidation, "email name required");
        }


    public void fnameValidation(MouseEvent mouseEvent) {
        addAdminValidation.firstNameValidation(firstName,firstvalidation,"please only " +
                "enters letters from a-z / A-Z");
    }
    public void lnameValidation(MouseEvent mouseEvent) {
        addAdminValidation.secondNameValidation(lastName,lastvalidation,"please only " +
                "enters letters from a-z / A-Z");
    }

    public void userValidation(MouseEvent mouseEvent) {
       addAdminValidation.userValidation(userName, uservalidation, "please only " +
                "enters letters from a-z / A-Z with out spaces");
    }

    public void passwordValidation(MouseEvent mouseEvent) {
      addAdminValidation.passwordValidation(password, passwordvalidation, "your password (from 8 to 20 character) must contain at" +
                " least one lower case , one upper case" +
                ", one digit , one special character and no spaces");
    }

    public void emailValidation(MouseEvent mouseEvent) {
       addAdminValidation.emailValidation(email, emailvalidation, "Format must be name@emailaddress.com");
    }
}
