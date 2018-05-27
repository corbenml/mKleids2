package com.mkleids.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import comben.mkleids.com.mkleids.R;

public class email_header_view extends LinearLayout {
   private TextView mSender;
   private TextView mTitle;
   private TextView mReceivedDateTime;
   private ImageView mEmailStatus;
   private View mView;

    public email_header_view(Context context) {
        super(context);
       // this.mView=mView;
//        LayoutInflater mInflater = LayoutInflater.from(context);
//        View myView = mInflater.inflate(R.layout.email_head_item, null);
//        mSender = (TextView) mView.findViewById(R.id.email_head_sender);
//        mTitle = (TextView) mView.findViewById(R.id.email_head_title);
//        mReceivedDateTime = (TextView) mView.findViewById(R.id.email_head_received_date);
//        mEmailStatus = (ImageView) mView.findViewById(R.id.email_head_status_icon);
//2种方法调用inflater
       LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.email_head_item,this,true);
                mSender = (TextView) findViewById(R.id.email_head_sender);
                mTitle = (TextView) findViewById(R.id.email_head_title);
                mReceivedDateTime = (TextView) findViewById(R.id.email_head_received_date);
                mEmailStatus = (ImageView) findViewById(R.id.email_head_status_icon);
        this.setBackgroundColor(getResources().getColor(R.color.gray2));
    }

    public void setmSender(String sender) {
        mSender.setText(sender);
    }

    public void setmTitle(String title) {
        mTitle.setText(title);
    }

    public void setmReceivedDateTime(String receivedDateTime) {
        mReceivedDateTime.setText(receivedDateTime);
    }

    public void setmEmailStatus(String sender,String read,int reply) {
        if("eBay".equals(sender)) {
            mEmailStatus.setImageResource(R.drawable.ebay);
            if("No".equals(read)) {
                mTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mTitle.setTextSize(18);
                mSender.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mReceivedDateTime.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            }else{
                mTitle.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mTitle.setTextSize(16);
                mSender.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mReceivedDateTime.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        }
        else {
            if ("No".equals(read)) {
                mTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mTitle.setTextSize(18);
                mSender.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mReceivedDateTime.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mEmailStatus.setImageResource(R.drawable.unread);
            } else if (reply==1) {
                mEmailStatus.setImageResource(R.drawable.reply);
                mTitle.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mTitle.setTextSize(16);
                mSender.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mReceivedDateTime.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
            else {
                mEmailStatus.setImageResource(R.drawable.readed);
                mTitle.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mTitle.setTextSize(18);
                mSender.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mReceivedDateTime.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        }
    }
    public void setBackColor(int i){
        if(i%2==0)
           this.setBackgroundColor(getResources().getColor(R.color.gray));
        else
            this.setBackgroundColor(getResources().getColor(R.color.gray2));
    }
}
