package com.fcicb.view.sample.student;
import com.fcicb.model.service.impl.StudentService;
import com.fcicb.view.sample.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.fcicb.jaas.authentication.InMemoryLoginModule.userPrincipal;
public class NewPassword implements Initializable {
    @FXML  PasswordField password , cofirmPassword ;
    @FXML
    Label errorConfirmation;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorConfirmation.setText("");
    }

    public void confirmNewPassword(ActionEvent actionEvent) throws IOException {
        String passwordvalue , cofirmPasswordvalue ;
        passwordvalue = password.getText();
        cofirmPasswordvalue = cofirmPassword.getText();
        if (cofirmPasswordvalue.equals(passwordvalue))
        {
            StudentService studentService = new StudentService();
            studentService.updatePassword(userPrincipal,cofirmPasswordvalue);
            Login login =new Login();
            login.studentview(actionEvent);
            cofirmPassword.setText("");
        }
        else
        {
            errorConfirmation.setText("not match password");

        }

    }
}
