package com.example.myparking.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myparking.R;
import com.example.myparking.data.BaiXeSQLHandler;
import com.example.myparking.data.KhachHangSQLHandler;
import com.example.myparking.data.RoleSQLHandler;
import com.example.myparking.data.SQLHandler;
import com.example.myparking.data.TaiKhoanSQLHandler;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    SQLHandler sqlHandler;
    BaiXeSQLHandler baiXeSQLHandler;
    TaiKhoanSQLHandler taiKhoanSQLHandler;
    KhachHangSQLHandler khachHangSQLHandler;
    RoleSQLHandler roleSQLHandler;
    TextInputLayout textIp_username_regis;
    TextInputLayout textIp_password_regis;
    TextInputLayout textIp_passwordAgain_regis;
    Button btn_continue1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        baiXeSQLHandler = new BaiXeSQLHandler();
        taiKhoanSQLHandler = new TaiKhoanSQLHandler();
        khachHangSQLHandler = new KhachHangSQLHandler();
        roleSQLHandler = new RoleSQLHandler();
        sqlHandler = new SQLHandler(this, baiXeSQLHandler, taiKhoanSQLHandler, khachHangSQLHandler, roleSQLHandler);

        textIp_username_regis = findViewById(R.id.textIp_username_regis);
        textIp_password_regis = findViewById(R.id.textIp_password_regis);
        textIp_passwordAgain_regis = findViewById(R.id.textIp_passwordAgain_regis);
        btn_continue1 = findViewById(R.id.btn_continue1);

        setupFloatingLabelErrorUsernameRegis();
        setupFloatingLabelErrorPasswordRegis();
        setupFloatingLabelErrorPasswordAgainRegis();
        btn_continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textIp_password_regis.getEditText().getText().toString().equals(textIp_passwordAgain_regis.getEditText().getText().toString())){
                    Intent i = new Intent(Register.this, Register2.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("usernameRegis",textIp_username_regis.getEditText().getText().toString() );
                    bundle.putString("passwordRegis", textIp_password_regis.getEditText().getText().toString());
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_LONG).show();
                }
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
    private void setupFloatingLabelErrorUsernameRegis() {
        textIp_username_regis.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0 ) {
                    textIp_username_regis.setError("Username is required");
                    textIp_username_regis.setErrorEnabled(true);
                } else if (text.length() < 5 ) {
                    textIp_username_regis.setError("Username is required and length must be >= 5");
                    textIp_username_regis.setErrorEnabled(true);
                } else {
                    textIp_username_regis.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
    private void setupFloatingLabelErrorPasswordRegis() {
        textIp_password_regis.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0 ) {
                    textIp_password_regis.setError("Passwrord is required");
                    textIp_password_regis.setErrorEnabled(true);
                } else if (text.length() < 5 ) {
                    textIp_password_regis.setError("Passwrord is required and length must be >= 5");
                    textIp_password_regis.setErrorEnabled(true);
                } else {
                    textIp_password_regis.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
    private void setupFloatingLabelErrorPasswordAgainRegis() {
        textIp_passwordAgain_regis.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0 ) {
                    textIp_passwordAgain_regis.setError("Passwrord is required");
                    textIp_passwordAgain_regis.setErrorEnabled(true);
                } else if (text.length() < 5 ) {
                    textIp_passwordAgain_regis.setError("Passwrord is required and length must be >= 5");
                    textIp_passwordAgain_regis.setErrorEnabled(true);
                } else {
                    textIp_passwordAgain_regis.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
}