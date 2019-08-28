package shishukatha.com.shishukatha_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class Manage_App_Rates extends AppCompatActivity {


    String urlAddress="http://192.168.1.14/demosk/showratings.php";
    ListView lv;
    ArrayAdapter<String> adapter;
    InputStream is=null;
    String line=null;
    String result=null;
    String[] data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__app__rates);
        lv=findViewById(R.id.listview1);
        //new Downloader(Manage_App_Rates.this,urlAddress,lv).execute();
    }
    private  void  getData(){
        try {
            URL url=new URL(urlAddress);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());



        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine()) != null){
                sb.append(line +"\n");
            }
            is.close();
            result=sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
            JSONArray js=new JSONArray(result);
            JSONObject jo=null;

            data=new String[js.length()];

            for (int i=0;i<js.length();i++){

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
