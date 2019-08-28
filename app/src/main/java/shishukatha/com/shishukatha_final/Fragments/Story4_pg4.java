package shishukatha.com.shishukatha_final.Fragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;
import com.ohoussein.playpause.PlayPauseView;

import es.dmoral.toasty.Toasty;
import shishukatha.com.shishukatha_final.Backgroundfeedback;
import shishukatha.com.shishukatha_final.R;
import shishukatha.com.shishukatha_final.StoryMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class Story4_pg4 extends Fragment {

    public String rate,uid,sid,rates,userid,storyid,apprate;
    float ratings=0.0f;
    MediaPlayer narate;
    ImageButton audioplay;
    Boolean isPlaying=false;
    ImageButton startmenu;

    public Story4_pg4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_story4_pg4, container, false);
        narate= MediaPlayer.create(getActivity(),R.raw.s4a4);
        ImageButton feed=(ImageButton)v2.findViewById(R.id.imageButton15);
        final RatingBar gettingrates=(RatingBar)v2.findViewById(R.id.ratingBar2);
        ImageButton prev=(ImageButton)v2.findViewById(R.id.imageButton14);
        startmenu=v2.findViewById(R.id.startmenu44);


        userid="storyrating";
        storyid="storyrating";

        startmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startmenu=new Intent(getActivity(), StoryMenu.class);
                startActivity(startmenu);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Story4_pg3 page3=new Story4_pg3();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page3,page3.getTag())
                        .commit();
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent feedintent=new Intent(getActivity(),Feedback.class);
//                feedintent.putExtra("data","Story4");
//                feedintent.putExtra("Ratings",rate);
//                startActivity(feedintent);
                feedback(view);
                Intent i=new Intent(getActivity(),StoryMenu.class);
                startActivity(i);
            }
        });

        gettingrates.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toasty.info(getActivity(), "Rated" + gettingrates.getRating(), Toast.LENGTH_SHORT, true).show();
                ratings= gettingrates.getRating();
                rate=String.valueOf(ratings);
            }
        });

        if (ratings == 0.0f){
            rate="";
        }

        final PlayPauseView view = (PlayPauseView) v2.findViewById(R.id.play_pause_view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPlaying) {
                    stop();
                    view.toggle();
                }
                else{
                    play();
                    view.toggle();
                }
                isPlaying = !isPlaying;
            }
        });

        return v2;
    }

    void play(){
        narate.start();
    }
    void stop(){
        narate.pause();
    }

    public void onDestroy() {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (narate != null) {
            narate.release();
            narate = null;
        }
    }

    public void feedback(View view  ){
        uid=userid.toString();
        System.out.println("Feedback userid"+uid);
        sid=storyid.toString();
        rates=rate.toString();
        apprate="storyrating";

        Backgroundfeedback backgroundfeedback=new Backgroundfeedback(getActivity());
        backgroundfeedback.execute(uid,sid,rates,apprate);
    }

    @Override
    public void onPause(){
        super.onPause();
        storyid=null;
        rate=null;
        // apprate="";
        //uid=null;
    }
}
