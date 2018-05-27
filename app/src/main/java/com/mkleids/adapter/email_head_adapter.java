package com.mkleids.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mkleids.bean.email_received_head;
import com.mkleids.view.email_header_view;

import java.util.ArrayList;
import java.util.List;

import comben.mkleids.com.mkleids.R;

public class email_head_adapter extends BaseAdapter {
    private final String TAG = "email_head_adapter";
    private Context mContext;
    public List<email_received_head> mItmes = new ArrayList<email_received_head>();

    public email_head_adapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mItmes.size();
    }

    public void add(email_received_head e){
        mItmes.add(e);
    }
    @Override
    public Object getItem(int position) {
        return mItmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        email_header_view itemView;
        if(convertView==null){
            itemView = new email_header_view(mContext);
        }else
            itemView = (email_header_view)convertView;

        if (position%2 == 0)
            itemView.setBackgroundColor(Color.parseColor("#EAEAEA"));
        else
            itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        try {
            email_received_head emh = mItmes.get(position);
            itemView.setmSender(emh.getSender()+"  ["+emh.getShop_cr()+"店]");
            itemView.setmTitle(emh.getSubject());
            itemView.setmReceivedDateTime(emh.getReceiveDate());
            itemView.setmEmailStatus(emh.getSender(),emh.getReaded(),emh.getReply());
        } catch (Exception e) {
            Log.d(TAG, "Exception get view");
            e.printStackTrace();
        }
        return itemView;
    }
}
