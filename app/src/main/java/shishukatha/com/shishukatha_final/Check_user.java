package shishukatha.com.shishukatha_final;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Ted on 10/4/2017.
 */

public class Check_user extends AsyncTask<String, Void, String> {

    public AsyncResponce delegate = null;
    public String line,result;
    Context ctx;
    Check_user(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String regurl="http://demosk.hignosisit.com/checkuser.php";

        System.out.println("Connection success to db");
        String userid=params[0];
        System.out.println(userid);

        try {
            URL url=new URL(regurl);
            HttpURLConnection httpURLConnection = null;
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data = URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(userid,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            System.out.println("Statement 10");
            httpURLConnection.disconnect();

            httpURLConnection=(HttpURLConnection)url.openConnection();
            System.out.println("Statement 10.1");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            System.out.println("Statement 11");
            InputStream input = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            //StringBuilder result = new StringBuilder();
            result="";
            line="";
            System.out.println("Statement 12");
            while ((line = reader.readLine()) != null) {
                //result.append(line);
                result=result.concat(line);
                System.out.println("The result is "+line);// this line is working fine for fetching data.
            }

            input.close();
            System.out.println("Statement 14");
           // return (result.toString());


//            OutputStream OS=httpURLConnection.getOutputStream();
//            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
//
//            String data = URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(userid,"UTF-8")+"&"+
//                    URLEncoder.encode("storyid","UTF-8")+"="+URLEncoder.encode(storyid,"UTF-8")+"&"+
//                    URLEncoder.encode("readstatus","UTF-8")+"="+URLEncoder.encode(readstatus,"UTF-8");
//
//            System.out.println(data);
//            bufferedWriter.write(data);
//            bufferedWriter.flush();
//            bufferedWriter.close();
//            OS.close();
//            InputStream IS=httpURLConnection.getInputStream();
//            System.out.println(data);
//            IS.close();
//
//            return "SLabels Updated";

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("Statement 15");
        System.out.println("PostExecute"+result);
        delegate.processFinish(result);

    }
}
