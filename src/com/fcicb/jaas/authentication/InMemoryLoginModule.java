package com.fcicb.jaas.authentication;

import com.sun.security.auth.UserPrincipal;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

public class InMemoryLoginModule implements LoginModule {

    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "testpassword";

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
        try {
            callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
            String username = nameCallback.getName();
            String password =  new String(passwordCallback.getPassword());
            if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                loginSucceeded = true;
                userPrincipal = new UserPrincipal(username);
                subject.getPrincipals().add(userPrincipal);
            }
        } catch (IOException | UnsupportedCallbackException e) {
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
