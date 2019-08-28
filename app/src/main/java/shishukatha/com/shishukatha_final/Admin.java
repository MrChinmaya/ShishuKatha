package shishukatha.com.shishukatha_final;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Admin extends AppCompatActivity implements AsyncResponce{

    Story1rating story1rating=new Story1rating(Admin.this);
    Story2rating story2rating=new Story2rating(Admin.this);
    Story3rating story3rating=new Story3rating(Admin.this);
    Story4rating story4rating=new Story4rating(Admin.this);
    Story5rating story5rating=new Story5rating(Admin.this);
    Story6rating story6rating=new Story6rating(Admin.this);
    Story7rating story7rating=new Story7rating(Admin.this);
    Story8rating story8rating=new Story8rating(Admin.this);

    ImageButton view_as_user,showrating;
    public String story1rate, story2rate, story3rate, story4rate, story5rate, story6rate, story7rate, story8rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        android.support.v7.app.ActionBar AB=getSupportActionBar();//To remove title bar
//        AB.hide();// remove title
        setContentView(R.layout.activity_admin);

        showrating=findViewById(R.id.showrating);
        view_as_user=findViewById(R.id.viewuser);

        story1rating.delegate= this;
        story1rating.execute("storyratin");

        story2rating.delegate= this;
        story2rating.execute("storyratin");

        story3rating.delegate= this;
        story3rating.execute("storyratin");

        story4rating.delegate= this;
        story4rating.execute("storyratin");

        story5rating.delegate= this;
        story5rating.execute("storyratin");

        story6rating.delegate= this;
        story6rating.execute("storyratin");

        story7rating.delegate= this;
        story7rating.execute("storyratin");

        story8rating.delegate= this;
        story8rating.execute("storyratin");

        view_as_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loaduser=new Intent(Admin.this,MainActivity.class);
                startActivity(loaduser);
            }
        });

    }


    @Override
    public void processFinish(final String output){
        System.out.println("The fetched output is"+output);
        if(output.contains("Storyone")){
            System.out.println("Yes it contains1");
            System.out.println(output.charAt(0));
            Character s1=output.charAt(0);
            story1rate=String.valueOf(s1);
        }
        else if(output.contains("Storytwo")){
            System.out.println("Yes it contains2");
            System.out.println(output.charAt(0));
            Character s2=output.charAt(0);
            story2rate=String.valueOf(s2);
        }
        else if(output.contains("Storythree")){
            System.out.println("Yes it contains3");
            System.out.println(output.charAt(0));
            Character s3=output.charAt(0);
            story3rate=String.valueOf(s3);
        }
        else if(output.contains("Storyfour")){
            System.out.println("Yes it contains4");
            System.out.println(output.charAt(0));
            Character s4=output.charAt(0);
            story4rate=String.valueOf(s4);
        }
        else if(output.contains("Storyfive")){
            System.out.println("Yes it contains5");
            System.out.println(output.charAt(0));
            Character s5=output.charAt(0);
            story5rate=String.valueOf(s5);
        }
        else if(output.contains("Storysix")){
            System.out.println("Yes it contains6");
            System.out.println(output.charAt(0));
            Character s6=output.charAt(0);
            story6rate=String.valueOf(s6);
        }
        else if(output.contains("Storyseven")){
            System.out.println("Yes it contains7");
            System.out.println(output.charAt(0));
            Character s7=output.charAt(0);
            story7rate=String.valueOf(s7);
        }
        else if(output.contains("Storyeight")){
            System.out.println("Yes it contains8");
            System.out.println(output.charAt(0));
            Character s8=output.charAt(0);
            story8rate=String.valueOf(s8);
        }
        showrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_intent = new Intent(Admin.this, ShowRating.class);
                System.out.println("values are"+story1rate+story2rate+story3rate+story4rate+story5rate+story6rate+story7rate+story8rate);
                home_intent.putExtra("Story1rate",story1rate);
                home_intent.putExtra("Story2rate",story2rate);
                home_intent.putExtra("Story3rate",story3rate);
                home_intent.putExtra("Story4rate",story4rate);
                home_intent.putExtra("Story5rate",story5rate);
                home_intent.putExtra("Story6rate",story6rate);
                home_intent.putExtra("Story7rate",story7rate);
                home_intent.putExtra("Story8rate",story8rate);
                startActivity(home_intent);
            }
        });

    }


    //Check ratings

    public class Story1rating extends AsyncTask<String, Void, String> {
        public AsyncResponce delegate = null;
        public String line1,result1;
        Context ctx1;

        public Story1rating(Context ctx){
            this.ctx1=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("Connection success to db");
            String storyid=params[0];
            System.out.println(storyid);

            String regurl="http://192.168.1.14/demosk/showratings.php?storyid="+storyid;

            try {
                URL url=new URL(regurl);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                System.out.println("Statement 11");
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Statement 12");
                line1=reader.readLine();
                result1=line1;
                System.out.println("The result is "+line1);
                input.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result1;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Statement 15");
            System.out.println("PostExecute"+result);
            delegate.processFinish(result+"Storyone");
        }
    }

    public class Story2rating extends AsyncTask<String, Void, String> {
        public AsyncResponce delegate = null;
        public String line2,result2;
        Context ctx2;

        public Story2rating(Context ctx){
            this.ctx2=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("Connection success to db");
            String storyid=params[0];
            System.out.println(storyid);

            String regurl="http://192.168.1.14/demosk/showratings.php?storyid="+storyid;

            try {
                URL url=new URL(regurl);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                System.out.println("Statement 11");
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Statement 12");
                line2=reader.readLine();
                result2=line2;
                System.out.println("The result is "+line2);
                input.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result2;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Statement 15");
            System.out.println("PostExecute"+result);
            delegate.processFinish(result+"Storytwo");
        }
    }

    public class Story3rating extends AsyncTask<String, Void, String> {
        public AsyncResponce delegate = null;
        public String line3,result3;
        Context ctx3;

        public Story3rating(Context ctx){
            this.ctx3=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("Connection success to db");
            String storyid=params[0];
            System.out.println(storyid);

            String regurl="http://192.168.1.14/demosk/showratings.php?storyid="+storyid;

            try {
                URL url=new URL(regurl);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                System.out.println("Statement 11");
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Statement 12");
                line3=reader.readLine();
                result3=line3;
                System.out.println("The result is "+line3);
                input.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result3;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Statement 15");
            System.out.println("PostExecute"+result);
            delegate.processFinish(result+"Storythree");
        }
    }

    public class Story4rating extends AsyncTask<String, Void, String> {
        public AsyncResponce delegate = null;
        public String line4,result4;
        Context ctx4;

        public Story4rating(Context ctx){
            this.ctx4=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("Connection success to db");
            String storyid=params[0];
            System.out.println(storyid);

            String regurl="http://192.168.1.14/demosk/showratings.php?storyid="+storyid;

            try {
                URL url=new URL(regurl);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                System.out.println("Statement 11");
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Statement 12");
                line4=reader.readLine();
                result4=line4;
                System.out.println("The result is "+line4);
                input.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result4;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Statement 15");
            System.out.println("PostExecute"+result);
            delegate.processFinish(result+"Storyfour");
        }
    }

    public class Story5rating extends AsyncTask<String, Void, String> {
        public AsyncResponce delegate = null;
        public String line5,result5;
        Context ctx5;

        public Story5rating(Context ctx){
            this.ctx5=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("Connection success to db");
            String storyid=params[0];
            System.out.println(storyid);

            String regurl="http://192.168.1.14/demosk/showratings.php?storyid="+storyid;

            try {
                URL url=new URL(regurl);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                System.out.println("Statement 11");
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Statement 12");
                line5=reader.readLine();
                result5=line5;
                System.out.println("The result is "+line5);
                input.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result5;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Statement 15");
            System.out.println("PostExecute"+result);
            delegate.processFinish(result+"Storyfive");
        }
    }

    public class Story6rating extends AsyncTask<String, Void, String> {
        public AsyncResponce delegate = null;
        public String line6,result6;
        Context ctx6;

        public Story6rating(Context ctx){
            this.ctx6=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("Connection success to db");
            String storyid=params[0];
            System.out.println(storyid);

            String regurl="http://192.168.1.14/demosk/showratings.php?storyid="+storyid;

            try {
                URL url=new URL(regurl);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                System.out.println("Statement 11");
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Statement 12");
                line6=reader.readLine();
                result6=line6;
                System.out.println("The result is "+line6);
                input.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result6;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Statement 15");
            System.out.println("PostExecute"+result);
            delegate.processFinish(result+"Storysix");
        }
    }

    public class Story7rating extends AsyncTask<String, Void, String> {
        public AsyncResponce delegate = null;
        public String line7,result7;
        Context ctx7;

        public Story7rating(Context ctx){
            this.ctx7=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("Connection success to db");
            String storyid=params[0];
            System.out.println(storyid);

            String regurl="http://192.168.1.14/demosk/showratings.php?storyid="+storyid;

            try {
                URL url=new URL(regurl);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                System.out.println("Statement 11");
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Statement 12");
                line7=reader.readLine();
                result7=line7;
                System.out.println("The result is "+line7);
                input.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result7;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Statement 15");
            System.out.println("PostExecute"+result);
            delegate.processFinish(result+"Storyseven");
        }
    }

    public class Story8rating extends AsyncTask<String, Void, String> {
        public AsyncResponce delegate = null;
        public String line8,result8;
        Context ctx8;

        public Story8rating(Context ctx){
            this.ctx8=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("Connection success to db");
            String storyid=params[0];
            System.out.println(storyid);

            String regurl="http://192.168.1.14/demosk/showratings.php?storyid="+storyid;

            try {
                URL url=new URL(regurl);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                System.out.println("Statement 11");
                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Statement 12");
                line8=reader.readLine();
                result8=line8;
                System.out.println("The result is "+line8);
                input.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result8;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Statement 15");
            System.out.println("PostExecute"+result);
            delegate.processFinish(result+"Storyeight");
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Admin.this, MainActivity.class));
        finish();

    }
}
