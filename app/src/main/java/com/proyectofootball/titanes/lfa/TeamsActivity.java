package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofootball.titanes.lfa.menu.MenuGenerico;
import com.proyectofootball.titanes.lfa.model.Equipo;
import com.proyectofootball.titanes.lfa.viewHolders.TeamsViewHolder;

public class TeamsActivity extends AppCompatActivity {


    private DatabaseReference mDadabaseReference;
    private RecyclerView mTeamsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        instanciaElementos();

        MenuGenerico mMenu = new MenuGenerico();
        mMenu.crearMenu(this);





    }

    private void instanciaElementos() {
        mDadabaseReference = FirebaseDatabase.getInstance().getReference().child("2016/equipo");

        mTeamsRecyclerView = (RecyclerView) findViewById(R.id.teams_list);

        mTeamsRecyclerView.setHasFixedSize(true);

        mTeamsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Equipo, TeamsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Equipo, TeamsViewHolder>(Equipo.class, R.layout.teams_row, TeamsViewHolder.class, mDadabaseReference) {
            @Override
            public void populateViewHolder(TeamsViewHolder viewHolder, Equipo equipo, int position) {
                viewHolder.setNombre(equipo.getNombre());


                viewHolder.setGanados(equipo.getGanados());
                viewHolder.setPerdidos(equipo.getPerdidos());
                viewHolder.setBackgrounColor(equipo.getColorPrincipal());
                //falta agregar lo de empatados
                Float porcentaje = 0f;
                int totalPartidos = 0;
                int mPerdidos = Integer.valueOf(equipo.getPerdidos());
                int mGanados = Integer.valueOf(equipo.getGanados());
                if (mGanados > 0) {
                    totalPartidos = mPerdidos + mGanados;

                    if (equipo.getEmpates() != null && !equipo.getEmpates().isEmpty()) {
                        int mEmpates = Integer.valueOf(equipo.getEmpates());
                        totalPartidos = totalPartidos + mEmpates;
                    }
                    porcentaje = Float.valueOf(mGanados) / Float.valueOf(totalPartidos);
                    viewHolder.setPorcentaje(String.format("%.3f", porcentaje));

                } else {
                    viewHolder.setPorcentaje(String.format("%.3f", porcentaje));
                }

            }
        };

        mTeamsRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}