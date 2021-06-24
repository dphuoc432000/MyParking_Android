package com.example.myparking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myparking.R;
import com.example.myparking.data.BaiXeSQLHandler;
import com.example.myparking.data.KhachHangSQLHandler;
import com.example.myparking.data.RoleSQLHandler;
import com.example.myparking.data.SQLHandler;
import com.example.myparking.data.TaiKhoanSQLHandler;
import com.example.myparking.data.model.BaiXe;
import com.example.myparking.data.model.TaiKhoan;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Login extends AppCompatActivity {

    TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    Button btn_login;
    SQLHandler sqlHandler;
    BaiXeSQLHandler baiXeSQLHandler;
    TaiKhoanSQLHandler taiKhoanSQLHandler;
    TextView regisText;
    KhachHangSQLHandler khachHangSQLHandler;
    RoleSQLHandler roleSQLHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textInputLayoutUsername = findViewById(R.id.textIp_username);
        textInputLayoutPassword = findViewById(R.id.textIp_password);
        regisText = findViewById(R.id.registerSwitchText);
        btn_login = findViewById(R.id.btn_login);
        baiXeSQLHandler = new BaiXeSQLHandler();
        taiKhoanSQLHandler = new TaiKhoanSQLHandler();
        khachHangSQLHandler = new KhachHangSQLHandler();
        roleSQLHandler = new RoleSQLHandler();
        sqlHandler = new SQLHandler(this,baiXeSQLHandler, taiKhoanSQLHandler, khachHangSQLHandler, roleSQLHandler);
        setupFloatingLabelErrorUsername();
        setupFloatingLabelErrorPassword();
//        sqlHandler.addKhachHang();
        sqlHandler.addRole();
        sqlHandler.addTaiKhoan();
        List<TaiKhoan> list = sqlHandler.getAllTaiKhoan();

//        textInputLayoutUsername.getEditText().getText().length()
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textInputLayoutUsername.getEditText().getText().toString().equals("admin") && textInputLayoutPassword.getEditText().getText().toString().equals( "admin")){
                    Intent intent = new Intent(Login.this, QuanLyBaiXe_admin.class);
                    startActivity(intent);
                }else{
                    TaiKhoan taiKhoan = sqlHandler.getTaiKhoanByUsernamePassword(textInputLayoutUsername.getEditText().getText().toString(), textInputLayoutPassword.getEditText().getText().toString());
                    if(taiKhoan != null){
                        Intent intent = new Intent(Login.this, MapActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("username", taiKhoan.getUsername());
                        bundle2.putString("password", taiKhoan.getPassword());
                        bundle2.putInt("matk", taiKhoan.getMatk());
                        intent.putExtras(bundle2);
                        Toast.makeText(getApplicationContext(),"Logged in successfully", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }else
                        Toast.makeText(getApplicationContext(),"Username or password is incorrect", Toast.LENGTH_LONG).show();
                }

            }
        });
        regisText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }

    private void setupFloatingLabelErrorUsername() {
        textInputLayoutUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0 ) {
                    textInputLayoutUsername.setError("Username is required");
                    textInputLayoutUsername.setErrorEnabled(true);
                } else if (text.length() < 5 ) {
                    textInputLayoutUsername.setError("Username is required and length must be >= 5");
                    textInputLayoutUsername.setErrorEnabled(true);
                } else {
                    textInputLayoutUsername.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
    private void setupFloatingLabelErrorPassword() {
        textInputLayoutPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0 ) {
                    textInputLayoutPassword.setError("Passwrord is required");
                    textInputLayoutPassword.setErrorEnabled(true);
                } else if (text.length() < 5 ) {
                    textInputLayoutPassword.setError("Passwrord is required and length must be >= 5");
                    textInputLayoutPassword.setErrorEnabled(true);
                } else {
                    textInputLayoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
}