package shishukatha.com.shishukatha_final.StoryActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import shishukatha.com.shishukatha_final.Feedback;
import shishukatha.com.shishukatha_final.Fragments.Story1_pg1;
import shishukatha.com.shishukatha_final.R;
import shishukatha.com.shishukatha_final.StoryMenu;

public class Story1 extends AppCompatActivity {
    public String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        android.support.v7.app.ActionBar AB=getSupportActionBar();//To remove title bar
//        AB.hide();// remove title
        setContentView(R.layout.activity_story1);

        userid= getIntent().getStringExtra("userid");
        Intent intent= new Intent(Story1.this, Feedback.class);
        intent.putExtra("userid",userid);

        Story1_pg1 page1=new Story1_pg1();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layout1,page1,page1.getTag())
                .commit();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Story1.this, StoryMenu.class));
        finish();

    }
}
