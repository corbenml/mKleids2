package comben.mkleids.com.mkleids;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link email_content_asorder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class email_content_asorder extends Fragment {
    private String sender;
    private String shopcr;
    private OnFragmentInteractionListener mListener;

    public email_content_asorder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.email_content_asorders,null);
        sender = getActivity().getIntent().getStringExtra("sender");
        shopcr = getActivity().getIntent().getStringExtra("shopcr");
        WebView webView = view.findViewById(R.id.email_content_asorder_webview);
        webView.loadUrl("http://101.132.99.78:8080/WebServiceProject/android/asorders.jsp?sender="+sender+"&&shop_cr="+shopcr);
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
}
