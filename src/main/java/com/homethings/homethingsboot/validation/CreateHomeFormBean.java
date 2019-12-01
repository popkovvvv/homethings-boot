package com.homethings.homethingsboot.validation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class CreateHomeFormBean {

    @Min(4)
    @Max(32)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
