package com.example.myparking.data.model;

public class BaiXe {
    private int mabx;
    private String tenBx, diachi;
    private int soluonggiuxe, soluongtrong;
    private double kinhdo, vido;
    private String sdt;
    private String description;
    private String images;
    private String startTime, finishTime;
    private double price;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public BaiXe(int mabx, String tenBx, String diachi, int soluonggiuxe, int soluongtrong, double kinhdo, double vido, String sdt, String description, String images, String startTime, String finishTime, double price) {
        this.mabx = mabx;
        this.tenBx = tenBx;
        this.diachi = diachi;
        this.soluonggiuxe = soluonggiuxe;
        this.soluongtrong = soluongtrong;
        this.kinhdo = kinhdo;
        this.vido = vido;
        this.description = description;
        this.sdt = sdt;
        this.images = images;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.price = price;
    }

    public int getMabx() {
        return mabx;
    }

    public void setMabx(int mabx) {
        this.mabx = mabx;
    }

    public String getTenBx() {
        return tenBx;
    }

    public void setTenBx(String tenBx) {
        this.tenBx = tenBx;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSoluonggiuxe() {
        return soluonggiuxe;
    }

    public void setSoluonggiuxe(int soluonggiuxe) {
        this.soluonggiuxe = soluonggiuxe;
    }

    public int getSoluongtrong() {
        return soluongtrong;
    }

    public void setSoluongtrong(int soluongtrong) {
        this.soluongtrong = soluongtrong;
    }

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }
}
