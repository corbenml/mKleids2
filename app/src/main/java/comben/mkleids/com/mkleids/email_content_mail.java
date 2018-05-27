package comben.mkleids.com.mkleids;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class email_content_mail extends Fragment {
    @Nullable
    public String MessageID;
    public String shopcr;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.email_content_mail,null);
        TextView textView = view.findViewById(R.id.this_mail_messageID);
        MessageID = getActivity().getIntent().getStringExtra("messageid");
        shopcr = getActivity().getIntent().getStringExtra("shopcr");
        textView.setText(MessageID);
        WebView webView = view.findViewById(R.id.email_content_vewview);
        String str = "<table><tr><td>aaa</td><td>bbb</td><td><a href='http://www.baidu.com'>baidu</a></td></tr></table>";
        //webView.loadData(getHtmlData(str),"text/html; charset=UTF-8",null); //string 数据源加载
        webView.loadUrl("http://101.132.99.78:8080/email/content.jsp?MessageID="+MessageID+"&&shop_cr="+shopcr);
        return view;
    }
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
