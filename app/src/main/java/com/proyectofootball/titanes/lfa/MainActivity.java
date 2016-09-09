package com.proyectofootball.titanes.lfa;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.proyectofootball.titanes.lfa.adapter.SlidingMenuAdapter;
import com.proyectofootball.titanes.lfa.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public Toolbar toolbarParaLogo;
    private RecyclerView recyclerNoticias;
    //public ActionBar actionnBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSliding = (ListView)findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();

        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(R.drawable.ic_stat_silueta_logo_para_notificacion, getResources().getString(R.string.home)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_player, getResources().getString(R.string.teams)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_calendar_time, getResources().getString(R.string.schedule)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_football_play, getResources().getString(R.string.standings)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_first_down_mark, getResources().getString(R.string.estadisticas)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_referee, getResources().getString(R.string.reglamento)));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_info, getResources().getString(R.string.acerca_de)));
        //listSliding.add(new ItemSlideMenu(R.drawable.ic_engranes, getResources().getString(R.string.action_settings)));
        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        toolbarParaLogo = (Toolbar)findViewById(R.id.main_toolbar);
        recyclerNoticias = (RecyclerView)findViewById(R.id.top_stories_recycler);
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set title
                //setTitle(listSliding.get(position).getTitle());
                //item selected
                //listViewSliding.setItemChecked(position, true);
                //Replace fragment
                changeActivity(position);
                //replaceFragment(position);
                //Close menu
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbarParaLogo, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        setSupportActionBar(toolbarParaLogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBarDrawerToggle.syncState();


    }
/*
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_top_stories, container, false);
        recyclerNoticias = (RecyclerView) layout.findViewById(R.id.top_stories_recycler);
        return layout;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(listViewSliding)){
                    drawerLayout.closeDrawer(listViewSliding);
                } else {
                    drawerLayout.openDrawer(listViewSliding);
                }
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    public void changeActivity(int pos) {
        switch (pos) {
            case 0:
                Intent noticiasPrincipalesIntent = new Intent(this, TopStories.class);
                startActivity(noticiasPrincipalesIntent);
                break;
            case 1:
                Intent detalleEquiposIntent = new Intent(this, Teams.class);
                startActivity(detalleEquiposIntent);
                break;
            case 2:
                break;
            case 3:
                Intent standingsIntent = new Intent(this, Standings.class);
                startActivity(standingsIntent);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                Intent acercaDeIntent = new Intent(this, AcercaDe.class);
                startActivity(acercaDeIntent);
                break;
            case 7:
                Intent preferenciasIntent = new Intent(this, Preferencias.class);
                startActivity(preferenciasIntent);
                break;
        }

    }

    private void replaceFragment(int pos) {
        Fragment fragment = null;
        switch (pos) {
            case 0:
                //fragment = new TopStories();
                break;
            case 1:
               // fragment = new Teams();
                break;
            case 2:
                //fragment = new Fragment3();
                break;
            case 3:
               // fragment = new Standings();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
             //   fragment = new AcercaDe();
                break;
            case 7:
              //  fragment = new PreferenciasFragment();
                break;
            default:
                //fragment = new Fragment1();
                break;
        }

       /* if(null!=fragment) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }*/
    }
/*

        //Init component
        //toolBaar = (Toolbar)findViewById(R.id.tooolbar);

//        View view = getLayoutInflater().inflate(R.layout.toolbar, null);
        //Display icon to open/ close sliding list
        actionnBar = getSupportActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        // TODO: Remove the redundant calls to getSupportActionBar()
        //       and use variable actionBar instead
        actionnBar.setDisplayHomeAsUpEnabled(true);
        actionnBar.setHomeButtonEnabled(true);
        actionnBar.setDisplayShowTitleEnabled(false);
        actionnBar.setDisplayShowCustomEnabled(true);
  //      actionnBar.setCustomView(view);

        //Set title
        setTitle(listSliding.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //Close menu
        drawerLayout.closeDrawer(listViewSliding);

        //Display fragment 1 when start
        replaceFragment(0);
        //Hanlde on item click

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set title
                //setTitle(listSliding.get(position).getTitle());
                //item selected
                //listViewSliding.setItemChecked(position, true);
                //Replace fragment
                replaceFragment(position);
                //Close menu
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

}*/

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }*/
}