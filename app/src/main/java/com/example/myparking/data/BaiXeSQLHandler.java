package com.example.myparking.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myparking.data.model.BaiXe;
import com.example.myparking.data.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class BaiXeSQLHandler {

    public void addBaiXe(SQLiteDatabase db, BaiXe baiXe){
        ContentValues ct = new ContentValues();
        //ct.put(DBUltil.COLUMN_BAIXE_MABX, baiXe.getMabx());
        ct.put(DBUltil.COLUMN_BAIXE_TENBX, baiXe.getTenBx());
        ct.put(DBUltil.COLUMN_BAIXE_DIACHI, baiXe.getDiachi());
        ct.put(DBUltil.COLUMN_BAIXE_SOLUONGGIUXE, baiXe.getSoluonggiuxe());
        ct.put(DBUltil.COLUMN_BAIXE_SOLUONGTRONG, baiXe.getSoluongtrong());
        ct.put(DBUltil.COLUMN_BAIXE_KINHDO, baiXe.getKinhdo());
        ct.put(DBUltil.COLUMN_BAIXE_VIDO, baiXe.getVido());
        ct.put(DBUltil.COLUMN_BAIXE_SODIENTHOAI, baiXe.getSdt());
        ct.put(DBUltil.COLUMN_BAIXE_DESCRIPTION, baiXe.getDescription());
        ct.put(DBUltil.COLUMN_BAIXE_IMAGES, baiXe.getImages());
        ct.put(DBUltil.COLUMN_BAIXE_STARTTIME, baiXe.getStartTime());
        ct.put(DBUltil.COLUMN_BAIXE_FINISHTIME, baiXe.getFinishTime());
        ct.put(DBUltil.COLUMN_BAIXE_PRICE, baiXe.getPrice());
        db.insert(DBUltil.BAIXE_TABLE_NAME, null, ct);
    }

    public List<BaiXe> getAllBaiXe(SQLiteDatabase db){
        List<BaiXe> listBaiXe= new ArrayList<>();
        String query = "SELECT * FROM " + DBUltil.BAIXE_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                BaiXe bx = new BaiXe(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getDouble(5), cursor.getDouble(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getDouble(12) );
                listBaiXe.add(bx);
            }while (cursor.moveToNext());
        }
        return listBaiXe;
    }
    public void deleteBaiXe(SQLiteDatabase db,int id){
        String mabx = id + "";
        db.delete(DBUltil.BAIXE_TABLE_NAME, DBUltil.COLUMN_BAIXE_MABX + "=?", new String[]{mabx});
    }
    public void updateBaiXe(SQLiteDatabase db,BaiXe baiXe ,int id){
        String mabx = id + "";
        ContentValues ct = new ContentValues();
        //ct.put(DBUltil.COLUMN_BAIXE_MABX, baiXe.getMabx());
        ct.put(DBUltil.COLUMN_BAIXE_TENBX, baiXe.getTenBx());
        ct.put(DBUltil.COLUMN_BAIXE_DIACHI, baiXe.getDiachi());
        ct.put(DBUltil.COLUMN_BAIXE_SOLUONGGIUXE, baiXe.getSoluonggiuxe());
        ct.put(DBUltil.COLUMN_BAIXE_SOLUONGTRONG, baiXe.getSoluongtrong());
        ct.put(DBUltil.COLUMN_BAIXE_KINHDO, baiXe.getKinhdo());
        ct.put(DBUltil.COLUMN_BAIXE_VIDO, baiXe.getVido());
        ct.put(DBUltil.COLUMN_BAIXE_SODIENTHOAI, baiXe.getSdt());
        ct.put(DBUltil.COLUMN_BAIXE_DESCRIPTION, baiXe.getDescription());
        ct.put(DBUltil.COLUMN_BAIXE_IMAGES, baiXe.getImages());
        ct.put(DBUltil.COLUMN_BAIXE_STARTTIME, baiXe.getStartTime());
        ct.put(DBUltil.COLUMN_BAIXE_FINISHTIME, baiXe.getFinishTime());
        ct.put(DBUltil.COLUMN_BAIXE_PRICE, baiXe.getPrice());
        db.update(DBUltil.BAIXE_TABLE_NAME, ct,DBUltil.COLUMN_BAIXE_MABX + "=?", new String[]{mabx});
    }
}
