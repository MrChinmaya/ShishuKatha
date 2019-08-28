package shishukatha.com.shishukatha_final;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bigscreen.iconictabbar.view.IconicTab;
import com.bigscreen.iconictabbar.view.IconicTabBar;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import shishukatha.com.shishukatha_final.StoryActivity.Story1;
import shishukatha.com.shishukatha_final.StoryActivity.Story2;
import shishukatha.com.shishukatha_final.StoryActivity.Story3;
import shishukatha.com.shishukatha_final.StoryActivity.Story4;
import shishukatha.com.shishukatha_final.StoryActivity.Story5;
import shishukatha.com.shishukatha_final.StoryActivity.Story6;
import shishukatha.com.shishukatha_final.StoryActivity.Story7;
import shishukatha.com.shishukatha_final.StoryActivity.Story8;
import shishukatha.com.shishukatha_final.util.IabHelper;
import shishukatha.com.shishukatha_final.util.IabResult;
import shishukatha.com.shishukatha_final.util.Inventory;
import shishukatha.com.shishukatha_final.util.Purchase;

public class StoryMenu extends AppCompatActivity {
    private static final String TAG1 = StoryMenu.class.getSimpleName();
    View view;
    private IconicTabBar iconicTabBar;
    private static final String TAG ="shishukatha.com.shishukatha_final";
    IabHelper mHelper;
    static final String ITEM_SKU = "com.example.buttonclicked";
        String Fetchedname,Fetchedmail,userid,profile_pic,storyid,readstatus,sid,rstatus;
        TextView title1,title2,title3,title4,title5,title5_1,title6,title7,title8;
        TextView subtitle1,subtitle2,subtitle3,subtitle4,subtitle5,subtitle6,subtitle7,subtitle8;
        TextView authorStory1,authorStory2,authorStory3,authorStory4,authorStory5,authorStory6,authorStory7,authorStory8;
        CircleImageView Storyimage1,Storyimage2,Storyimage3,Storyimage4,Storyimage5,Storyimage6,Storyimage7,Storyimage8;
        RatingBar Story1ratingbar,Story2ratingbar,Story3ratingbar,Story4ratingbar,Story5ratingbar,Story6ratingbar,Story7ratingbar,Story8ratingbar;
        public Float story1rate, story2rate, story3rate, story4rate, story5rate, story6rate, story7rate, story8rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SK", "Menu Page");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_story_menu);

        String base64EncodedPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmdHHR0fRUfIYdiH9Jm26cM8z4neGVjf7rfUxaH+jqLDCo/D0BmYouVCJ4RNK2DtcQdl+Qki+b9bSIP+RcdMCp2zISm9biYU7gwUwYbpvyvIkMqXngJckKq8Tgo+LeVuIdnt9IRnZrRD9To4ICVAxWgx7WWPS6duDaGbkatP4WuKXLTVeXx+cDvZwfnFuZuD3ts/spIWw0CmMg0+J1LWqeIef8dh4yigJg86PiThUTq73NfKwUXH6Zy4i1UNd35o7QxmzVDUfQhfTCwVUjruFCI3cXwTueGrrhOmY7yShcLKhvQDGSj4AB7amTjDHP/9VucTowZM9w0WP6EUqQP4VrQIDAQAB";
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.startSetup(new
                                   IabHelper.OnIabSetupFinishedListener() {
                                       public void onIabSetupFinished(IabResult result)
                                       {
                                           if (!result.isSuccess()) {
                                               Log.d(TAG, "In-app Billing setup failed: " +result);
                                           } else {
                                               Log.d(TAG, "In-app Billing is set up OK");
                                           }
                                       }
                                   });

        Fetchedname= getIntent().getStringExtra("Fetchedname");
        Fetchedmail= getIntent().getStringExtra("Fetchedmail");
        userid= getIntent().getStringExtra("userid");


        title1=findViewById(R.id.Storytitle1);
        subtitle1=findViewById(R.id.Storysubtitle1);
        authorStory1=findViewById(R.id.Authorstory1);
        Storyimage1=findViewById(R.id.profile_image1);
        Story1ratingbar=findViewById(R.id.story1rating);
        Story1ratingbar.setScrollBarSize(10);


        title2=findViewById(R.id.Storytitle2);
        subtitle2=findViewById(R.id.Storysubtitle2);
        authorStory2=findViewById(R.id.Authorstory2);
        Storyimage2=findViewById(R.id.profile_image2);
        Story2ratingbar=findViewById(R.id.story2rating);


        title3=findViewById(R.id.Storytitle3);
        subtitle3=findViewById(R.id.Storysubtitle3);
        authorStory3=findViewById(R.id.Authorstory3);
        Storyimage3=findViewById(R.id.profile_image3);
        Story3ratingbar=findViewById(R.id.story3rating);

        title4=findViewById(R.id.Storytitle4);
        subtitle4=findViewById(R.id.Storysubtitle4);
        authorStory4=findViewById(R.id.Authorstory4);
        Storyimage4=findViewById(R.id.profile_image4);
        Story4ratingbar=findViewById(R.id.story4rating);

        title5=findViewById(R.id.Storytitle5);
        title5_1=findViewById(R.id.Storytitle5_1);
        subtitle5=findViewById(R.id.Storysubtitle5);
        authorStory5=findViewById(R.id.Authorstory5);
        Storyimage5=findViewById(R.id.profile_image5);
        Story5ratingbar=findViewById(R.id.story5rating);

        title6=findViewById(R.id.Storytitle6);
        subtitle6=findViewById(R.id.Storysubtitle6);
        authorStory6=findViewById(R.id.Authorstory6);
        Storyimage6=findViewById(R.id.profile_image6);
        Story6ratingbar=findViewById(R.id.story6rating);

        title7=findViewById(R.id.Storytitle7);
        subtitle7=findViewById(R.id.Storysubtitle7);
        authorStory7=findViewById(R.id.Authorstory7);
        Storyimage7=findViewById(R.id.profile_image7);
        Story7ratingbar=findViewById(R.id.story7rating);

        title8=findViewById(R.id.Storytitle8);
        subtitle8=findViewById(R.id.Storysubtitle8);
        authorStory8=findViewById(R.id.Authorstory8);
        Storyimage8=findViewById(R.id.profile_image8);
        Story8ratingbar=findViewById(R.id.story8rating);



        title1.setText("SHABASH CUCKOO ");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title1.setTypeface(typeface1);
        subtitle1.setText("Early one morning, when the sun...");
        authorStory1.setText("by: DASH BENHUR");
        Storyimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Translated & Illustrated by:", Toast.LENGTH_LONG, true).show();
                storyid="Story1";
                Intent Start1=new Intent(StoryMenu.this, Story1.class);
//                statsupdate(v);
                Start1.putExtra("Fetchedname", Fetchedname);
                Start1.putExtra("Fetchedmail", Fetchedmail);
                Start1.putExtra("userid", userid);
                startActivity(Start1);
            }
        });

        title2.setText("THE LAZY GRASSHOPPER ");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title2.setTypeface(typeface2);
        subtitle2.setText("It was hot, hot summer, night...");
        authorStory2.setText("by: DASH BENHUR");
        Storyimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Translated & Illustrated by:", Toast.LENGTH_LONG, true).show();
                storyid="Story2";
                Intent Start2=new Intent(StoryMenu.this, Story2.class);
//                statsupdate(v);
                Start2.putExtra("Fetchedname", Fetchedname);
                Start2.putExtra("Fetchedmail", Fetchedmail);
                Start2.putExtra("userid", userid);
                startActivity(Start2);
            }
        });

        title3.setText("YA CHHOON! ");
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title3.setTypeface(typeface3);
        subtitle3.setText("A lion was fast asleep inside jungle...");
        authorStory3.setText("by: DASH BENHUR");
        Storyimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Translated & Illustrated by:", Toast.LENGTH_LONG, true).show();
                storyid="Story3";
                Intent Start3=new Intent(StoryMenu.this, Story3.class);
//                statsupdate(v);
                Start3.putExtra("Fetchedname", Fetchedname);
                Start3.putExtra("Fetchedmail", Fetchedmail);
                Start3.putExtra("userid", userid);
                startActivity(Start3);
            }
        });

        title4.setText("BRAVE THIMPI ");
        Typeface typeface4 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title4.setTypeface(typeface4);
        subtitle4.setText("Thimpi was terror-striken. But...");
        authorStory4.setText("by: DASH BENHUR");
        Storyimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Translated & Illustrated by:", Toast.LENGTH_LONG, true).show();
                storyid="Story4";
                Intent Start4=new Intent(StoryMenu.this, Story4.class);
//                statsupdate(v);
                Start4.putExtra("Fetchedname", Fetchedname);
                Start4.putExtra("Fetchedmail", Fetchedmail);
                Start4.putExtra("userid", userid);
                startActivity(Start4);
            }
        });

        title5.setText("ROCKO THE CROW AND ");
        Typeface typeface5 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title5.setTypeface(typeface5);
        title5_1.setText("GRANDFATHER ");
        Typeface typeface5_1 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title5_1.setTypeface(typeface5_1);
        subtitle5.setText("There was a huge Banyan tree...");
        authorStory5.setText("by: DASH BENHUR");
        Storyimage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Translated & Illustrated by:", Toast.LENGTH_LONG, true).show();
                storyid="Story5";
                Intent Start5=new Intent(StoryMenu.this, Story5.class);
//                statsupdate(v);
                Start5.putExtra("Fetchedname", Fetchedname);
                Start5.putExtra("Fetchedmail", Fetchedmail);
                Start5.putExtra("userid", userid);
                startActivity(Start5);
            }
        });

        title6.setText("THE TEENY WEENY BALLOON ");
        Typeface typeface6 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title6.setTypeface(typeface6);
        subtitle6.setText("There was tiny, teeny-weeny...");
        authorStory6.setText("by: DASH BENHUR");
        Storyimage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Translated & Illustrated by:", Toast.LENGTH_LONG, true).show();
                storyid="Story6";
                Intent Start6=new Intent(StoryMenu.this, Story6.class);
//                statsupdate(v);
                Start6.putExtra("Fetchedname", Fetchedname);
                Start6.putExtra("Fetchedmail", Fetchedmail);
                Start6.putExtra("userid", userid);
                startActivity(Start6);
            }
        });

        title7.setText("WHITEY THE YOUNG CRANE ");
        Typeface typeface7 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title7.setTypeface(typeface7);
        subtitle7.setText("In a Banyan tree there lived a...");
        authorStory7.setText("by: DASH BENHUR");
        Storyimage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Translated & Illustrated by:", Toast.LENGTH_LONG, true).show();
                storyid="Story7";
                Intent Start7=new Intent(StoryMenu.this, Story7.class);
//                statsupdate(v);
                Start7.putExtra("Fetchedname", Fetchedname);
                Start7.putExtra("Fetchedmail", Fetchedmail);
                Start7.putExtra("userid", userid);
                startActivity(Start7);
            }
        });

        title8.setText("TWO PIGEONS ");
        Typeface typeface8 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScriptRegular.otf");
        title8.setTypeface(typeface8);
        subtitle8.setText("It was a month of baisakh...");
        authorStory8.setText("by: DASH BENHUR");
        Storyimage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Translated & Illustrated by:", Toast.LENGTH_LONG, true).show();
                storyid="Story8";
                Intent Start8=new Intent(StoryMenu.this, Story8.class);
//                statsupdate(v);
                Start8.putExtra("Fetchedname", Fetchedname);
                Start8.putExtra("Fetchedmail", Fetchedmail);
                Start8.putExtra("userid", userid);
                startActivity(Start8);
            }
        });

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initViews();
    }


    private void initViews() {
        iconicTabBar = findViewById(R.id.bottom_bar);

        iconicTabBar.setOnTabSelectedListener(new IconicTabBar.OnTabSelectedListener() {
            @Override
            public void onSelected(IconicTab tab, int position) {
                Log.d(TAG1, "selected tab on= " + position);
               // String demoText = "";
                int tabId = tab.getId();
                switch (tabId) {
                    case R.id.menu:
                        Intent menu=new Intent(StoryMenu.this,StoryMenu.class);
                        startActivity(menu);
                        break;
                    case R.id.subscribe:
                        subscribe(view);
                        Toasty.info(getApplicationContext(), "Subscription is under Maintainace!", Toast.LENGTH_SHORT, true).show();
//                        Intent subscribe=new Intent(StoryMenu.this,StoryMenu.class);
//                        startActivity(subscribe);
                        break;
                    case R.id.rateus:
                        Intent rateus=new Intent(StoryMenu.this,Feedback.class);
                        rateus.putExtra("Fetchedname", Fetchedname);
                        rateus.putExtra("Fetchedmail", Fetchedmail);
                        rateus.putExtra("userid", userid);
                        rateus.putExtra("data","apprating");
                        rateus.putExtra("ratings","apprating");
                        startActivity(rateus);
                        break;
                    case R.id.myprofile:
                        //Toasty.info(getApplicationContext(), "Profile page is under Maintainace!", Toast.LENGTH_SHORT, true).show();
                        Intent start=new Intent(StoryMenu.this,Profile.class);
                        start.putExtra("Fetchedname", Fetchedname);
                        start.putExtra("Fetchedmail", Fetchedmail);
                        start.putExtra("userid", userid);
                        startActivity(start);
                        break;
                    default:
                        break;
                }
//                textDemo.setText(demoText);
            }

            @Override
            public void onUnselected(IconicTab tab, int position) {
                Log.d(TAG1, "unselected tab on= " + position);
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
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(StoryMenu.this, MainActivity.class));
        finish();
    }

    @Override
    public void onPause(){
        super.onPause();
        sid=null;
        storyid=null;
    }

    void subscribe(View view){

        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                mPurchaseFinishedListener, "mypurchasetoken");
    }

    public void buyClick(View view) {
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,mPurchaseFinishedListener, "mypurchasetoken");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        if (!mHelper.handleActivityResult(requestCode,resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener= new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                return;
            }
            else if (purchase.getSku().equals(ITEM_SKU)) {
                consumeItem();
                //buy.setEnabled(false);
            }
        }
    };

    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,Inventory inventory) {

            if (result.isFailure()) {
                // Handle failure
            } else {
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
                        mConsumeFinishedListener);
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase,IabResult result) {

            if (result.isSuccess()) {
                Toasty.info(getApplicationContext(), "Subscription is Completed!", Toast.LENGTH_SHORT, true).show();
            } else {
                // handle error
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }
}
