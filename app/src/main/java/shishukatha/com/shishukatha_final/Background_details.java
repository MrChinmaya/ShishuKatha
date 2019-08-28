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

/**
 * Created by Ted on 9/13/2017.
 */

public class Background_details extends AsyncTask <String,Void,String > {

    Context ctx;
    Background_details(Context ctx){

        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String regurl="http://demosk.hignosisit.com/register.php";
        String name=params[0];
        String age=params[1];
        String children_number=params[2];
        String details=params[3];
        String email=params[4];
        String userid=params[5];

        try {
            URL url=new URL(regurl);
            HttpURLConnection httpURLConnection = null;
            httpURLConnection=(HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data =URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(userid,"UTF-8")+"&"+
                    URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                    URLEncoder.encode("childage","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"+
                    URLEncoder.encode("childnumber","UTF-8")+"="+URLEncoder.encode(children_number,"UTF-8")+"&"+
                    URLEncoder.encode("details","UTF-8")+"="+URLEncoder.encode(details,"UTF-8")+"&"+
                    URLEncoder.encode("usercontactdetails","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");

            System.out.println(data);
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS=httpURLConnection.getInputStream();
            IS.close();
            return "Registration Success";

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

    }
}
