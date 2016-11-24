package com.proyectofootball.titanes.lfa.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.proyectofootball.titanes.lfa.AcercaDe;
import com.proyectofootball.titanes.lfa.CalendarioActivity;
import com.proyectofootball.titanes.lfa.MainActivity;
import com.proyectofootball.titanes.lfa.ParaPruebas;
import com.proyectofootball.titanes.lfa.R;
import com.proyectofootball.titanes.lfa.Standings;
import com.proyectofootball.titanes.lfa.StatisticsActivity;
import com.proyectofootball.titanes.lfa.TeamsActivity;
import com.proyectofootball.titanes.lfa.adapter.SlidingMenuAdapter;
import com.proyectofootball.titanes.lfa.model.ItemSlideMenu;

import java.io.File;
import java.io.IOException;
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
        listSliding.add(new ItemSlideMenu(R.drawable.ic_first_down_mark, activity.getResources().getString(R.string.estadisticas)));
        //listSliding.add(new ItemSlideMenu(R.drawable.ic_referee, activity.getResources().getString(R.string.reglamento)));
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

        /*setSupportActionBar(toolbarParaLogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        return toolbarParaLogo;

    }


    private void changeActivity(int pos, Activity activity) {
        switch (pos) {
            case 0:

                if (activity.getClass() != MainActivity.class) {
                    Intent noticiasPrincipalesIntent = new Intent(activity, MainActivity.class);
                    activity.startActivity(noticiasPrincipalesIntent);
                }

                break;
            case 1:
                if (activity.getClass() != TeamsActivity.class) {
                    Intent detalleEquiposIntent = new Intent(activity, TeamsActivity.class);
                    activity.startActivity(detalleEquiposIntent);
                }

                break;
            case 2:
                if (activity.getClass() != CalendarioActivity.class) {
                    Intent intentCalendario = new Intent(activity, CalendarioActivity.class);
                    activity.startActivity(intentCalendario);
                }


                break;
            case 3:
                if (activity.getClass() != Standings.class) {
                    Intent standingsIntent = new Intent(activity, Standings.class);
                    activity.startActivity(standingsIntent);
                }

                break;
            case 4:
                if (activity.getClass() != StatisticsActivity.class) {
                    Intent statisticsIntent = new Intent(activity, StatisticsActivity.class);
                    activity.startActivity(statisticsIntent);
                }
                break;

            case 5:
                if (activity.getClass() != AcercaDe.class) {
                    Intent acercaDeIntent = new Intent(activity, AcercaDe.class);
                    activity.startActivity(acercaDeIntent);
                }

                break;
            case 6:
                if (activity.getClass() != ParaPruebas.class) {
                    Intent preferenciasIntent = new Intent(activity, ParaPruebas.class);
                    activity.startActivity(preferenciasIntent);
                }

                break;
        }

    }

    /*https://firebasestorage.googleapis.com/v0/b/lfa-desarrollo.appspot.com/o/archivos_notas%2FResultados%20TRYOUTS%205%20y%206%20de%20Diciembre%20%E2%80%93%20LFA.pdf?alt=media&token=3da39694-9ad3-4d75-81a7-55df012c367a*/

    private void downloadRulesPDF() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference httpsReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/lfa-desarrollo.appspot.com/o/archivos_notas%2FResultados%20TRYOUTS%205%20y%206%20de%20Diciembre%20%E2%80%93%20LFA.pdf?alt=media&token=3da39694-9ad3-4d75-81a7-55df012c367a");

        try {
            File localFile = File.createTempFile("pdf", "hola.pdf");
            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Log.v("asdfasd", "1");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(listViewSliding)) {
                    drawerLayout.closeDrawer(listViewSliding);
                } else {
                    drawerLayout.openDrawer(listViewSliding);
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
