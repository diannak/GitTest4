package comstudy.android.ex31_http01;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView tvHtml;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_get);

        tvHtml = findViewById(R.id.tvHtml2);
        webView = findViewById(R.id.webView2);
    }
    public void onBtnGet(View v){
        String sUrl = getString(R.string.server_addr) + "/Go_Palace/test.jsp";

        GetAction myGetAct = new GetAction();
        myGetAct.execute(sUrl);
    }
    public void onBtnFinish(View v){finish();}

    class GetAction extends AsyncTask<String, Integer, String>{
        protected void onPreExecute(){
        }

        protected String doInBackground(String... value){
            String sOutput = "";
            StringBuilder output = new StringBuilder();
            try{
                URL url = new URL(value[0].toString());
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                Log.d(TAG, "sUrl:"+value[0].toString());

                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    int resCode = conn.getResponseCode();
                    if(resCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(conn.getInputStream()));
                        String line = null;
                        while(true){
                            line = reader.readLine();
                            if(line == null){
                                break;
                            }
                            output.append(line + "\n");
                        }
                        reader.close();
                        conn.disconnect();

                        Log.d(TAG,output.toString());
                        sOutput = output.toString();
                    }else{

                    }
                }else{

                }
            }catch(Exception ex){

            }
            return  sOutput;
        }
        protected void onProgressUpdate(Integer...value){

        }
        protected void onPostExecute(String result){
            tvHtml.setText(result);
            webView.loadData(result, "text/html; charset=UTF-8",null);
        }
    }
}