package com.example.myparking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myparking.BaiXeListAdpater;
import com.example.myparking.R;
import com.example.myparking.data.BaiXeSQLHandler;
import com.example.myparking.data.KhachHangSQLHandler;
import com.example.myparking.data.RoleSQLHandler;
import com.example.myparking.data.SQLHandler;
import com.example.myparking.data.TaiKhoanSQLHandler;
import com.example.myparking.data.model.BaiXe;

import java.util.List;

public class QuanLyBaiXe_admin extends AppCompatActivity {
    SQLHandler sqlHandler;
    BaiXeSQLHandler baiXeSQLHandler;
    TaiKhoanSQLHandler taiKhoanSQLHandler;
    KhachHangSQLHandler khachHangSQLHandler;
    RoleSQLHandler roleSQLHandler;
    ListView listView;
    Button btn_update_baixe, btn_delete_baixe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_bai_xe_admin);
        baiXeSQLHandler = new BaiXeSQLHandler();
        taiKhoanSQLHandler = new TaiKhoanSQLHandler();;
        khachHangSQLHandler = new KhachHangSQLHandler();
        roleSQLHandler = new RoleSQLHandler();
        sqlHandler = new SQLHandler(QuanLyBaiXe_admin.this, baiXeSQLHandler, taiKhoanSQLHandler, khachHangSQLHandler, roleSQLHandler);

        List<BaiXe> baiXeList = sqlHandler.getAllBaiXe();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter( new BaiXeListAdpater(this, baiXeList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiXe baiXe = (BaiXe) listView.getItemAtPosition(position);
                Intent i = new Intent(QuanLyBaiXe_admin.this, BaiXeDetail_admin.class);
                Bundle bundle = new Bundle();
                bundle.putInt("mabx", baiXe.getMabx());
                bundle.putString("tenbaixe", baiXe.getTenBx());
                bundle.putString("diachi", baiXe.getDiachi());
                bundle.putString("startTime", baiXe.getStartTime());
                bundle.putString("finishTime", baiXe.getFinishTime());
                bundle.putDouble("price", baiXe.getPrice());
                bundle.putString("description", baiXe.getDescription());
                bundle.putInt("soluongtrong", baiXe.getSoluongtrong());
                bundle.putInt("soluonggiuxe", baiXe.getSoluonggiuxe());
                bundle.putDouble("latitude", baiXe.getKinhdo());
                bundle.putDouble("longitude", baiXe.getVido());
                bundle.putString("sdt" , baiXe.getSdt());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}