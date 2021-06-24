package com.example.myparking.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myparking.data.model.BaiXe;
import com.example.myparking.data.model.KhachHang;
import com.example.myparking.data.model.TaiKhoan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhachHangSQLHandler {
    public List<KhachHang> getAllKhachHang(SQLiteDatabase db){
        List<KhachHang> listKhachHang= new ArrayList<>();
        String query = "SELECT * FROM " + DBUltil.KHACHHANG_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                KhachHang khachHang = new KhachHang(cursor.getInt(0), cursor.getString(1), null ,cursor.getString(3), cursor.getString(4), cursor.getString(5));
                listKhachHang.add(khachHang);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listKhachHang;
    }
    public void addKhachKhang(SQLiteDatabase db, KhachHang khachHang){
        ContentValues ct = new ContentValues();
        ct.put(DBUltil.COLUMN_KHACHHANG_TEN, khachHang.getTen());
        ct.put(DBUltil.COLUMN_KHACHHANG_NGAYSINH, "null");
        ct.put(DBUltil.COLUMN_KHACHHANG_DIACHI, khachHang.getDiachi());
        ct.put(DBUltil.COLUMN_KHACHHANG_SODIENTHOAI,khachHang.getSodienthoai());
        ct.put(DBUltil.COLUMN_KHACHHANG_ANHDAIDIEN, "");
        db.insert(DBUltil.KHACHHANG_TABLE_NAME, null, ct);
    }
}
