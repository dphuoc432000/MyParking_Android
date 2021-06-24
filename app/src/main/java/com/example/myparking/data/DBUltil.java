package com.example.myparking.data;

public class DBUltil {
    //parameter taikhoan static
    public static final String TAIKHOAN_TABLE_NAME = "taikhoan";
    public static final String COLUMN_TAIKHOAN_MATK = "matk";
    public static final String COLUMN_TAIKHOAN_USERNAME = "username";
    public static final String COLUMN_TAIKHOAN_PASSWORD = "password";
    public static final String COLUMN_TAIKHOAN_KHACHHANG_MAKH = "makh";
    public static final String COLUMN_TAIKHOAN_ROLE_MAROLE = "marole";

    //parameter khachhang static
    public static final String KHACHHANG_TABLE_NAME = "khachhang";
    public static final String COLUMN_KHACHHANG_MAKH = "makh";
    public static final String COLUMN_KHACHHANG_TEN = "ten";
    public static final String COLUMN_KHACHHANG_NGAYSINH = "ngaysinh";
    public static final String COLUMN_KHACHHANG_DIACHI = "diachi";
    public static final String COLUMN_KHACHHANG_SODIENTHOAI = "sodienthoai";
    public static final String COLUMN_KHACHHANG_ANHDAIDIEN = "anhdaidien";

    //parameter role static
    public static final String ROLE_TABLE_NAME = "role";
    public static final String COLUMN_ROLE_MAROLE = "marole";
    public static final String COLUMN_ROLE_TENROLE = "tenrole";

    //parameter thue static
    public static final String THUE_TABLE_NAME = "thue";
    public static final String COLUMN_THUE_ID = "id";
    public static final String COLUMN_THUE_KHACHHANG_MAKH = "makh";
    public static final String COLUMN_THUE_BAIXE_MABX= "mabx";
    public static final String COLUMN_THUE_NGAYTHUE = "ngaythue";
    public static final String COLUMN_THUE_THOIHAN = "thoihan";

    //parameter baixe static
    public static final String BAIXE_TABLE_NAME = "baixe";
    public static final String COLUMN_BAIXE_MABX = "mabx";
    public static final String COLUMN_BAIXE_TENBX = "tenbx";
    public static final String COLUMN_BAIXE_DIACHI = "diachi";
    public static final String COLUMN_BAIXE_SOLUONGGIUXE = "soluonggiuxe";
    public static final String COLUMN_BAIXE_SOLUONGTRONG = "soluongtrong";
    public static final String COLUMN_BAIXE_KINHDO = "kinhdo";
    public static final String COLUMN_BAIXE_VIDO = "vido";
    public static final String COLUMN_BAIXE_SODIENTHOAI = "sodienthoai";
    public static final String COLUMN_BAIXE_DESCRIPTION = "description";
    public static final String COLUMN_BAIXE_IMAGES = "images";
    public static final String COLUMN_BAIXE_STARTTIME = "startTime";
    public static final String COLUMN_BAIXE_FINISHTIME = "finishTime";
    public static final String COLUMN_BAIXE_PRICE = "price";

    //parameter hinhanh static
    public static final String HINHANH_TABLE_NAME = "hinhanh";
    public static final String COLUMN_HINHANH_ID = "id";
    public static final String COLUMN_HINHANH_ANHBAIXE = "anhbaixe";
    public static final String COLUMN_HINHANH_BAIXE_MABX = "mabx";

    // parameter type data
    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String FOREIGN_KEY = "FOREIGN KEY";
    public static final String REFERENCES = "REFERENCES";
    public static final String NOT_NULL = "NOT NULL";
    public static final String NULL = "NULL";
    public static final String AUTOINCREMENT = "AUTOINCREMENT";
    public static final String TEXT_DATA_TYPE = "TEXT";
    public static final String REAL_DATA_TYPE = "DOUBLE";
    public static final String DATE_DATA_TYPE = "DATE";
    public static final String INTEGER_DATA_TYPE = "INTEGER";

}
