package shishukatha.com.shishukatha_final;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import shishukatha.com.shishukatha_final.StoryActivity.Story7;
import shishukatha.com.shishukatha_final.StoryActivity.Story8;

public class Menu2 extends AppCompatActivity {

    Button story7;
    Button story8;
    ImageButton prevmenu,nextmenu;
    String uristring;
    View view;
    public String userid,storyid,readstatus,sid,rstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        android.support.v7.app.ActionBar AB=getSupportActionBar();//To remove title bar
//        AB.hide();// remove title
        setContentView(R.layout.activity_menu2);

        userid= getIntent().getStringExtra("userid");

        story7=(Button) findViewById(R.id.button6);
        story8=(Button) findViewById(R.id.button11);
        prevmenu=(ImageButton)findViewById(R.id.imageButton12);
        nextmenu=(ImageButton)findViewById(R.id.imageButton);

        story7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu2.this, Story7.class);
                storyid="Story7";
                readstatus="yes";
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });

        story8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu2.this, Story8.class);
                storyid="Story8";
                readstatus="yes";
                statsupdate(view);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });

        prevmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu2.this,Menu.class);
                startActivity(intent);
            }
        });

        nextmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Menu2.this,Menu.class);
                startActivity(intent1);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(android.view.Menu menu){
//        getMenuInflater().inflate(R.menu.hamberger,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem){
//        switch (menuItem.getItemId()){
//
//            case R.id.item1:
//                share(view);
//                return true;
//            case R.id.item2:
//                Intent intent2=new Intent(this,MainActivity.class);
//                startActivity(intent2);
//                return true;
//            case R.id.item3:
//                Intent intent3=new Intent(this,MainActivity.class);
//                startActivity(intent3);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(menuItem);
//
//        }
//    }
    public void statsupdate(View view  ){
        userid=userid.toString();
        sid=storyid.toString();
        rstatus=readstatus.toString();
        BackgroundStoryStatus backgroundStoryStatus=new BackgroundStoryStatus(this);
        backgroundStoryStatus.execute(userid,sid,rstatus);
        //  finish();

    }

//    public void share(View view) {
//
//        Intent shareintent = new Intent(Intent.ACTION_SEND);
//        System.out.println("Statement 1");
//        shareintent.setType("text/plain");
//        System.out.println("Statement 2");
//        uristring = "http://www.hignosisit.com/";
//        System.out.println("Statement 3");
//        shareintent.putExtra(Intent.EXTRA_TEXT, uristring);
////        shareintent.setPackage("com.facebook.katana");
////        //startActivity(shareintent);
////        System.out.println("Statement 6");
//        boolean facebookAppFound = false;
//        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(shareintent, 0);
//        for (ResolveInfo info : matches) {
//            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
//                shareintent.setPackage(info.activityInfo.packageName);
//                facebookAppFound = true;
//                System.out.println("Statement 4");
//                break;
//            }
//        }
//
//// As fallback, launch sharer.php in a browser
//        if (!facebookAppFound) {
//            System.out.println("Statement 5");
//            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + uristring;
//            shareintent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
//        }
//        System.out.println("Statement 6");
//        startActivity(shareintent);
//    }

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
        startActivity(new Intent(Menu2.this, MainActivity.class));
        finish();

    }
}
