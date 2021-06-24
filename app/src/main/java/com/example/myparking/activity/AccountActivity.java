package com.example.myparking.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myparking.R;

public class AccountActivity extends AppCompatActivity {

    Button btn_goback;
    Button btn_update_account;
    TextView tv_username, tv_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        tv_username = findViewById(R.id.tv_username_acc);
        tv_password = findViewById(R.id.tv_password_acc);
        btn_goback = findViewById(R.id.btn_goback_map);
        btn_update_account = findViewById(R.id.btn_update_acc);
        Bundle bundle = this.getIntent().getExtras();
        tv_password.setText(bundle.getString("password"));
        tv_username.setText(bundle.getString("username"));
        btn_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        btn_update_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, UpdateAccount.class);
                intent.putExtras(bundle);
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