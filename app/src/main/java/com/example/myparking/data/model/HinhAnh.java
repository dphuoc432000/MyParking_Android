package com.example.myparking.data.model;

public class HinhAnh {
    private int id;
    private String anhbaixe;

    public HinhAnh(int id, String anhbaixe) {
        this.id = id;
        this.anhbaixe = anhbaixe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnhbaixe() {
        return anhbaixe;
    }

    public void setAnhbaixe(String anhbaixe) {
        this.anhbaixe = anhbaixe;
    }
}
