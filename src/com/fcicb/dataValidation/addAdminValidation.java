package com.fcicb.dataValidation;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class addAdminValidation {

    public static boolean textFieldIsNull(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;

        if (inputTextField.getText().isEmpty()) {
            isNull = true;
            validationString = validationText;
        }
        inputLabel.setText(validationString);
        return isNull;
    }

        public static boolean nameValidation(TextField inputTextField, Label inputLabel, String validationText) {
            boolean isAlphabet = true;
            String validationString = null;

            if (!inputTextField.getText().matches("[a-z A-Z]+")) {
                isAlphabet = false;
                validationString = validationText;

            }
            inputLabel.setText(validationString);

            System.out.println(inputTextField.getText().matches("[a-z A-Z]"));
            return isAlphabet;

        }

    public static boolean userValidation(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isAlphabetWithoutSpace = true;
        String validationString = null;

        if (!inputTextField.getText().matches("(([a-z A-Z])((?=\\S+$).{8,20}))")) {
            isAlphabetWithoutSpace = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("(([a-z A-Z])((?=\\S+$)))"));
        return isAlphabetWithoutSpace;

    }

    public static boolean emailValidation(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isEmailFormat = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
            isEmailFormat = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com"));
        return isEmailFormat;

    }

    public static boolean passwordValidation(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isPasswordFormat = true;
        String validationString = null;

        if (!inputTextField.getText().matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20})")) {
            isPasswordFormat = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20})"));
        return isPasswordFormat;

    }

    }

