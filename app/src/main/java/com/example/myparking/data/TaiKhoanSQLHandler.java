package com.example.myparking.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myparking.data.model.BaiXe;
import com.example.myparking.data.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanSQLHandler {
    public TaiKhoan getTaiKhoanByUsenamePassword(SQLiteDatabase sqLiteDatabase,String username, String password){
        TaiKhoan taiKhoan = null;;
        String[] colums = {
                DBUltil.COLUMN_TAIKHOAN_MATK,
                DBUltil.COLUMN_TAIKHOAN_USERNAME,
                DBUltil.COLUMN_TAIKHOAN_PASSWORD,
                DBUltil.COLUMN_TAIKHOAN_KHACHHANG_MAKH,
                DBUltil.COLUMN_TAIKHOAN_ROLE_MAROLE
        };
        String selection = DBUltil.COLUMN_TAIKHOAN_USERNAME + " = ?" + " AND " + DBUltil.COLUMN_TAIKHOAN_PASSWORD + "= ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = sqLiteDatabase.query(DBUltil.TAIKHOAN_TABLE_NAME, colums, selection, selectionArgs,null,null,null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            taiKhoan = new TaiKhoan(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2));
        }
        if(cursor != null)
            cursor.close();
        sqLiteDatabase.close();
        return taiKhoan;
    }
    public List<TaiKhoan> getAllTaiKhoan(SQLiteDatabase db){
        List<TaiKhoan> listTaiKhoan= new ArrayList<>();
        String query = "SELECT * FROM " + DBUltil.TAIKHOAN_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                TaiKhoan tk = new TaiKhoan(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                listTaiKhoan.add(tk);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listTaiKhoan;
    }
    public void addTaiKhoan(SQLiteDatabase db, TaiKhoan taiKhoan, int makh, int marole){
        ContentValues ct = new ContentValues();
        ct.put(DBUltil.COLUMN_TAIKHOAN_USERNAME, taiKhoan.getUsername());
        ct.put(DBUltil.COLUMN_TAIKHOAN_PASSWORD, taiKhoan.getPassword());
        ct.put(DBUltil.COLUMN_TAIKHOAN_KHACHHANG_MAKH, makh);
        ct.put(DBUltil.COLUMN_TAIKHOAN_ROLE_MAROLE, marole);
        db.insert(DBUltil.TAIKHOAN_TABLE_NAME, null, ct);
    }
    public void updateTaiKhoan(SQLiteDatabase db, TaiKhoan taiKhoan,int matk){
        String matk2 = matk + "";
        ContentValues ct = new ContentValues();
        ct.put(DBUltil.COLUMN_TAIKHOAN_USERNAME, taiKhoan.getUsername());
        ct.put(DBUltil.COLUMN_TAIKHOAN_PASSWORD, taiKhoan.getPassword());
        db.update(DBUltil.TAIKHOAN_TABLE_NAME, ct,DBUltil.COLUMN_TAIKHOAN_MATK + "=?", new String[]{matk2});
    }
}
