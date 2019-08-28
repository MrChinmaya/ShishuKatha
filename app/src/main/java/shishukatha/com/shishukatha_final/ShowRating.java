package shishukatha.com.shishukatha_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class ShowRating extends AppCompatActivity {

    public String story1rate, story2rate, story3rate, story4rate, story5rate, story6rate, story7rate, story8rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        android.support.v7.app.ActionBar AB=getSupportActionBar();//To remove title bar
//        AB.hide();// remove title
        setContentView(R.layout.activity_show_rating);

        story1rate=getIntent().getStringExtra("Story1rate");
        story2rate=getIntent().getStringExtra("Story2rate");
        story3rate=getIntent().getStringExtra("Story3rate");
        story4rate=getIntent().getStringExtra("Story4rate");
        story5rate=getIntent().getStringExtra("Story5rate");
        story6rate=getIntent().getStringExtra("Story6rate");
        story7rate=getIntent().getStringExtra("Story7rate");
        story8rate=getIntent().getStringExtra("Story8rate");

        System.out.println("values are"+story1rate+story2rate+story3rate+story4rate+story5rate+story6rate+story7rate+story8rate);
    }
}
