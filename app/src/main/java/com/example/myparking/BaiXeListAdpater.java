package com.example.myparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myparking.data.model.BaiXe;

import java.util.List;

public class BaiXeListAdpater extends BaseAdapter {
    private List<BaiXe> baiXeList;
    private LayoutInflater layoutInflater;
    private Context context;

    public BaiXeListAdpater(Context context, List<BaiXe> baiXeList){
        this.context = context;
        this.baiXeList = baiXeList;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
         return baiXeList.size();
    }

    @Override
    public Object getItem(int position) {
        return baiXeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.tv_li_tenbx = (TextView) convertView.findViewById(R.id.tv_li_ten_bx);
            holder.tv_li_diachi = (TextView) convertView.findViewById(R.id.tv_li_diachi);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder)convertView.getTag();
        BaiXe baiXe = this.baiXeList.get(position);
        holder.tv_li_tenbx.setText(baiXe.getTenBx());
        holder.tv_li_diachi.setText(baiXe.getDiachi());
        return convertView;
    }
    static class ViewHolder {
        TextView tv_li_tenbx;
        TextView tv_li_diachi;
    }
}
