package com.homethings.homethingsboot.validation;

import javax.validation.constraints.Size;

public class LoginFormBean {

    private String login;

    @Size(min = 4, max = 32, message = "Password length ...")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
