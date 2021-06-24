package com.example.myparking.data.model;

import java.util.Date;

public class Thue {

    private int id;
    private Date ngaythue;
    private int thoihan;

    public Thue(int id, Date ngaythue, int thoihan) {
        this.id = id;
        this.ngaythue = ngaythue;
        this.thoihan = thoihan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(Date ngaythue) {
        this.ngaythue = ngaythue;
    }

    public int getThoihan() {
        return thoihan;
    }

    public void setThoihan(int thoihan) {
        this.thoihan = thoihan;
    }
}
