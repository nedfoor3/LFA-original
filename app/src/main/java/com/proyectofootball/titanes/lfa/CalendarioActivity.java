package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofootball.titanes.lfa.menu.MenuGenerico;
import com.proyectofootball.titanes.lfa.model.Calendario;
import com.proyectofootball.titanes.lfa.viewHolders.CalendarViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarioActivity extends AppCompatActivity {

    private DatabaseReference calendarioDbReference;
    private RecyclerView mCalendarRecyclerView;
    private Spinner spinner;
    private final static int FIRST_YEAR = 2016;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        MenuGenerico mMenu = new MenuGenerico();
        mMenu.crearMenu(this);

        instanciarElementos();

        populateSpinner();
    }

    /**
     *
     */
    private void populateSpinner() {
        int actualYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> listYears = new ArrayList<>();
        if (actualYear > FIRST_YEAR) {
            for (int i = FIRST_YEAR; i <= actualYear; i++) {
                listYears.add(i);
            }

        } else {
            listYears.add(FIRST_YEAR);
        }
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, listYears);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Calendario, CalendarViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Calendario, CalendarViewHolder>(Calendario.class, R.layout.row_calendar, CalendarViewHolder.class, calendarioDbReference) {
            @Override
            protected void populateViewHolder(CalendarViewHolder viewHolder, Calendario calendario, int position) {

                if (calendario.getEstatus() != null) {

                    viewHolder.setLocal(calendario.getMarcadorLocal());
                    viewHolder.setVisitante(calendario.getMarcadorVisitante());

                    if ("terminado".compareTo(calendario.getEstatus().toLowerCase()) == 0) {
                        viewHolder.setEstatus(getString(R.string.final_texto));
                    } else {
                        viewHolder.setEstatus(getString(R.string.jugando_texto));
                    }
                } else {
                    viewHolder.setLocal(calendario.getLocal().substring(0, 3));
                    viewHolder.setVisitante(calendario.getVisitante().substring(0, 3));
                }
                int resourceIdLocal = getResources().getIdentifier("logo_" + calendario.getLocal().toLowerCase() + "_fondo_color_sin_texto", "drawable", "com.proyectofootball.titanes.lfa");
                viewHolder.setImagenLocal(resourceIdLocal);
                int primaryColorLocal = getResources().getIdentifier("primary_" + calendario.getLocal().toLowerCase(), "color", "com.proyectofootball.titanes.lfa");
                primaryColorLocal = getResources().getColor(primaryColorLocal);
                viewHolder.setBackgroundLocal(primaryColorLocal);


                int resourceIdVisitante = getResources().getIdentifier("logo_" + calendario.getVisitante().toLowerCase() + "_fondo_color_sin_texto", "drawable", "com.proyectofootball.titanes.lfa");
                viewHolder.setImagenVisitante(resourceIdVisitante);
                int primaryColorVisitante = getResources().getIdentifier("primary_" + calendario.getVisitante().toLowerCase(), "color", "com.proyectofootball.titanes.lfa");
                primaryColorVisitante = getResources().getColor(primaryColorVisitante);
                viewHolder.setBackgroundVisitante(primaryColorVisitante);
                viewHolder.setHorario(calendario.getFecha().concat(" ").concat(calendario.getHora()));
            }
        };
        mCalendarRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    /**
     * Metodo para instanciar los elementos a usar en la actividad
     */
    private void instanciarElementos() {

        calendarioDbReference = FirebaseDatabase.getInstance().getReference().child("2016/calendario/jornada");

        mCalendarRecyclerView = (RecyclerView) findViewById(R.id.calendar_list);

        mCalendarRecyclerView.setHasFixedSize(true);

        mCalendarRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);
        spinner.setEnabled(true);


    }
}
