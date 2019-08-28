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
public class Story3_pg5 extends Fragment {

    public String userid;
    ImageButton startmenu;

    public Story3_pg5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_story3_pg5, container, false);

        ImageButton prev=(ImageButton)v2.findViewById(R.id.imageButton14);
        startmenu=v2.findViewById(R.id.startmenu35);

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
                Story3_pg4 page4=new Story3_pg4();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page4,page4.getTag())
                        .commit();
            }
        });

        ImageButton next=(ImageButton)v2.findViewById(R.id.imageButton9);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Story3_pg6 page6=new Story3_pg6();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout1,page6,page6.getTag())
                        .commit();
            }
        });
        return v2;
    }
}
