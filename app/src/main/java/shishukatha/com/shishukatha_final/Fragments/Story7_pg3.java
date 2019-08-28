package shishukatha.com.shishukatha_final.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import shishukatha.com.shishukatha_final.Backgroundfeedback;
import shishukatha.com.shishukatha_final.R;
import shishukatha.com.shishukatha_final.StoryMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class Story7_pg3 extends Fragment {

    public String rate,uid,sid,rates,userid,storyid,apprate;
    float ratings=0.0f;
    ImageButton startmenu;

    public Story7_pg3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_story7_pg3, container, false);

        ImageButton feed=(ImageButton)v2.findViewById(R.id.imageButton15);
        final RatingBar gettingrates=(RatingBar)v2.findViewById(R.id.ratingBar2);
        ImageButton prev=(ImageButton)v2.findViewById(R.id.imageButton14);
        startmenu=v2.findViewById(R.id.startmenu73);

        startmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startmenu=new Intent(getActivity(), StoryMenu.class);
                startActivity(startmenu);
            }
        });

        userid="storyrating";
        storyid="storyrating";

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Story7_pg2 page2=new Story7_pg2();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page2,page2.getTag())
                        .commit();
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent feedintent=new Intent(getActivity(),Feedback.class);
//                feedintent.putExtra("data","Story7");
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
        return v2;
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
