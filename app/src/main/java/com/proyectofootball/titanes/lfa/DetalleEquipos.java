package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofootball.titanes.lfa.adapter.ScheduleRosterAdapter;
import com.proyectofootball.titanes.lfa.fragments.Rosters;
import com.proyectofootball.titanes.lfa.fragments.TeamSchedule;
import com.proyectofootball.titanes.lfa.menu.MenuGenerico;

public class DetalleEquipos extends AppCompatActivity {

    private String idEquiposParaTema;
    private TabLayout tabLayoutRosterSchedule;
    private ViewPager pagerRosterSchedule;
    private ScheduleRosterAdapter scheduleRosterAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayoutDetalleEquipos;
    private ActionBar actionBar;

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

    private DatabaseReference calendarioDbReference;
    private DatabaseReference rosterDbReference;

    private RecyclerView mCalendarioList;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipos);

        Log.v("DETALLE", "Entramos a detalle");
        instanciarElementos();

        cargaDetallesEquipo();


        MenuGenerico mMenu = new MenuGenerico();
        mMenu.crearMenu(this);

    }


    /**
     * Metodo para instanciar los elementos a usar en la actividad
     */
    private void instanciarElementos() {

        tabLayoutRosterSchedule = (TabLayout) findViewById(R.id.tab_selector_roster_calendario);
        tabLayoutRosterSchedule.setTabMode(TabLayout.MODE_FIXED);

        pagerRosterSchedule = (ViewPager) findViewById(R.id.pager_selector_roster_calendario);
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


        /*---------------------------------------------------------------------*/
        tvNombreEquipoCabecera = (TextView) findViewById(R.id.tv_equipo_en_toolbar);
        tvRecordEquipoToolbar = (TextView) findViewById(R.id.record_equipo_en_toolbar);
        imgLogoEquipoToolbar = (ImageView) findViewById(R.id.logo_equipo_en_toolbar);

        Bundle extras = getIntent().getExtras();

        this.nombreEquipo = extras.getString(NOMBRE_EQUIPO);
        this.ganados = extras.getString(PARTIDOS_GANADOS);
        this.perdidos = extras.getString(PARTIDOS_PERDIDOS);
        this.empatados = extras.getString(PARTIDOS_EMPATADOS);

        calendarioDbReference = FirebaseDatabase.getInstance().getReference().child("2016/calendario/jornada");
        rosterDbReference = FirebaseDatabase.getInstance().getReference("2016/equipo/" + this.nombreEquipo.toLowerCase() + "/jugador");


        /*mCalendarioList = (RecyclerView) findViewById(R.id.calendar_list);

        mCalendarioList.setHasFixedSize(true);

        mCalendarioList.setLayoutManager(new LinearLayoutManager(this));*/

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

    @Override
    protected void onStart() {
        super.onStart();
    }
}
