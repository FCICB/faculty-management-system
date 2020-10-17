package com.fcicb.view.sample.student;

import com.fcicb.view.sample.Login;
import com.fcicb.view.sample.logout;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentView implements Initializable , logout {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @Override
    public void logout(ActionEvent event) throws IOException {
        Login login= new Login();
        login.logoutevent(event);
    }
}
