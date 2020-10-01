package com.fcicb.jaas.authentication;

import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCallbackHandler  implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                NameCallback nameCallback = (NameCallback) callback;
                nameCallback.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                passwordCallback.setPassword((new BufferedReader(new InputStreamReader(System.in))).readLine().toCharArray());

            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }
}
