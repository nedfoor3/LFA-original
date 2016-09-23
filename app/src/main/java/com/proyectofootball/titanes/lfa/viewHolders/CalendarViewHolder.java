package com.proyectofootball.titanes.lfa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.R;

/**
 * Created by Ricardo Rodriguez on 9/20/2016.
 */
public class CalendarViewHolder extends RecyclerView.ViewHolder {

    /*View*/
    View mView;

    public CalendarViewHolder(View itemView) {
        super(itemView);
        this.mView = itemView;
    }

    public void setLocal(String local) {
        TextView tvLocal = (TextView) mView.findViewById(R.id.tvEquipoLocal);
        tvLocal.setText(local);
    }

    public void setImagenLocal(int imagenLocal) {
        ImageView imageView = (ImageView) mView.findViewById(R.id.logo_equipo_local);
        imageView.setImageResource(imagenLocal);
    }

    public void setVisitante(String visitante) {
        TextView tvVisitante = (TextView) mView.findViewById(R.id.tvEquipoVisitante);
        tvVisitante.setText(visitante);
    }

    public void setImagenVisitante(int imagenVisitante) {
        ImageView imageView = (ImageView) mView.findViewById(R.id.logo_equipo_visitante);
        imageView.setImageResource(imagenVisitante);
    }

    public void setEstatus(String estatus) {
        TextView tvEstatus = (TextView) mView.findViewById(R.id.tvEstatus);
        tvEstatus.setText(estatus);
    }

    public void setHorario(String horario) {
        TextView tvHorario = (TextView) mView.findViewById(R.id.tvFechaPartido);
        tvHorario.setText(horario);
    }


}
