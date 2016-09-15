package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
                //falta agregar lo de empatados
                double porcentaje = 0.0;
                int totalPartidos = 0;
                int mPerdidos = Integer.valueOf(equipo.getPerdidos());
                int mGanados = Integer.valueOf(equipo.getGanados());

                if (mGanados > 0) {
                    totalPartidos = mPerdidos + mGanados;
                    porcentaje = Double.valueOf(mGanados) / Double.valueOf(totalPartidos);
                    viewHolder.setPorcentaje(String.format("%.4f", porcentaje));
                } else {
                    viewHolder.setPorcentaje(String.format("%.4f", porcentaje));
                }

            }
        };

        mTeamsRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}