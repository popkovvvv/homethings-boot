package com.homethings.homethingsboot.validation;

public class EditTaskFormBean {

    private String title;

    private long performed;

    private boolean checked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPerformed() {
        return performed;
    }

    public void setPerformed(long performed) {
        this.performed = performed;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
