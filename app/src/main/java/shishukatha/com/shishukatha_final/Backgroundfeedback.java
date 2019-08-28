package shishukatha.com.shishukatha_final;

import android.content.Context;
import android.os.AsyncTask;
import android.system.Os;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import es.dmoral.toasty.Toasty;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Ted on 9/14/2017.
 */

public class Backgroundfeedback extends AsyncTask <String,Void,String > {

    Context ctx;
    public Backgroundfeedback(Context ctx){

    this.ctx=ctx;
    }
        @Override
        protected void onPreExecute() {
        super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
        String regurl="http://demosk.hignosisit.com/managefeedback.php";

            String userid=params[0];
            String Storyid=params[1];
            String storycontentrating=params[2];
            String apprate=params[3];

        try {
        URL url=new URL(regurl);
        HttpURLConnection httpURLConnection = null;
        httpURLConnection=(HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        OutputStream OS=httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

        String data = URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(userid,"UTF-8")+"&"+
                URLEncoder.encode("Storyid","UTF-8")+"="+URLEncoder.encode(Storyid,"UTF-8")+"&"+
                URLEncoder.encode("storycontentrating","UTF-8")+"="+URLEncoder.encode(storycontentrating,"UTF-8")+"&"+
                URLEncoder.encode("apprate","UTF-8")+"="+URLEncoder.encode(apprate,"UTF-8");

        System.out.println(data);
        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.close();
        OS.close();
        InputStream IS=httpURLConnection.getInputStream();
        System.out.println(data);
        IS.close();
        return "Thankyou for your Feedback";

        }
        catch (MalformedURLException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }

        return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
        //Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
            Toasty.success(ctx, result, Toast.LENGTH_SHORT).show();
        }
}
