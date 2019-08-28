package shishukatha.com.shishukatha_final;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import es.dmoral.toasty.Toasty;
import shishukatha.com.shishukatha_final.AppConstants.AppConstants;
import shishukatha.com.shishukatha_final.AppConstants.AppSharedpreferences;

import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG ="SK";
    LoginButton loginButton;
    CallbackManager callbackManager;
    Button signout;
    ImageButton adminlogin;
    public String adminemail, password, gname, email, userid, uristring,imgurl;
    TextView Username;
    ImageView Profpic;
    View view;
    Boolean doubleBackToExitPressedOnce = false;
    Integer loginpressed=1 ;
    private String facebook_id,full_name;
    private GoogleApiClient googleApiClient;
    private static final int  REQ_CODE = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setVisibility(View.GONE);
        adminlogin = findViewById(R.id.admin);
        signout = findViewById(R.id.signout);
        Username = findViewById(R.id.username);
        Profpic = findViewById(R.id.profpic);

        AppUpdater appUpdater = new AppUpdater(this)
                .setUpdateFrom(UpdateFrom.GOOGLE_PLAY)
                .setTitleOnUpdateAvailable("Update available")
                .setContentOnUpdateAvailable("Update to the latest version available!")
                .setButtonUpdate("Update")
                .setCancelable(false)
                .setButtonDismiss(null)
                .setButtonDoNotShowAgain(null);
        Log.i(TAG, "onCreate: Checking for updates");
        appUpdater.start();

         Context context=getApplicationContext();
            PackageInfo packageInfo;
            String key = null;
            try {
                String packageName = context.getApplicationContext().getPackageName();
                packageInfo = context.getPackageManager().getPackageInfo(packageName,
                        PackageManager.GET_SIGNATURES);

                for (Signature signature : packageInfo.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    key = new String(Base64.encode(md.digest(), 0));
                    Log.i(TAG, "key"+key);
                }
            } catch (PackageManager.NameNotFoundException e1) {
                Log.e("Name not found", e1.toString());
            }
            catch (NoSuchAlgorithmException e) {
                Log.e("No such an algorithm", e.toString());
            } catch (Exception e) {
                Log.e("Exception", e.toString());
            }

        signout.setOnClickListener(this);
        Profpic.setVisibility(View.GONE);
        Username.setVisibility(View.GONE);
        signout.setVisibility(View.GONE);


        getstarted();               //Login dialog called

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

        //Admin clicked
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.login_dialog, null);
                final TextView admin = mView.findViewById(R.id.textView5);
                final EditText mEmail = mView.findViewById(R.id.email);
                final EditText mPassword = mView.findViewById(R.id.password);
                Button mlogin = mView.findViewById(R.id.dialoglogin);

                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Azkiademo.otf");
                admin.setTypeface(typeface);

                mlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()) {
                            adminemail = mEmail.getText().toString();
                            password = mPassword.getText().toString();
                            validate();
                        } else {
                            Toasty.warning(getApplicationContext(), "Please fill any empty fields!", Toast.LENGTH_SHORT, true).show();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    //Facebook Login
    public void getstarted(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        View view1 = getLayoutInflater().inflate(R.layout.login, null);
        ImageButton glogin = view1.findViewById(R.id.glogin);
        loginButton = findViewById(R.id.login_button);

        glogin.setOnClickListener(MainActivity.this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i(TAG, "Inside Facebook Login");

                Profile profile = Profile.getCurrentProfile();

                if (profile != null) {
                    //newCode
                    facebook_id = profile.getId();
                    full_name = profile.getName();
                }

                final Intent move = new Intent(MainActivity.this, SplashMessage.class);
                move.putExtra("Fetchedname", full_name);
                move.putExtra("userid", facebook_id);
                move.putExtra("Fetchedmail", "null");
                move.putExtra("profile_pic","null");
                startActivity(move);
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "Cancel clicked inside Facebook");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i(TAG, "Facebook:Unable to Login");
            }
        });

        builder.setView(view1);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
       // wmlp.x =500;   //x position
        wmlp.y = 400;   //y position

        dialog.show();

    }

    // For admin validation
    public void validate() {
        if (adminemail.equals("chinubhoi569") && password.equals("12345")) {
            Toasty.success(getApplicationContext(), "Success!", Toast.LENGTH_SHORT, true).show();
            final Intent validationintent = new Intent(MainActivity.this, Admin.class);
            startActivity(validationintent);
            return;
        } else {
            Toasty.error(getApplicationContext(), "Admin Login Failed!", Toast.LENGTH_SHORT, true).show();
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.glogin:
                Signin();
                break;
            case R.id.signout:
                Signout();
                break;
        }
    }

    private void Signin() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQ_CODE) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleResult(result);
            }
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            gname = account.getDisplayName();
            email = account.getEmail();
          //  imgurl = account.getPhotoUrl().toString();
            Username.setText(gname);
            userid = email.toLowerCase();
        //    Glide.with(this).load(imgurl).into(Profpic);

            updateUI(true);
        } else {
            updateUI(false);
        }
    }

    private void Signout() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void updateUI(Boolean isLogin) {
        if (isLogin) {
            Profpic.setVisibility(View.GONE);
            Username.setVisibility(View.GONE);
            signout.setVisibility(View.VISIBLE);
            final Intent move = new Intent(MainActivity.this, SplashMessage.class);
            move.putExtra("Fetchedname", gname);
            move.putExtra("userid", userid);
            move.putExtra("Fetchedmail", email);
            move.putExtra("profile_pic",imgurl);
            startActivity(move);
        } else {
            Profpic.setVisibility(View.GONE);
            Username.setVisibility(View.GONE);
            signout.setVisibility(View.GONE);
            final Intent over = new Intent(this, MainActivity.class);
            startActivity(over);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toasty.error(getApplicationContext(), "Check Internet Connectivity", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toasty.warning(getApplicationContext(), "Double tap to exit", Toast.LENGTH_SHORT, true).show();
        Stop();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }

    void Stop() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View upgradeview = getLayoutInflater().inflate(R.layout.share_exitdialog, null);

        ImageButton share = upgradeview.findViewById(R.id.share);
        ImageButton exit =  upgradeview.findViewById(R.id.exit);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share(view);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForceExit();
            }
        });
        builder.setView(upgradeview);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void share(View view) {             // Code to show the popup to quit
        Intent shareintent = new Intent(Intent.ACTION_SEND);
        shareintent.setType("text/plain");
        uristring = "https://goo.gl/QMrVcK";
        shareintent.putExtra("A great platform for your Kids and Parents", uristring);
        boolean facebookAppFound = false;
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(shareintent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                shareintent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }
// As fallback, launch sharer.php in a browser
        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + uristring;
            shareintent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }
        startActivity(shareintent);
    }



    public void ForceExit(){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}

