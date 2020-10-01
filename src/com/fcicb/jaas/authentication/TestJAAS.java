package com.fcicb.jaas.authentication;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;

public class TestJAAS {
    public static void main(String[] args) {

        System.setProperty("java.security.auth.login.config","resources/jaas.config");
        LoginContext loginContext = null;

        try {
            loginContext = new LoginContext("loadloginmodule", new ConsoleCallbackHandler());

        }
        catch (LoginException e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            try {
                assert loginContext != null;
                loginContext.login();
                System.out.println("success authentication");
                Subject subject = loginContext.getSubject();
                for (Principal principal : subject.getPrincipals()) {
                    System.out.println(principal);
                }

                System.exit(0);

            }
            catch (LoginException e)
            {
                e.printStackTrace();
            }
        }
    }
}
