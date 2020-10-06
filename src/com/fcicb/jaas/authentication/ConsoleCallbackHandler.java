package com.fcicb.jaas.authentication;
import com.fcicb.view.sample.*;
import javax.security.auth.callback.*;
import java.io.IOException;


import static com.fcicb.view.sample.Login.userEmailCheck;
import static com.fcicb.view.sample.Login.userPasswordCheck;

public class ConsoleCallbackHandler  implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        Login login =new Login();
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {

                NameCallback nameCallback = (NameCallback) callback;
                //nameCallback.setName(String.valueOf(callbacks[0]));
              nameCallback.setName(userEmailCheck);
             //  nameCallback.setName(String.valueOf(callbacks[0]));

            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callback;
             //  passwordCallback.setPassword("testpassword".toCharArray());
               passwordCallback.setPassword(userPasswordCheck.toCharArray());
          //   passwordCallback.setPassword(login.userPasswordCheck.toCharArray());
        //    passwordCallback.setPassword(String.valueOf(callbacks[1]).toCharArray());

            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }
}
