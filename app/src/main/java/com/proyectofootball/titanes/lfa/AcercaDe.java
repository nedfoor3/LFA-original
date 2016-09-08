package com.proyectofootball.titanes.lfa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.R;

public class AcercaDe extends AppCompatActivity {
public TextView tvContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        tvContacto = (TextView)findViewById(R.id.contacto_rawr);
        tvContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandarCorreo(view);
            }
        });

    }

    public void mandarCorreo(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        //intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"soporte@rawrstudio.com"});
        startActivity(intent);
    }

}