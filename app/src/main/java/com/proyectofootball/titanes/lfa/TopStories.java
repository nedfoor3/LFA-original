package com.proyectofootball.titanes.lfa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class TopStories extends AppCompatActivity {
    private RecyclerView recyclerNoticias;
    public ActionBar actionnBar;
    private TextView tvName, tvAdress, tvUrl;

   // @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_top_stories, container, false);
        //recyclerNoticias = (RecyclerView) layout.findViewById(R.id.top_stories_recycler);
    return layout;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    public void detalleEquiposOnClickPrueba() {
        Intent intent = new Intent(this, DetalleEquipos.class);
        startActivity(intent);
    }
}