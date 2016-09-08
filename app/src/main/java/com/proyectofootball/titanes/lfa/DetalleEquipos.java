package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.graphics.Palette;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;

import com.proyectofootball.titanes.lfa.adapter.ScheduleRosterAdapter;
import com.proyectofootball.titanes.lfa.fragments.Rosters;
import com.proyectofootball.titanes.lfa.fragments.TeamSchedule;

public class DetalleEquipos extends AppCompatActivity {
    private String idEquiposParaTema;
    private TabLayout tabLayoutRosterSchedule;
    private ViewPager pagerRosterSchedule;
    ScheduleRosterAdapter  scheduleRosterAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayoutDetalleEquipos;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        //idEquiposParaTema = bundle.toString();
        //setTheme(R.style.MayasTheme);
        //setTitle(R.string.mayas);
        setContentView(R.layout.activity_detalle_equipos);
        tabLayoutRosterSchedule = (TabLayout)findViewById(R.id.tab_selector_roster_calendario);
        pagerRosterSchedule = (ViewPager)findViewById(R.id.pager_selector_roster_calendario);
        scheduleRosterAdapter = new ScheduleRosterAdapter(getSupportFragmentManager());
        scheduleRosterAdapter.addFragments(new TeamSchedule(), getResources().getString(R.string.schedule));
        scheduleRosterAdapter.addFragments(new Rosters(), getResources().getString(R.string.roster));
        pagerRosterSchedule.setAdapter(scheduleRosterAdapter);
        tabLayoutRosterSchedule.setupWithViewPager(pagerRosterSchedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        //collapsingToolbarLayoutDetalleEquipos = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
