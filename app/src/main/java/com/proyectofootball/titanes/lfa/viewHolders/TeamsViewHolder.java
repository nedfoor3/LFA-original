package com.proyectofootball.titanes.lfa.viewHolders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.R;

/**
 * Created by Ricardo Rodriguez on 9/14/2016.
 */
public final class TeamsViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public TeamsViewHolder(View itemView) {
        super(itemView);
        this.mView = itemView;

    }

    public void setNombre(String nombre) {
        TextView nombreEquipo = (TextView) mView.findViewById(R.id.nombre_equipo);
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
        TextView partidosEmpatados = (TextView) mView.findViewById(R.id.porcentaje_juegos_ganados);
        partidosEmpatados.setText(porcentaje);
    }

    public void setBackgrounColor(String backgrounColor) {
        TableRow partidosEmpatados = (TableRow) mView.findViewById(R.id.renglon_equipos);
        partidosEmpatados.setBackgroundColor(Color.parseColor(backgrounColor));
    }

}
