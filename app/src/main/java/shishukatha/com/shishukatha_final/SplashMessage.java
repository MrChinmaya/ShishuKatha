package shishukatha.com.shishukatha_final;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import shishukatha.com.shishukatha_final.AppConstants.AppSharedpreferences;
import shishukatha.com.shishukatha_final.util.IabHelper;
import shishukatha.com.shishukatha_final.util.IabResult;

import shishukatha.com.shishukatha_final.util.Inventory;
import shishukatha.com.shishukatha_final.util.Purchase;

 public class SplashMessage extends AppCompatActivity{


    public String name,mail,userid,profile_pic;
    View view;
    ImageButton continuebutton,subscribe;
    private static final String TAG ="SK";
    IabHelper mHelper;
    static final String ITEM_SKU = "com.example.buttonclicked";
    TextView displayname,displaymessage1;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_message);

        String base64EncodedPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmdHHR0fRUfIYdiH9Jm26cM8z4neGVjf7rfUxaH+jqLDCo/D0BmYouVCJ4RNK2DtcQdl+Qki+b9bSIP+RcdMCp2zISm9biYU7gwUwYbpvyvIkMqXngJckKq8Tgo+LeVuIdnt9IRnZrRD9To4ICVAxWgx7WWPS6duDaGbkatP4WuKXLTVeXx+cDvZwfnFuZuD3ts/spIWw0CmMg0+J1LWqeIef8dh4yigJg86PiThUTq73NfKwUXH6Zy4i1UNd35o7QxmzVDUfQhfTCwVUjruFCI3cXwTueGrrhOmY7yShcLKhvQDGSj4AB7amTjDHP/9VucTowZM9w0WP6EUqQP4VrQIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.startSetup(new
                                   IabHelper.OnIabSetupFinishedListener() {
                                       public void onIabSetupFinished(IabResult result)
                                       {
                                           if (!result.isSuccess()) {
                                               Log.i(TAG, "In-app Billing setup failed: " +result);
                                           } else {
                                               Log.i(TAG, "In-app Billing is set up OK");
                                           }
                                       }
                                   });

        name= getIntent().getStringExtra("Fetchedname");
        mail= getIntent().getStringExtra("Fetchedmail");
        userid= getIntent().getStringExtra("userid");

        displayname=findViewById(R.id.textView1);
        displaymessage1=findViewById(R.id.msg1);

        if (name.equals(null)){
            displayname.setVisibility(View.GONE);
        }else{
//            DoLogin doLogin = new DoLogin();
//            doLogin.execute("");

        }
        continuebutton=findViewById(R.id.continuebutton);
        subscribe=findViewById(R.id.subscribe);

        displayname.setText(" Hello "+"\n  "+ name);
       // displaymessage1.setBackgroundColor(getResources().getColor(R.color.WhiteSmoke));
        displaymessage1.setText("At Shishukatha, we believe stories make personalities and build lives.\n\n" +
                "We are presenting you a collection of very carefully chosen stories from award winning authors.\n\n" +
                "Hope you enjoy reading and listening them as much as we enjoyed in creating them for you.\n" +
                "Please feel free to provide us feedback to help us improve.");


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/GLOO-GUN.TTF");
        displayname.setTypeface(typeface);

        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/Happy-Marker.ttf");
        displaymessage1.setTypeface(typeface1);


        subscribe.setVisibility(view.INVISIBLE);




//        subscribe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                subscribe(view);
//            }
//        });

         continuebutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent home_intent = new Intent(SplashMessage.this, StoryMenu.class);
                 home_intent.putExtra("Fetchedname", name);
                 home_intent.putExtra("userid", userid);
                 home_intent.putExtra("Fetchedmail", mail);
                 startActivity(home_intent);
             }
         });
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
                Intent home_intent=new Intent(SplashMessage.this,StoryMenu.class);
                home_intent.putExtra("Fetchedname",name);// try to change it to  displayname because if the user changes the name then the changed name will be store in the database
                home_intent.putExtra("Fetchedmail",mail);
                home_intent.putExtra("userid",userid);
                home_intent.putExtra("profile_pic",profile_pic);
                startActivity(home_intent);
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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SplashMessage.this, MainActivity.class));
        finish();

    }


}
