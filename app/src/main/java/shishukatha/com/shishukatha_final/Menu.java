package shishukatha.com.shishukatha_final;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.andexert.library.RippleView;
import com.littlechoc.cornerlabel.CornerLabel;

import de.hdodenhof.circleimageview.CircleImageView;
import shishukatha.com.shishukatha_final.StoryActivity.Story1;
import shishukatha.com.shishukatha_final.StoryActivity.Story2;
import shishukatha.com.shishukatha_final.StoryActivity.Story3;
import shishukatha.com.shishukatha_final.StoryActivity.Story4;
import shishukatha.com.shishukatha_final.StoryActivity.Story5;
import shishukatha.com.shishukatha_final.StoryActivity.Story6;
import shishukatha.com.shishukatha_final.StoryActivity.Story7;
import shishukatha.com.shishukatha_final.StoryActivity.Story8;

public class Menu extends AppCompatActivity {

    ImageButton story1,story2,story3,story4,story5,story6,story7,story8;
    View view;
   public String userid,storyid,readstatus,sid,rstatus;
    ImageView shelf1,shelf2,shelf3,shelf4;
   CornerLabel Storylabel1,Storylabel2,Storylabel3,Storylabel4,Storylabel5,Storylabel6,Storylabel7,Storylabel8;
   CircleImageView circleImageView1;
    RippleView rippleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        android.support.v7.app.ActionBar AB=getSupportActionBar();//To remove title bar
//        AB.hide();// remove title
       setContentView(R.layout.activity_menu);

        Storylabel1=(CornerLabel)findViewById(R.id.cornerLabel1);
        Storylabel2=(CornerLabel)findViewById(R.id.cornerLabel2);
        Storylabel3=(CornerLabel)findViewById(R.id.cornerLabel3);
        Storylabel4=(CornerLabel)findViewById(R.id.cornerLabel4);
        Storylabel5=(CornerLabel)findViewById(R.id.cornerLabel5);
        Storylabel6=(CornerLabel)findViewById(R.id.cornerLabel6);
        Storylabel7=(CornerLabel)findViewById(R.id.cornerLabel7);
        Storylabel8=(CornerLabel)findViewById(R.id.cornerLabel8);

        circleImageView1=findViewById(R.id.profile_image);
        //circleImageView1.setVisibility(View.INVISIBLE);

        shelf1=(ImageView)findViewById(R.id.imageView1);
        shelf2=(ImageView)findViewById(R.id.imageView2);
        shelf3=(ImageView)findViewById(R.id.imageView3);
        shelf4=(ImageView)findViewById(R.id.imageView4);

        Storylabel2.bringToFront();
        Storylabel2.invalidate();

        rippleView=findViewById(R.id.rippleview);
        
        Storylabel3.bringToFront();
        Storylabel4.bringToFront();
        Storylabel5.bringToFront();
        Storylabel8.bringToFront();

        Storylabel1.setVisibility(View.INVISIBLE);
        Storylabel6.setVisibility(View.INVISIBLE);
        Storylabel7.setVisibility(View.INVISIBLE);

        story1=(ImageButton) findViewById(R.id.imageButton1);
        story2=(ImageButton) findViewById(R.id.imageButton2);
        story3=(ImageButton) findViewById(R.id.imageButton3);
        story4=(ImageButton) findViewById(R.id.imageButton4);
        story5=(ImageButton) findViewById(R.id.imageButton5);
        story6=(ImageButton) findViewById(R.id.imageButton6);
        story7=(ImageButton)findViewById(R.id.imageButton7);
        story8=(ImageButton) findViewById(R.id.imageButton8);
        userid= getIntent().getStringExtra("userid");

        story1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyid="Story1";
                readstatus="yes";
                Intent intent = new Intent(Menu.this, Story1.class);
                System.out.println(storyid);
                System.out.println(readstatus);
                statsupdate(view);
                checklabel(view);
                intent.putExtra("userid",userid);
                System.out.println("Menu userid"+userid);
                startActivity(intent);
            }
        });

        story2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyid="Story2";
                readstatus="yes";
                Intent intent = new Intent(Menu.this, Story2.class);
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });

        story3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyid="Story3";
                readstatus="yes";
                Intent intent = new Intent(Menu.this, Story3.class);
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });

        story4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyid="Story4";
                readstatus="yes";
                Intent intent = new Intent(Menu.this, Story4.class);
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });

        story5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyid="Story5";
                readstatus="yes";
                Intent intent = new Intent(Menu.this, Story5.class);
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });

        story6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyid="Story6";
                readstatus="yes";
                Intent intent = new Intent(Menu.this, Story6.class);
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });

        story7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyid="Story7";
                readstatus="yes";
                Intent intent = new Intent(Menu.this, Story7.class);
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });

        story8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyid="Story8";
                readstatus="yes";
                Intent intent = new Intent(Menu.this, Story8.class);
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
    }

    public void statsupdate(View view  ){
        userid=userid.toString();
        sid=storyid.toString();
        rstatus=readstatus.toString();

        System.out.println(userid);
        System.out.println(sid);
        System.out.println(readstatus);

        BackgroundStoryStatus backgroundStoryStatus=new BackgroundStoryStatus(this);
        backgroundStoryStatus.execute(userid,sid,rstatus);
    }

    public  void checklabel(View view){
        BackgroundStoryLabelCheck backgroundStoryLabelCheck=new BackgroundStoryLabelCheck(this);
        backgroundStoryLabelCheck.execute(userid,sid);
    }

    @Override
    public void onPause(){
        super.onPause();
        sid=null;
        rstatus=null;
        storyid=null;
        readstatus=null;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Menu.this, MainActivity.class));
        finish();
    }

}
