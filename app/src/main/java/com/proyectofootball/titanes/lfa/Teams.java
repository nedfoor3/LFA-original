package com.proyectofootball.titanes.lfa;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Teams extends AppCompatActivity {
    public LinearLayout equiposDetalle, detalleEagles, detalleMayas, detalleRaptors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        detalleRaptors = (LinearLayout)findViewById(R.id.linear_raptors);
        detalleEagles = (LinearLayout)findViewById(R.id.linear_aguilas);
        detalleMayas = (LinearLayout)findViewById(R.id.linear_mayas);

        detalleRaptors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalleEquiposOnClickPrueba();
            }
        });

        detalleEagles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalleEquiposOnClickEagles();
            }
        });

        detalleMayas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalleEquiposOnClickMayas();
            }
        });

    }

    public void detalleEquiposOnClickPrueba() {
        Intent intent = new Intent(this, DetalleEquipos.class);
        intent.putExtra("idEquipos", (R.string.raptors)).toString();
        startActivity(intent);
    }

    public void detalleEquiposOnClickEagles() {
        Intent intent = new Intent(this, DetalleEquipos.class);
        intent.putExtra("idEquipos", (R.string.eagles)).toString();
        startActivity(intent);
    }

    public void detalleEquiposOnClickMayas() {
        Intent intent = new Intent(this, DetalleEquipos.class);
        intent.putExtra("idEquipos", (R.string.mayas)).toString();
        startActivity(intent);
    }
}