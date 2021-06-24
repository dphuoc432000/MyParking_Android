package com.example.myparking.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myparking.R;

public class BaiXeDetailActivity extends AppCompatActivity {

    TextView tenbX;
    TextView diachi;
    TextView starTime, finishTime,price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_xe_detail2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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