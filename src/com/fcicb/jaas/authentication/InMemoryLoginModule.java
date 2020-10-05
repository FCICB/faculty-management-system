package com.fcicb.jaas.authentication;

import com.fcicb.jdbc.DatabaseConnection;
import com.sun.security.auth.UserPrincipal;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class InMemoryLoginModule implements LoginModule {
    DatabaseConnection instance = DatabaseConnection.getInstance();


    private Subject subject;
    public Principal userPrincipal;
    private CallbackHandler callbackHandler;
    private boolean loginSucceeded = false;



    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
       this.subject =subject;
       this.callbackHandler=callbackHandler;
    }

    @Override
    public boolean login()  {
        NameCallback nameCallback = new NameCallback("username: ");
        PasswordCallback passwordCallback = new PasswordCallback("password: ", false);
        String username;
        String password;
        PreparedStatement list =null ;
        ResultSet rst = null;
        try {
            Connection connection = instance.getConnection();
            list = connection.prepareStatement("SELECT username , password  FROM admin");
            rst = list.executeQuery();

            while(rst.next())
            {
                 callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
             username = nameCallback.getName();
             password =  new String(passwordCallback.getPassword());
            if (rst.getString(1).equals(username) && rst.getString(2).equals(password)) {
                loginSucceeded = true;
                userPrincipal = new UserPrincipal(username);
                subject.getPrincipals().add(userPrincipal);
            }



            }
        } catch (IOException | UnsupportedCallbackException | SQLException e) {
            e.printStackTrace();
        }

        return loginSucceeded;
    }

    @Override
    public boolean commit() {
         return loginSucceeded;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
