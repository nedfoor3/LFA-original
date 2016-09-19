package com.proyectofootball.titanes.lfa.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.proyectofootball.titanes.lfa.AcercaDe;
import com.proyectofootball.titanes.lfa.MainActivity;
import com.proyectofootball.titanes.lfa.ParaPruebas;
import com.proyectofootball.titanes.lfa.R;
import com.proyectofootball.titanes.lfa.Standings;
import com.proyectofootball.titanes.lfa.TeamsActivity;
import com.proyectofootball.titanes.lfa.adapter.SlidingMenuAdapter;
import com.proyectofootball.titanes.lfa.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Rodriguez on 9/15/2016.
 */
public class MenuGenerico extends AppCompatActivity {

    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private Toolbar toolbarParaLogo;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    public Toolbar crearMenu(final Activity activity) {
        listViewSliding = (ListView) activity.findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();

        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(R.drawable.ic_stat_silueta_logo_para_notificacion, activity.getResources().getString(R.string.home)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_player, activity.getResources().getString(R.string.teams)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_calendar_time, activity.getResources().getString(R.string.schedule)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_football_play, activity.getResources().getString(R.string.standings)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_first_down_mark, activity.getResources().getString(R.string.estadisticas) + " " + activity.getResources().getString(R.string.construyendo)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_referee, activity.getResources().getString(R.string.reglamento)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_info, activity.getResources().getString(R.string.acerca_de)));
        //listSliding.add(new ItemSlideMenu(R.drawable.ic_engranes, getResources().getString(R.string.probando)));
        adapter = new SlidingMenuAdapter(activity, listSliding);
        listViewSliding.setAdapter(adapter);

        toolbarParaLogo = (Toolbar) activity.findViewById(R.id.main_toolbar);
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                changeActivity(position, activity);
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbarParaLogo, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                activity.invalidateOptionsMenu();
                syncState();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /* setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        return toolbarParaLogo;

    }


    private void changeActivity(int pos, Activity activity) {
        switch (pos) {
            case 0:
                Intent noticiasPrincipalesIntent = new Intent(activity, MainActivity.class);
                activity.startActivity(noticiasPrincipalesIntent);
                break;
            case 1:
                Intent detalleEquiposIntent = new Intent(activity, TeamsActivity.class);
                activity.startActivity(detalleEquiposIntent);
                break;
            case 2:
                break;
            case 3:
                Intent standingsIntent = new Intent(activity, Standings.class);
                activity.startActivity(standingsIntent);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                Intent acercaDeIntent = new Intent(activity, AcercaDe.class);
                activity.startActivity(acercaDeIntent);
                break;
            case 7:
                Intent preferenciasIntent = new Intent(activity, ParaPruebas.class);
                activity.startActivity(preferenciasIntent);
                break;
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }
}
