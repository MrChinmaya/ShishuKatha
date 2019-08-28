package shishukatha.com.shishukatha_final;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.spark.submitbutton.SubmitButton;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;


public class Profile extends AppCompatActivity {

    StepperTouch stepperTouch,stepperTouch1;
    CircleImageView profilepic;
    TextView profilename;
    String Fetchedname,Fetchedmail,profile_pic,userid;
    SubmitButton updateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Fetchedname= getIntent().getStringExtra("Fetchedname");
        Fetchedmail= getIntent().getStringExtra("Fetchedmail");
        userid= getIntent().getStringExtra("userid");
        profile_pic=getIntent().getStringExtra("profile_pic");

        System.out.println("Profile"+Fetchedname);
        System.out.println("Profile"+Fetchedmail);
        System.out.println("Profile"+userid);
        System.out.println("Profile"+profile_pic);

        profilepic=findViewById(R.id.profile_image);
        stepperTouch=findViewById(R.id.stepperTouch);
        stepperTouch1=findViewById(R.id.stepperTouch1);
        profilename=findViewById(R.id.name_profile);
        updateProfile=findViewById(R.id.profile_update);

    //    profilepic.setImageResource(R.drawable.my);
        profilename.setText(Fetchedname);

        if(profile_pic==null){
            profilepic.setImageResource(R.drawable.blankprofile_pic);
        }else{
            Glide.with(Profile.this).load(profile_pic).into(profilepic);
        }

        stepperTouch.stepper.setMin(0);
        stepperTouch.stepper.setMax(5);

        stepperTouch1.stepper.setMin(0);
        stepperTouch1.stepper.setMax(10);

        stepperTouch.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int i, boolean b) {
                String v= String.valueOf(i);
               // Toast.makeText(Profile.this,v,Toast.LENGTH_SHORT).show();
                Toasty.info(getApplicationContext(), v+" Children", Toast.LENGTH_SHORT, false).show();
            }
        });

        stepperTouch1.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int i, boolean b) {
                String v= String.valueOf(i);
                //Toast.makeText(Profile.this,v,Toast.LENGTH_SHORT).show();
                Toasty.info(getApplicationContext(), "Age is "+v, Toast.LENGTH_SHORT, false).show();
            }
        });
//        val stepperTouch = findViewById(R.id.stepperTouch) as StepperTouch
//        stepperTouch.stepper.setMin(0)
//        stepperTouch.stepper.setMax(3)
//        stepperTouch.stepper.addStepCallback(object :OnStepCallback {
//            override fun onStep(value: Int, positive: Boolean) {
//                Toast.makeText(applicationContext, value.toString(), Toast.LENGTH_SHORT).show()
//            }
//        })

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent(Profile.this,StoryMenu.class);
                        startActivity(i);
                    }
                }, 3000);
            }
        });
    }
}
