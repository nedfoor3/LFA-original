package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.proyectofootball.titanes.lfa.menu.MenuGenerico;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        MenuGenerico mMenu = new MenuGenerico();

        mMenu.crearMenu(this);
    }
}
