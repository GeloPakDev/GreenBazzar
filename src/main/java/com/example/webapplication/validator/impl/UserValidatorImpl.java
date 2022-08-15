package com.example.webapplication.validator.impl;

import com.example.webapplication.entity.user.User;
import com.example.webapplication.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private static final String INCORRECT_VALUE_PARAMETER = "- Incorrect Value";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    private static final String EMAIL_REGEX = "^(.+)@(\\S+)$";
    private static final String FIRSTNAME_AND_LASTNAME_REGEX = "^[A-Za-z]{0,25}$";

    private static final UserValidator instance = new UserValidatorImpl();

    public static UserValidator getInstance() {
        return instance;
    }

    private UserValidatorImpl() {
    }

    @Override
    public boolean checkLogin(String login) {
        return login != null && Pattern.matches(LOGIN_REGEX, login);
    }

    @Override
    public boolean checkPassword(String password) {
        return password != null && Pattern.matches(PASSWORD_REGEX, password);
    }

    @Override
    public boolean checkFirstName(String firstName) {
        return firstName != null && Pattern.matches(FIRSTNAME_AND_LASTNAME_REGEX, firstName);
    }

    @Override
    public boolean checkLastName(String lastName) {
        return lastName != null && Pattern.matches(FIRSTNAME_AND_LASTNAME_REGEX, lastName);
    }

    @Override
    public boolean checkEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }


    @Override
    public boolean checkUser(User user) {
        boolean isValid = true;
        if (!checkLogin(user.getLogin())) {
            user.setLogin(user.getLogin() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkPassword(user.getPassword())) {
            user.setPassword(user.getPassword() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkEmail(user.getEmail())) {
            user.setEmail(user.getEmail() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkFirstName(user.getFirstName())) {
            user.setFirstName(user.getFirstName() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkLastName(user.getLastName())) {
            user.setLastName(user.getLastName() + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        return isValid;
    }
}