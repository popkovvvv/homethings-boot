package com.homethings.homethingsboot.validation;

import javax.validation.constraints.Size;

public class LoginFormBean {

    private String email;

    @Size(min = 4, max = 32, message = "Password length ...")
    private String password;

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

}
