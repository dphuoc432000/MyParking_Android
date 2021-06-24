package com.example.myparking.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myparking.R;
import com.example.myparking.data.BaiXeSQLHandler;
import com.example.myparking.data.KhachHangSQLHandler;
import com.example.myparking.data.RoleSQLHandler;
import com.example.myparking.data.SQLHandler;
import com.example.myparking.data.TaiKhoanSQLHandler;
import com.example.myparking.data.model.Role;
import com.example.myparking.data.model.TaiKhoan;

public class UpdateAccount extends AppCompatActivity {

    EditText editT_username, editT_password;
    Button btn_update;
    BaiXeSQLHandler baiXeSQLHandler;
    KhachHangSQLHandler khachHangSQLHandler;
    RoleSQLHandler roleSQLHandler;
    TaiKhoanSQLHandler taiKhoanSQLHandler;
    SQLHandler sqlHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        baiXeSQLHandler = new BaiXeSQLHandler();
        khachHangSQLHandler = new KhachHangSQLHandler();
        roleSQLHandler = new RoleSQLHandler();
        taiKhoanSQLHandler = new TaiKhoanSQLHandler();
        sqlHandler = new SQLHandler(this, baiXeSQLHandler,taiKhoanSQLHandler,khachHangSQLHandler,roleSQLHandler);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        editT_username = findViewById(R.id.editT_username_update_acc);
        editT_password = findViewById(R.id.editT_password_update_acc);
        btn_update = findViewById(R.id.btn_update_account);


        Bundle bundle = this.getIntent().getExtras();
        editT_username.setText(bundle.getString("username"));
        editT_password.setText(bundle.getString("password"));
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiKhoan taiKhoan = new TaiKhoan(bundle.getInt("matk"),editT_username.getText().toString(), editT_password.getText().toString());
                sqlHandler.updateTaiKhoan(taiKhoan, bundle.getInt("matk"));
                Intent intent = new Intent(UpdateAccount.this, AccountActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("matk", bundle.getInt("matk"));
                bundle1.putString("username", taiKhoan.getUsername());
                bundle1.putString("password", taiKhoan.getPassword());
                intent.putExtras(bundle1);
                Toast.makeText(getApplicationContext(),"Update account in successfully", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}