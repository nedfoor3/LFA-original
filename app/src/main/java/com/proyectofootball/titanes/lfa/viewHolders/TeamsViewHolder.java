package com.proyectofootball.titanes.lfa.viewHolders;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.DetalleEquipos;
import com.proyectofootball.titanes.lfa.R;

/**
 * Created by Ricardo Rodriguez on 9/14/2016.
 */
public final class TeamsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    /*The View*/
    View mView;
    String nombreEquipo;

    public TeamsViewHolder(View itemView) {
        super(itemView);
        this.mView = itemView;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intentDetalleEquipo = new Intent(view.getContext(), DetalleEquipos.class);
        intentDetalleEquipo.putExtra("nombreEquipo", nombreEquipo.toLowerCase());
        view.getContext().startActivity(intentDetalleEquipo);
    }

    public void setNombre(String nombre) {
        TextView nombreEquipo = (TextView) mView.findViewById(R.id.nombre_equipo);
        this.nombreEquipo = nombre;
        nombreEquipo.setText(nombre);
    }

    public void setGanados(String ganados) {
        TextView partidosGanados = (TextView) mView.findViewById(R.id.numero_ganados);
        partidosGanados.setText(ganados);
    }

    public void setPerdidos(String perdidos) {
        TextView partidosPerdidos = (TextView) mView.findViewById(R.id.numero_perdidos);
        partidosPerdidos.setText(perdidos);
    }

    public void setEmpates(String empates) {
        TextView partidosEmpatados = (TextView) mView.findViewById(R.id.numero_empatados);
        partidosEmpatados.setText(empates);
    }

    public void setPorcentaje(String porcentaje) {
        TextView porcentajes = (TextView) mView.findViewById(R.id.porcentaje_juegos_ganados);
        porcentajes.setText(porcentaje);
    }

    public void setBackgrounColor(String backgrounColor) {
        TableRow renglon = (TableRow) mView.findViewById(R.id.renglon_equipos);
        renglon.setBackgroundColor(Color.parseColor(backgrounColor));
    }

    public void setImagenLogo(int imagenLogo) {
        ImageView imageView = (ImageView) mView.findViewById(R.id.logo_equipo_row_equipos);
        imageView.setImageResource(imagenLogo);
    }



}
