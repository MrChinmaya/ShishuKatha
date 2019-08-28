package shishukatha.com.shishukatha_final;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;
import com.spark.submitbutton.SubmitButton;

import es.dmoral.toasty.Toasty;

public class Feedback extends AppCompatActivity {

    //ImageButton submit;
    public int smilerate=0;
    public String storyid,sid,ratings,rates,uid,userid,apprate;
    public TextView feedbackTitle;
    public SmileRating smileRating;
    SubmitButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        android.support.v7.app.ActionBar AB=getSupportActionBar();//To remove title bar
//        AB.hide();// remove title
        setContentView(R.layout.activity_feedback);

        storyid= getIntent().getStringExtra("data");
        ratings=getIntent().getExtras().getString("Ratings","apprating");
        userid= getIntent().getExtras().getString("userid","default");

        System.out.println("Feedback"+storyid);
        System.out.println("Feedback"+ratings);
        System.out.println("Feedback"+userid);



        feedbackTitle=(TextView)findViewById(R.id.feedbacktitle);
        smileRating = (SmileRating) findViewById(R.id.smile_rating);
       // submit=(ImageButton)findViewById(R.id.submitid);
        submit=findViewById(R.id.submitid);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/LobstertwoboldItalic.otf");
        feedbackTitle.setTypeface(typeface);

        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                // level is from 1 to 5 (0 when none selected)
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                 smilerate= smileRating.getRating();
                 apprate=String.valueOf(ratings);
                //Toast.makeText(Feedback.this, "Rated " + smileRating.getRating(), Toast.LENGTH_SHORT).show();

            }
        });

        if (smilerate == 0){
            apprate="";
        }


//        final Intent feed = new Intent(Feedback.this,StoryMenu.class);
//        submit.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//                feedback(view);
//                //Toast.makeText(Feedback.this, "Rated " + smileRating.getRating(), Toast.LENGTH_SHORT).show();
//              //  Toasty.info(getApplicationContext(), "Rated "+ smileRating.getRating(), Toast.LENGTH_SHORT).show();
//                startActivity(feed);
//            }});


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        feedback(v);
                        Intent i=new Intent(Feedback.this,StoryMenu.class);
                        startActivity(i);
                    }
                }, 3000);
            }
        });


    }

    public void feedback(View view  ){
        uid=userid.toString();
        System.out.println("Feedback userid"+uid);
        sid=storyid.toString();
        rates=ratings.toString();


        Backgroundfeedback backgroundfeedback=new Backgroundfeedback(this);
        backgroundfeedback.execute(uid,sid,rates,apprate);
        //  finish();
    }

    @Override
    public void onPause(){
        super.onPause();
        storyid=null;
        ratings=null;
       // apprate="";
        //uid=null;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Feedback.this, StoryMenu.class));
        finish();

    }


    }

