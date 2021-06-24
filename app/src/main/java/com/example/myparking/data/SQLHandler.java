package com.example.myparking.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myparking.data.model.BaiXe;
import com.example.myparking.data.model.KhachHang;
import com.example.myparking.data.model.Role;
import com.example.myparking.data.model.TaiKhoan;

import java.util.List;

public class SQLHandler extends SQLiteOpenHelper {
    private static final String NAME_DB = "myparking";
    private static final int VERSION_DB = 4;
    private final BaiXeSQLHandler baiXeSQLHandler;
    private  final TaiKhoanSQLHandler taiKhoanSQLHandler;
    private final KhachHangSQLHandler khachHangSQLHandler;
    private final RoleSQLHandler roleSQLHandler;

    private static final String CREATE_TABLE_TAIKHOAN =
            "CREATE TABLE " + DBUltil.TAIKHOAN_TABLE_NAME + "(" +
                    DBUltil.COLUMN_TAIKHOAN_MATK + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.PRIMARY_KEY +" "+ DBUltil.AUTOINCREMENT + ", " +
                    DBUltil.COLUMN_TAIKHOAN_USERNAME + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NOT_NULL +  ", " +
                    DBUltil.COLUMN_TAIKHOAN_PASSWORD + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.COLUMN_TAIKHOAN_KHACHHANG_MAKH + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.COLUMN_TAIKHOAN_ROLE_MAROLE + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.FOREIGN_KEY + "("+ DBUltil.COLUMN_TAIKHOAN_KHACHHANG_MAKH +")" + " " + DBUltil.REFERENCES + " " + DBUltil.KHACHHANG_TABLE_NAME + "(" + DBUltil.COLUMN_KHACHHANG_MAKH +")"+ ", " +
                    DBUltil.FOREIGN_KEY + "("+ DBUltil.COLUMN_TAIKHOAN_ROLE_MAROLE +")" + " " + DBUltil.REFERENCES + " " + DBUltil.ROLE_TABLE_NAME + "(" + DBUltil.COLUMN_ROLE_MAROLE +")"+ " " +
                    ")";
    private static final String CREATE_TABLE_KHACHHANG=
            "CREATE TABLE " + DBUltil.KHACHHANG_TABLE_NAME + "(" +
                    DBUltil.COLUMN_KHACHHANG_MAKH + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.PRIMARY_KEY +" "+ DBUltil.AUTOINCREMENT + ", " +
                    DBUltil.COLUMN_KHACHHANG_TEN + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NOT_NULL +  ", " +
                    DBUltil.COLUMN_KHACHHANG_NGAYSINH + " " + DBUltil.DATE_DATA_TYPE + "  " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_BAIXE_DIACHI + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_KHACHHANG_SODIENTHOAI + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_KHACHHANG_ANHDAIDIEN + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NULL + " " +
                    ")";
    private static final String CREATE_TABLE_ROLE =
            "CREATE TABLE " + DBUltil.ROLE_TABLE_NAME + "(" +
                    DBUltil.COLUMN_ROLE_MAROLE + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.PRIMARY_KEY +" "+ DBUltil.AUTOINCREMENT + ", " +
                    DBUltil.COLUMN_ROLE_TENROLE + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NOT_NULL + " " +
                    ")";
    private static final String CREATE_TABLE_BAIXE =
            "CREATE TABLE " + DBUltil.BAIXE_TABLE_NAME + "(" +
                    DBUltil.COLUMN_BAIXE_MABX + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.PRIMARY_KEY +" "+ DBUltil.AUTOINCREMENT + ", " +
                    DBUltil.COLUMN_BAIXE_TENBX + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NOT_NULL +  ", " +
                    DBUltil.COLUMN_BAIXE_DIACHI + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.COLUMN_BAIXE_SOLUONGGIUXE + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.COLUMN_BAIXE_SOLUONGTRONG + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.COLUMN_BAIXE_KINHDO + " " + DBUltil.REAL_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_BAIXE_VIDO + " " + DBUltil.REAL_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_BAIXE_SODIENTHOAI + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_BAIXE_DESCRIPTION + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_BAIXE_IMAGES + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_BAIXE_STARTTIME + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_BAIXE_FINISHTIME + " " + DBUltil.TEXT_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_BAIXE_PRICE + " " + DBUltil.REAL_DATA_TYPE + " " + DBUltil.NULL + " " +
                    ")";
    private static final String CREATE_TABLE_THUE =
            "CREATE TABLE " + DBUltil.THUE_TABLE_NAME + "(" +
                    DBUltil.COLUMN_THUE_ID + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.PRIMARY_KEY +" "+ DBUltil.AUTOINCREMENT + ", " +
                    DBUltil.COLUMN_THUE_KHACHHANG_MAKH + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL +  ", " +
                    DBUltil.COLUMN_THUE_BAIXE_MABX + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.COLUMN_THUE_NGAYTHUE + " " + DBUltil.DATE_DATA_TYPE + " " + DBUltil.NULL + ", " +
                    DBUltil.COLUMN_THUE_THOIHAN + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.FOREIGN_KEY + "("+ DBUltil.COLUMN_THUE_KHACHHANG_MAKH +")" + " " + DBUltil.REFERENCES + " " + DBUltil.KHACHHANG_TABLE_NAME + "(" + DBUltil.COLUMN_KHACHHANG_MAKH +")"+ ", " +
                    DBUltil.FOREIGN_KEY + "("+ DBUltil.COLUMN_THUE_BAIXE_MABX +")" + " " + DBUltil.REFERENCES + " " + DBUltil.BAIXE_TABLE_NAME + "(" + DBUltil.COLUMN_BAIXE_MABX +")"+ " " +
                    ")";
    private static final String CREATE_TABLE_HINHANH =
            "CREATE TABLE " + DBUltil.HINHANH_TABLE_NAME + "(" +
                    DBUltil.COLUMN_HINHANH_ID + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.PRIMARY_KEY +" "+ DBUltil.AUTOINCREMENT + ", " +
                    DBUltil.COLUMN_HINHANH_BAIXE_MABX + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL +  ", " +
                    DBUltil.COLUMN_HINHANH_ANHBAIXE + " " + DBUltil.INTEGER_DATA_TYPE + " " + DBUltil.NOT_NULL + ", " +
                    DBUltil.FOREIGN_KEY + "("+ DBUltil.COLUMN_HINHANH_BAIXE_MABX +")" + " " + DBUltil.REFERENCES + " " + DBUltil.BAIXE_TABLE_NAME + "(" + DBUltil.COLUMN_BAIXE_MABX +")"+ " " +
                    ")";

    public SQLHandler(Context context, BaiXeSQLHandler baiXeSQLHandler, TaiKhoanSQLHandler taiKhoanSQLHandler, KhachHangSQLHandler khachHangSQLHandler, RoleSQLHandler roleSQLHandler){
        super(context,NAME_DB, null, VERSION_DB);
        this.baiXeSQLHandler = baiXeSQLHandler;
        this.taiKhoanSQLHandler = taiKhoanSQLHandler;
        this.khachHangSQLHandler = khachHangSQLHandler;
        this.roleSQLHandler = roleSQLHandler;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KHACHHANG);
        db.execSQL(CREATE_TABLE_ROLE);
        db.execSQL(CREATE_TABLE_BAIXE);
        db.execSQL(CREATE_TABLE_TAIKHOAN);
        db.execSQL(CREATE_TABLE_THUE);
        db.execSQL(CREATE_TABLE_HINHANH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_kh = String.format("DROP TABLE IF EXISTS %s", DBUltil.KHACHHANG_TABLE_NAME);
        String drop_bx = String.format("DROP TABLE IF EXISTS %s", DBUltil.BAIXE_TABLE_NAME);
        String drop_role = String.format("DROP TABLE IF EXISTS %s", DBUltil.ROLE_TABLE_NAME);
        String drop_tk = String.format("DROP TABLE IF EXISTS %s", DBUltil.TAIKHOAN_TABLE_NAME);
        String drop_thue = String.format("DROP TABLE IF EXISTS %s", DBUltil.THUE_TABLE_NAME);
        String drop_hinhanh = String.format("DROP TABLE IF EXISTS %s", DBUltil.HINHANH_TABLE_NAME);
        db.execSQL(drop_kh);
        db.execSQL(drop_bx);
        db.execSQL(drop_role);
        db.execSQL(drop_tk);
        db.execSQL(drop_thue);
        db.execSQL(drop_hinhanh);

        onCreate(db);
    }
//    public void addKhachHang(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        KhachHang kh = new KhachHang(1,"phuoc",null,"phuoc","123123","");
//        ContentValues ct = new ContentValues();
//        ct.put(DBUltil.COLUMN_KHACHHANG_MAKH, kh.getMaKH());
//        ct.put(DBUltil.COLUMN_KHACHHANG_TEN, kh.getTen());
//        ct.put(DBUltil.COLUMN_KHACHHANG_NGAYSINH, String.valueOf(kh.getNgaysinh()));
//        ct.put(DBUltil.COLUMN_KHACHHANG_DIACHI, kh.getDiachi());
//        ct.put(DBUltil.COLUMN_KHACHHANG_SODIENTHOAI, kh.getSodienthoai());
//        ct.put(DBUltil.COLUMN_KHACHHANG_ANHDAIDIEN, kh.getAnhdaidien());
//        db.insert(DBUltil.KHACHHANG_TABLE_NAME, null, ct);
//        db.close();
//    }
//    public List<KhachHang> getAllKhachHang(){
//        List<KhachHang> listKH = new ArrayList<>();
//        String query = "SELECT * FROM " + DBUltil.KHACHHANG_TABLE_NAME;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if(cursor.moveToFirst()){
//            do{
//                KhachHang kh = new KhachHang(Integer.parseInt(cursor.getString(0)), cursor.getString(1), null, cursor.getString(3), cursor.getString(4), cursor.getString(5) );
//                listKH.add(kh);
//            }while (cursor.moveToNext());
//        }
//        return listKH;
//    }

    public  void addRole(){
        SQLiteDatabase db = this.getWritableDatabase();
        Role role = new Role(1, "user");
        ContentValues ct = new ContentValues();
//        ct.put(DBUltil.COLUMN_ROLE_MAROLE, role.getMarole());
        ct.put(DBUltil.COLUMN_ROLE_TENROLE, role.getTenrole());
        db.insert(DBUltil.ROLE_TABLE_NAME,null,ct);
        db.close();
    }


//  Xử lý bãi xe
    // add nhieu bai xe
//    public void addBaiXe(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        // Thành phố đà nẵng
//        BaiXe baiXe1 = new BaiXe(1, "Bãi đỗ xe Chương Dương", "Khuê Mỹ, Ngũ Hành Sơn, Đà Nẵng, Việt Nam", 50,20,16.0262285, 108.2383837, "083572010", "","","6AM", "9PM", 10.0 );
//        BaiXe baiXe2 = new BaiXe(2, "Bãi đỗ xe Xóm Đồng", "Chương D., Khuê Mỹ, Ngũ Hành Sơn, Đà Nẵng, Việt Nam", 50,20,16.021208, 108.2415211, "083572010", "","","6AM", "9:30PM", 9.0 );
//        BaiXe baiXe3 = new BaiXe(3, "Bãi đỗ xe Khu di tích K20", "Đ. Nghiêm Xuân Yêm, Khuê Mỹ, Ngũ Hành Sơn, Đà Nẵng, Việt Nam", 50,20,16.0220627, 108.2433896 ,"083572010", "","","6:30AM", "9PM", 10.0 );
//        //Thành phố hồ chí minh
//        BaiXe baiXe4 = new BaiXe(4, "Bãi Giữ Xe Ô Tô LÊ TRÂN", "14/2A Đường Trần Não, P. Bình An, Quận 2, Thành phố Hồ Chí Minh, Việt Nam", 50,20,10.7831499, 106.730463,"083572010", "","","6AM", "9PM", 10.0 );
//        BaiXe baiXe5 = new BaiXe(5, "Bãi giữ xe ô tô 24/24 giá rẻ", "68 Số 33, P. Bình An, Quận 2, Thành phố Hồ Chí Minh, Việt Nam", 50,20,10.7907994, 106.7269587,"083572010", "","","6AM", "9PM", 10.0  );
//        baiXeSQLHandler.addBaiXe(db, baiXe1);
//        baiXeSQLHandler.addBaiXe(db, baiXe2);
//        baiXeSQLHandler.addBaiXe(db, baiXe3);
//        baiXeSQLHandler.addBaiXe(db, baiXe4);
//        baiXeSQLHandler.addBaiXe(db, baiXe5);
//        db.close();
//    }
    public void addBaiXe(BaiXe baixe){
        SQLiteDatabase db = this.getWritableDatabase();
        baiXeSQLHandler.addBaiXe(db,baixe);
        db.close();
    }
    public List<BaiXe> getAllBaiXe(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return baiXeSQLHandler.getAllBaiXe(sqLiteDatabase);
    }
    public void deleteBaiXe(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        baiXeSQLHandler.deleteBaiXe(sqLiteDatabase,id);
        sqLiteDatabase.close();
    }
    public void updateBaiXe(BaiXe baiXe, int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        baiXeSQLHandler.updateBaiXe(sqLiteDatabase,baiXe,id);
        sqLiteDatabase.close();
    }

    //tài khoản
    public TaiKhoan getTaiKhoanByUsernamePassword(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return taiKhoanSQLHandler.getTaiKhoanByUsenamePassword(sqLiteDatabase, username, password);
    }
    public void addTaiKhoan(){
        SQLiteDatabase db = this.getWritableDatabase();
        TaiKhoan taiKhoan = new TaiKhoan(1,"phuoc", "12345");
        ContentValues ct = new ContentValues();
//        ct.put(DBUltil.COLUMN_TAIKHOAN_MATK, taiKhoan.getMatk());
        ct.put(DBUltil.COLUMN_TAIKHOAN_USERNAME, taiKhoan.getUsername());
        ct.put(DBUltil.COLUMN_TAIKHOAN_PASSWORD, taiKhoan.getPassword());
        ct.put(DBUltil.COLUMN_TAIKHOAN_KHACHHANG_MAKH, 1);
        ct.put(DBUltil.COLUMN_TAIKHOAN_ROLE_MAROLE, 1);
        db.insert(DBUltil.TAIKHOAN_TABLE_NAME,null,ct);
        db.close();
    }
    public void addTaiKhoan(TaiKhoan taiKhoan, int makh, int marole){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        taiKhoanSQLHandler.addTaiKhoan(sqLiteDatabase,taiKhoan,makh,marole);
    }
    public List<TaiKhoan> getAllTaiKhoan(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return taiKhoanSQLHandler.getAllTaiKhoan(sqLiteDatabase);
    }
    public void updateTaiKhoan(TaiKhoan taiKhoan,int matk){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        taiKhoanSQLHandler.updateTaiKhoan(sqLiteDatabase, taiKhoan, matk);
    }


    //Khach hang
    public List<KhachHang> getAllKhachHang(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return khachHangSQLHandler.getAllKhachHang(sqLiteDatabase);
    }
    public void addKhachHang(KhachHang khachHang){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        khachHangSQLHandler.addKhachKhang(sqLiteDatabase,khachHang);
        sqLiteDatabase.close();
    }
}
