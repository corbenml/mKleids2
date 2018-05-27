package comben.mkleids.com.mkleids;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.mkleids.adapter.email_head_adapter;
import com.mkleids.dao.email_received_headDao;
import com.mkleids.bean.email_received_head;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link email_content_asmail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link email_content_asmail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class email_content_asmail extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listview;
    private String sender;
    private String shopcr;
    private List<String> listMessageid;
    private List<String> senders;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public email_content_asmail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment email_content_asmail.
     */
    // TODO: Rename and change types and number of parameters
    public static email_content_asmail newInstance(String param1, String param2) {
        email_content_asmail fragment = new email_content_asmail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.email_content_asemails,null);
        shopcr = getActivity().getIntent().getStringExtra("shopcr");
        sender = getActivity().getIntent().getStringExtra("sender");
        listMessageid = new ArrayList<String>();
        senders = new ArrayList<String>();
        View view_item = inflater.inflate(R.layout.email_head_item,null);
        listview = (ListView)view.findViewById(R.id.email_content_asemail_listview);
        TextView email_left_fragment_text = (TextView)view.findViewById(R.id.email_left_fragment_text);
        Log.d("listv",""+listview);
        // String sender = getActivity().getIntent().getExtras().getString("sender","corben");
        String sqlstr = "";
        String[] args;
            sqlstr = "select * from email_receive_header where Sender=? and shop_cr=? order by _id desc";
            args=new String[]{sender,shopcr};

        listview.setOnItemClickListener(this);
        email_received_headDao emd = new email_received_headDao();
        Cursor cursor = emd.getHeader(sqlstr,args);
        // SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.email_head_item,cursor,
        //       new String[]{"subject","Sender","ReceiveDate"},new int[]{R.id.email_head_title,R.id.email_head_sender,R.id.email_head_received_date}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        email_head_adapter adapter = new email_head_adapter(getActivity());
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            do{
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
