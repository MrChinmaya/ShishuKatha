package shishukatha.com.shishukatha_final;

import android.content.Context;
import android.os.AsyncTask;
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
 * Created by Ted on 9/29/2017.
 */

public class BackgroundStoryLabelCheck extends AsyncTask<String, Void, String> {

    Context ctx;
    BackgroundStoryLabelCheck(Context ctx){
        System.out.println("Statement 5");
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String regurl="http://demosk.hignosisit.com/checklabel.php";

        String userid=params[0];
        String storyid=params[1];

        try {
            URL url=new URL(regurl);
            HttpURLConnection httpURLConnection = null;
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data = URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(userid,"UTF-8")+"&"+
                    URLEncoder.encode("storyid","UTF-8")+"="+URLEncoder.encode(storyid,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            httpURLConnection.disconnect();

            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            InputStream input = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            input.close();
            return (result.toString());
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
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
