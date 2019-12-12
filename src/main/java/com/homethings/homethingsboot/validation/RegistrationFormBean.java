package com.homethings.homethingsboot.validation;

import javax.validation.constraints.Size;

public class RegistrationFormBean {

    private String email;

    @Size(min = 4, max = 32, message = "Password length ...")
    private String password;

    @Size(min = 4, max = 32, message = "Password length ...")
    private String passwordConfirmation;

    @Size(min = 4, max = 32, message = "Login length ...")
    private String login;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
