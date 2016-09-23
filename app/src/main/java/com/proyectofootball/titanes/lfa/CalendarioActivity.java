package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofootball.titanes.lfa.menu.MenuGenerico;
import com.proyectofootball.titanes.lfa.model.Calendario;
import com.proyectofootball.titanes.lfa.viewHolders.CalendarViewHolder;

public class CalendarioActivity extends AppCompatActivity {

    private DatabaseReference calendarioDbReference;
    private RecyclerView mCalendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        MenuGenerico mMenu = new MenuGenerico();
        mMenu.crearMenu(this);

        instanciarElementos();
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
                int resourceIdVisitante = getResources().getIdentifier("logo_" + calendario.getVisitante().toLowerCase() + "_fondo_color_sin_texto", "drawable", "com.proyectofootball.titanes.lfa");
                viewHolder.setImagenVisitante(resourceIdVisitante);
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


    }
}
