package shishukatha.com.shishukatha_final.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import shishukatha.com.shishukatha_final.R;
import shishukatha.com.shishukatha_final.StoryMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class Story8_pg2 extends Fragment {

    public String userid;
    ImageButton startmenu;

    public Story8_pg2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_story8_pg2, container, false);

        ImageButton prev=(ImageButton)v2.findViewById(R.id.imageButton14);
        startmenu=v2.findViewById(R.id.startmenu82);

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
                Story8_pg1 page1=new Story8_pg1();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page1,page1.getTag())
                        .commit();
            }
        });

        ImageButton next=(ImageButton)v2.findViewById(R.id.imageButton9);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Story8_pg3 page3=new Story8_pg3();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page3,page3.getTag())
                        .commit();
            }
        });
        return v2;
    }
}
