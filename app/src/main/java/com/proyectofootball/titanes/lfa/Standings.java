package com.proyectofootball.titanes.lfa;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.proyectofootball.titanes.lfa.menu.MenuGenerico;


/**
 * A simple {@link Fragment} subclass.
 */
public class Standings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);

        MenuGenerico mMenu = new MenuGenerico();
        mMenu.crearMenu(this);
    }

}
