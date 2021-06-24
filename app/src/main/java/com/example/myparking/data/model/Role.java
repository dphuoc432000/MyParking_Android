package com.example.myparking.data.model;

public class Role {
    private int marole;
    private String tenrole;

    public Role(int marole, String tenrole) {
        this.marole = marole;
        this.tenrole = tenrole;
    }

    public int getMarole() {
        return marole;
    }

    public void setMarole(int marole) {
        this.marole = marole;
    }

    public String getTenrole() {
        return tenrole;
    }

    public void setTenrole(String tenrole) {
        this.tenrole = tenrole;
    }
}
