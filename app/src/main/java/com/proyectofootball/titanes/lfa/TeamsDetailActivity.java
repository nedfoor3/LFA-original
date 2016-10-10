package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.proyectofootball.titanes.lfa.adapter.DetailsTeamPagerAdapter;

public class TeamsDetailActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PagerAdapter adapter;
    private TextView tvNombreEquipoCabecera;
    private TextView tvRecordEquipoToolbar;
    private ImageView imgLogoEquipoToolbar;

    /*Nombre Equipo Static*/
    private final static String NOMBRE_EQUIPO = "nombreEquipo";
    /*Partidos Ganados Static*/
    private final static String PARTIDOS_GANADOS = "ganados";
    /*Partidos Perdidos Static*/
    private final static String PARTIDOS_PERDIDOS = "perdidos";
    /*Partidos Empatados Static*/
    private final static String PARTIDOS_EMPATADOS = "empatados";
    /*Nombre Equipo*/
    private String nombreEquipo;
    /*Record Equipo*/
    private String recordEquipo;
    /*Ganados*/
    private String ganados;
    /*Perdidos*/
    private String perdidos;
    /*Empatados*/
    private String empatados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_detail);

        instanciarElementos();

        cargaDetallesEquipo();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        /*MenuGenerico mMenu = new MenuGenerico();
        mMenu.crearMenu(this);*/


    }

    private void instanciarElementos() {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);


        tvNombreEquipoCabecera = (TextView) findViewById(R.id.tv_equipo_en_toolbar);
        tvRecordEquipoToolbar = (TextView) findViewById(R.id.record_equipo_en_toolbar);
        imgLogoEquipoToolbar = (ImageView) findViewById(R.id.logo_equipo_en_toolbar);

        Bundle extras = getIntent().getExtras();

        this.nombreEquipo = extras.getString(NOMBRE_EQUIPO);
        this.ganados = extras.getString(PARTIDOS_GANADOS);
        this.perdidos = extras.getString(PARTIDOS_PERDIDOS);
        this.empatados = extras.getString(PARTIDOS_EMPATADOS);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new DetailsTeamPagerAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new
                                                   TabLayout.OnTabSelectedListener() {
                                                       @Override
                                                       public void onTabSelected(TabLayout.Tab tab) {
                                                           viewPager.setCurrentItem(tab.getPosition());
                                                       }

                                                       @Override
                                                       public void onTabUnselected(TabLayout.Tab tab) {

                                                       }

                                                       @Override
                                                       public void onTabReselected(TabLayout.Tab tab) {

                                                       }

                                                   });

        tabLayout.setupWithViewPager(viewPager);


    }

    /**
     * Metodo para cargar los detalles del equipo en la toolbar
     */
    private void cargaDetallesEquipo() {


        if (this.empatados != null) {
            this.recordEquipo = this.ganados + " - " + this.perdidos + " - " + this.empatados;
        } else {
            this.recordEquipo = this.ganados + " - " + this.perdidos;
        }

        tvNombreEquipoCabecera.setText(nombreEquipo);
        tvRecordEquipoToolbar.setText(recordEquipo);

        int resourceId = getResources().getIdentifier("logo_" + nombreEquipo.toLowerCase() + "_fondo_color_sin_texto", "drawable", "com.proyectofootball.titanes.lfa");
        imgLogoEquipoToolbar.setImageResource(resourceId);
    }
}
