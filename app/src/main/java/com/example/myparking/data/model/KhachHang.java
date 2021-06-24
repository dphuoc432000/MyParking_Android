package com.example.myparking.data.model;

import java.util.Date;

public class KhachHang {
    private int maKH;
    private String ten;
    private Date ngaysinh;
    private String diachi;
    private String sodienthoai;
    private String anhdaidien;

    public KhachHang(int maKH, String ten, Date ngaysinh, String diachi, String sodienthoai, String anhdaidien) {
        this.maKH = maKH;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
        this.anhdaidien = anhdaidien;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getAnhdaidien() {
        return anhdaidien;
    }

    public void setAnhdaidien(String anhdaidien) {
        this.anhdaidien = anhdaidien;
    }
}
