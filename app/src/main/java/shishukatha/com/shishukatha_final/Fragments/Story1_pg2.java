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
import com.ohoussein.playpause.PlayPauseView;

import shishukatha.com.shishukatha_final.R;
import shishukatha.com.shishukatha_final.StoryMenu;


/**
 * A simple {@link Fragment} subclass.
 */
public class Story1_pg2 extends Fragment {
    public String userid;
    MediaPlayer narate;
    ImageButton audioplay;
    Boolean isPlaying=false;
    ImageButton startmenu;

    public Story1_pg2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_story1_pg2, container, false);
        narate= MediaPlayer.create(getActivity(),R.raw.s1a1);
        startmenu=v2.findViewById(R.id.startmenu12);

        startmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startmenu=new Intent(getActivity(), StoryMenu.class);
                startActivity(startmenu);
            }
        });


        ImageButton next=(ImageButton)v2.findViewById(R.id.imageButton9);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Story1_pg3 page3=new Story1_pg3();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page3,page3.getTag())
                        .commit();
            }
        });

        ImageButton prev=(ImageButton)v2.findViewById(R.id.imageButton14);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Story1_pg1 page1=new Story1_pg1();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page1,page1.getTag())
                        .commit();
            }
        });

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
}
