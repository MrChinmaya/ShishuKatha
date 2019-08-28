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
public class Story1_pg1 extends Fragment {
    MediaPlayer narate;
    ImageButton audioplay;
    Boolean isPlaying=false;
    public String userid;
    ImageButton startmenu;

    public Story1_pg1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_story1_pg1, container, false);
        ImageButton next=(ImageButton) v.findViewById(R.id.imageButton8);
        narate= MediaPlayer.create(getActivity(),R.raw.story1audio);
        startmenu=v.findViewById(R.id.startmenu11);

        startmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startmenu=new Intent(getActivity(), StoryMenu.class);
                startActivity(startmenu);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Story1_pg2 page2=new Story1_pg2();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page2,page2.getTag())
                        .commit();
            }
        });



        final PlayPauseView view = (PlayPauseView) v.findViewById(R.id.play_pause_view);
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
        return v;

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
