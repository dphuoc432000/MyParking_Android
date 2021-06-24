package com.example.myparking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myparking.R;
import com.example.myparking.data.BaiXeSQLHandler;
import com.example.myparking.data.KhachHangSQLHandler;
import com.example.myparking.data.RoleSQLHandler;
import com.example.myparking.data.SQLHandler;
import com.example.myparking.data.TaiKhoanSQLHandler;
import com.example.myparking.data.model.KhachHang;
import com.example.myparking.data.model.Role;
import com.example.myparking.data.model.TaiKhoan;
import com.google.android.material.textfield.TextInputLayout;

public class Register2 extends AppCompatActivity {

    SQLHandler sqlHandler;
    BaiXeSQLHandler baiXeSQLHandler;
    KhachHangSQLHandler khachHangSQLHandler;
    RoleSQLHandler roleSQLHandler;
    TaiKhoanSQLHandler taiKhoanSQLHandler;
    TextInputLayout textIp_fullname, textIp_diachi,textIp_sdt;
    Button btn_regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        baiXeSQLHandler = new BaiXeSQLHandler();
        roleSQLHandler = new RoleSQLHandler();
        taiKhoanSQLHandler = new TaiKhoanSQLHandler();
        khachHangSQLHandler = new KhachHangSQLHandler();
        sqlHandler = new SQLHandler(this,baiXeSQLHandler,taiKhoanSQLHandler,khachHangSQLHandler,roleSQLHandler);
        textIp_fullname = findViewById(R.id.textIp_ten_khachhang);
        textIp_diachi = findViewById(R.id.textIp_diachi_khachhang);
        textIp_sdt = findViewById(R.id.textIp_sdt_khachhang);
        btn_regis = findViewById(R.id.btn_register);

        Bundle bundle = this.getIntent().getExtras();
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhachHang khachHang = new KhachHang(1, textIp_fullname.getEditText().getText().toString(),null,textIp_diachi.getEditText().getText().toString(),textIp_sdt.getEditText().getText().toString(),"");
                sqlHandler.addKhachHang(khachHang);
                TaiKhoan taiKhoan = new TaiKhoan(1,bundle.getString("usernameRegis"), bundle.getString("passwordRegis"));
                sqlHandler.addTaiKhoan(taiKhoan, 1,1);
                Intent i = new Intent(Register2.this, Login.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Register Success", Toast.LENGTH_LONG).show();
            }
        });
    }
}