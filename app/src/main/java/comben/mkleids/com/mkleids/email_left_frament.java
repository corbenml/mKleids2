package comben.mkleids.com.mkleids;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mkleids.adapter.email_head_adapter;
import com.mkleids.dao.email_received_headDao;
import com.mkleids.bean.email_received_head;

import java.util.ArrayList;
import java.util.List;

public class email_left_frament extends Fragment implements AdapterView.OnItemClickListener {
    @Nullable
    private ListView listview;
    private Context context;
    private String shopcr;
    private String studus;
    private int total;
    private Bundle bundle;
    private List<String> listMessageid;
    private List<String> senders;

    public  int getTotal() {
        return total;
    }

    public void setTotal(int total) {

        this.total = total;
    }

    public void setStudus(String shopcr, String studus,int total) {
        this.studus = studus;
        this.shopcr = shopcr;
        this.total = total;
        bundle = new Bundle();
        String temp = "";
        if("unreply".equals(studus))
            temp="未处理";
        else
        if("ebay".equals(studus))
            temp="eBay系统来信";
        else
            temp="所有邮件";
        bundle.putString("total",temp+"("+total+")");
        this.setArguments(bundle);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static email_left_frament newInstance(String arg) {

        Bundle args = new Bundle();
        args.putString("total",arg);
        email_left_frament fragment = new email_left_frament();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        listMessageid = new ArrayList<String>();
        senders = new ArrayList<String>();
        View view = inflater.inflate(R.layout.email_left_fragment,null);
        View view_item = inflater.inflate(R.layout.email_head_item,null);
        listview = (ListView)view.findViewById(R.id.list_view_emial_left_fragment);
        TextView email_left_fragment_text = (TextView)view.findViewById(R.id.email_left_fragment_text);
        Log.d("listv",""+listview);
       // String sender = getActivity().getIntent().getExtras().getString("sender","corben");
        String sqlstr = "";
           String[] args;
        if("ebay".equals(studus)){
            sqlstr = "select * from email_receive_header where Sender=? and shop_cr=? order by _id desc";
            args=new String[]{"eBay",shopcr};
        }
        else if("unreply".equals(studus)){
            sqlstr = "select * from email_receive_header where Sender!=? and reply=0 and shop_cr=? order by _id desc";
            args=new String[]{"ebay",shopcr};
        }else{
            sqlstr = "select * from email_receive_header where Sender!=? and shop_cr=? order by _id desc";
            args=new String[]{studus,shopcr};
        }

        listview.setOnItemClickListener(this);
        email_received_headDao emd = new email_received_headDao();
        Cursor cursor = emd.getHeader(sqlstr,args);
       // SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.email_head_item,cursor,
         //       new String[]{"subject","Sender","ReceiveDate"},new int[]{R.id.email_head_title,R.id.email_head_sender,R.id.email_head_received_date}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        email_head_adapter adapter = new email_head_adapter(getActivity());
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        do{
            total++;
            String temp_messageid;
            String sender;
            email_received_head emh = new email_received_head();
            sender = cursor.getString(cursor.getColumnIndexOrThrow("Sender"));
            emh.setSender(sender);
            senders.add(sender);
            emh.setReceiveDate(cursor.getString(cursor.getColumnIndexOrThrow("ReceiveDate")));
            emh.setSubject(cursor.getString(cursor.getColumnIndexOrThrow("subject")));
            temp_messageid = cursor.getString(cursor.getColumnIndexOrThrow("messageID"));
            emh.setMessageID(temp_messageid);
            listMessageid.add(temp_messageid);
            emh.setReaded(cursor.getString(cursor.getColumnIndexOrThrow("Readed")));
            emh.setReply(cursor.getInt(cursor.getColumnIndexOrThrow("reply")));
            emh.setShop_cr(cursor.getString(cursor.getColumnIndexOrThrow("shop_cr")));
            adapter.add(emh);
        }while (cursor.moveToNext());
        cursor.close();
        listview.setAdapter(adapter);
        email_left_fragment_text.setText(cursor.getCount()+" 封邮件");
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),EmailActivity.class);
        intent.putExtra("messageid",listMessageid.get(position));
        intent.putExtra("shopcr",shopcr);
        intent.putExtra("sender",senders.get(position));
        getActivity().startActivity(intent);
        Log.d("Click MessageID",listMessageid.get(position));
    }
}
