package com.example.myparking.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myparking.R;
import com.example.myparking.data.BaiXeSQLHandler;
import com.example.myparking.data.KhachHangSQLHandler;
import com.example.myparking.data.RoleSQLHandler;
import com.example.myparking.data.SQLHandler;
import com.example.myparking.data.TaiKhoanSQLHandler;
import com.example.myparking.data.model.BaiXe;

public class UpdateBaiXe_admin extends AppCompatActivity {

    EditText tenbx, diachi, starttime, finishtime, soluongtrong, soluonggiuxe,sdt, latitude, longitude, price, description;
    Button btn_update_baixe;
    BaiXeSQLHandler baiXeSQLHandler;
    TaiKhoanSQLHandler taiKhoanSQLHandler;
    KhachHangSQLHandler khachHangSQLHandler;
    RoleSQLHandler roleSQLHandler;
    SQLHandler sqlHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bai_xe_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);;
        baiXeSQLHandler = new BaiXeSQLHandler();
        taiKhoanSQLHandler = new TaiKhoanSQLHandler();
        khachHangSQLHandler = new KhachHangSQLHandler();
        roleSQLHandler = new RoleSQLHandler();
        sqlHandler = new SQLHandler(this,baiXeSQLHandler, taiKhoanSQLHandler, khachHangSQLHandler, roleSQLHandler);
        tenbx = findViewById(R.id.editT_tenbx);
        diachi = findViewById(R.id.editT_diachi);
        starttime = findViewById(R.id.editT_startTime);
        finishtime = findViewById(R.id.editT_finishTime);
        soluongtrong = findViewById(R.id.editT_soluongtrong);
        soluonggiuxe = findViewById(R.id.editT_soluonggiuxe);
        sdt = findViewById(R.id.editT_sdt);
        latitude = findViewById(R.id.editT_latitude);
        longitude = findViewById(R.id.editT_longitude);
        price = findViewById(R.id.editT_price);
        description = findViewById(R.id.editT_description);
        btn_update_baixe = findViewById(R.id.btn_update_account);

        Bundle bundle = this.getIntent().getExtras();
        tenbx.setText(bundle.getString("tenbaixe"));
        diachi.setText(bundle.getString("diachi"));
        starttime.setText(bundle.getString("startTime"));
        finishtime.setText((bundle.getString("finishTime")));
        soluongtrong.setText(bundle.getInt("soluongtrong") + "");
        soluonggiuxe.setText(bundle.getInt("soluonggiuxe") + "");
        sdt.setText(bundle.getString("sdt"));
        latitude.setText(bundle.getDouble("latitude") + "");
        longitude.setText(bundle.getDouble("longitude") + "");
        price.setText(bundle.getDouble("price") + "");
        description.setText(bundle.getString("description"));

        btn_update_baixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiXe baiXe = new BaiXe(bundle.getInt("mabx"), tenbx.getText().toString(), diachi.getText().toString(), Integer.parseInt(soluonggiuxe.getText().toString()), Integer.parseInt(soluongtrong.getText().toString()), Double.parseDouble(latitude.getText().toString()), Double.parseDouble(longitude.getText().toString()), sdt.getText().toString(), description.getText().toString(), "", starttime.getText().toString(), finishtime.getText().toString(), Double.parseDouble(price.getText().toString()));
                sqlHandler.updateBaiXe(baiXe, bundle.getInt("mabx"));
                Intent i = new Intent(UpdateBaiXe_admin.this, QuanLyBaiXe_admin.class);
//                Bundle bundle1 = new Bundle();
//                bundle1.putInt("mabx", baiXe.getMabx());
//                bundle1.putString("tenbaixe", baiXe.getTenBx());
//                bundle1.putString("diachi", baiXe.getDiachi());
//                bundle1.putString("startTime", baiXe.getStartTime());
//                bundle1.putString("finishTime", baiXe.getFinishTime());
//                bundle1.putDouble("price", baiXe.getPrice());
//                bundle1.putString("description", baiXe.getDescription());
//                bundle1.putInt("soluongtrong", baiXe.getSoluongtrong());
//                bundle1.putInt("soluonggiuxe", baiXe.getSoluonggiuxe());
//                bundle1.putDouble("latitude", baiXe.getKinhdo());
//                bundle1.putDouble("longitude", baiXe.getVido());
//                bundle1.putString("sdt" , baiXe.getSdt());
//                i.putExtras(bundle1);
                Toast.makeText(getApplicationContext(),"Successful parking update", Toast.LENGTH_LONG).show();

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