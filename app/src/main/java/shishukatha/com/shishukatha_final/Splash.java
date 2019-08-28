package shishukatha.com.shishukatha_final;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import wail.splacher.com.splasher.lib.SplasherActivity;
import wail.splacher.com.splasher.models.SplasherConfig;
import wail.splacher.com.splasher.utils.Const;

public class Splash extends SplasherActivity {
        @Override
        public void initSplasher(SplasherConfig config) {
            config.setReveal_start(Const.START_CENTER)
                    //---------------
                    .setAnimationDuration(5000)
                    //---------------
                    .setLogo(R.drawable.logo)
                    .setLogo_animation(Techniques.BounceIn)
                    .setAnimationLogoDuration(2000)
                    .setLogoWidth(500)
                    //---------------
                    .setTitle("Learn")
                    .setTitleColor(Color.parseColor("#ffffff"))
                    .setTitleAnimation(Techniques.Bounce)
                    .setTitleSize(24)
                    //---------------
                    .setSubtitle("With Fun")
                    .setSubtitleColor(Color.parseColor("#ffffff"))
                    .setSubtitleAnimation(Techniques.FadeIn)
                    .setSubtitleSize(20)
                    //---------------
                    .setSubtitleTypeFace(Typeface.createFromAsset(getAssets(),"fonts/diana.otf"))
                    .setTitleTypeFace(Typeface.createFromAsset(getAssets(),"fonts/stc.otf"));


//        config.setCustomView(R.layout.custom_view)
//                .setReveal_start(Const.START_CENTER)
//                .setAnimationDuration(5000);
//        getCustomView().findViewById(R.id.textView);
        }

        @Override
        public void onSplasherFinished() {
          //  Toast.makeText(this, "Go to the next activity", Toast.LENGTH_SHORT).show();
            Intent startmain=new Intent(Splash.this,MainActivity.class);
            startActivity(startmain);
        }

}
