package com.example.myparking.data.model;

public class TaiKhoan {
    private int matk;
    private String username, password;

    public TaiKhoan(int matk, String username, String password) {
        this.matk = matk;
        this.username = username;
        this.password = password;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
