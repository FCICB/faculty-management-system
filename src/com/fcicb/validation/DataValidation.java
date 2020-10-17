package com.fcicb.validation;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class DataValidation {

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

        if (!inputTextField.getText().matches("[A-Z][a-z]*")) {
            isAlphabet = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isAlphabet;

    }

    public static boolean userValidation(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isAlphabetWithoutSpace = true;
        String validationString = null;

        if (!inputTextField.getText().matches("^[a-zA-Z0-9._-]{3,}$")) {
            isAlphabetWithoutSpace = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isAlphabetWithoutSpace;

    }

    public static boolean emailValidation(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isEmailFormat = true;
        String validationString = null;

        if (!inputTextField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            isEmailFormat = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isEmailFormat;

    }

    public static boolean passwordValidation(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isPasswordFormat = true;
        String validationString = null;

        if (!inputTextField.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")) {
            isPasswordFormat = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isPasswordFormat;

    }

}