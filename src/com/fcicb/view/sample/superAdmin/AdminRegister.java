package com.fcicb.view.sample.superAdmin;

import com.fcicb.domain.Admin;
import com.fcicb.model.service.impl.AdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminRegister implements Initializable {

    @FXML TextField ID, firstName, lastName, userName, email;
    @FXML PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Register(ActionEvent actionEvent) {
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
}
