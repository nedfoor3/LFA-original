package com.proyectofootball.titanes.lfa.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyectofootball.titanes.lfa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rosters extends Fragment {


    public Rosters() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rosters, container, false);
    }

}
