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
import com.example.myparking.data.BaiXeSQLHandler;
import com.example.myparking.data.KhachHangSQLHandler;
import com.example.myparking.data.RoleSQLHandler;
import com.example.myparking.data.SQLHandler;
import com.example.myparking.data.TaiKhoanSQLHandler;

public class BaiXeDetail_admin extends AppCompatActivity {
    TextView tenbX;
    TextView diachi;
    TextView starTime, finishTime,price, description;
    Button btn_update_baixe, btn_delete_baixe;
    SQLHandler sqlHandler;
    BaiXeSQLHandler baiXeSQLHandler;
    TaiKhoanSQLHandler taiKhoanSQLHandler;
    KhachHangSQLHandler khachHangSQLHandler;
    RoleSQLHandler roleSQLHandler;
    Button btn_goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_xe_detail_admin);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        baiXeSQLHandler = new BaiXeSQLHandler();
        taiKhoanSQLHandler = new TaiKhoanSQLHandler();
        khachHangSQLHandler = new KhachHangSQLHandler();
        roleSQLHandler = new RoleSQLHandler();
        sqlHandler = new SQLHandler(this, baiXeSQLHandler, taiKhoanSQLHandler, khachHangSQLHandler, roleSQLHandler);
        btn_update_baixe = findViewById(R.id.btn_update_baixe);
        btn_delete_baixe = findViewById(R.id.btn_xoa_baixe);
        btn_goback = findViewById(R.id.btn_goback);

        tenbX = findViewById(R.id.tv_tenbxchitiet);
        diachi = findViewById(R.id.tv_diachichitiet);
        starTime = findViewById(R.id.tv_starttime);
        finishTime = findViewById(R.id.tv_finishtime);
        description = findViewById(R.id.tv_description);
        price = findViewById(R.id.tv_cost);
        Bundle bundle =this.getIntent().getExtras();
        tenbX.setText(bundle.getString("tenbaixe"));
        diachi.setText(bundle.getString("diachi"));
        starTime.setText(bundle.getString("startTime"));
        finishTime.setText((bundle.getString("finishTime")));
        price.setText(bundle.getDouble("price") + "");
        description.setText("- Số lượng còn trống: "+bundle.getInt("soluongtrong") + "/" + bundle.getInt("soluonggiuxe") + "" +
                "/n" + bundle.getString("description"));
        btn_delete_baixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaiXeDetail_admin.this, QuanLyBaiXe_admin.class);
                sqlHandler.deleteBaiXe(bundle.getInt("mabx"));
                startActivity(i);
            }
        });
        btn_update_baixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaiXeDetail_admin.this, UpdateBaiXe_admin.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        btn_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaiXeDetail_admin.this, QuanLyBaiXe_admin.class);
                startActivity(i);
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