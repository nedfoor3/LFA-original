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
 *
 * Metodos para llenar el row de equipos
 */
public final class TeamsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /*Nombre Equipo Static*/
    private final static String NOMBRE_EQUIPO = "nombreEquipo";
    /*Partidos Ganados Static*/
    private final static String PARTIDOS_GANADOS = "ganados";
    /*Partidos Perdidos Static*/
    private final static String PARTIDOS_PERDIDOS = "perdidos";
    /*Partidos Empatados Static*/
    private final static String PARTIDOS_EMPATADOS = "empatados";
    /*The View*/
    View mView;
    /*Nombre Equipo varible*/
    String nombreEquipo;
    /*Ganados*/
    String ganados;
    /*Perdidos*/
    String perdidos;
    /*Empatados*/
    String empatados;


    /**
     * Teams View Holder Constructor
     *
     * @param itemView View
     */
    public TeamsViewHolder(View itemView) {
        super(itemView);
        this.mView = itemView;
        itemView.setOnClickListener(this);
    }

    /**
     * On Click Override
     * @param view View
     */
    @Override
    public void onClick(View view) {
        Intent intentDetalleEquipo = new Intent(view.getContext(), DetalleEquipos.class);
        intentDetalleEquipo.putExtra(this.NOMBRE_EQUIPO, this.nombreEquipo);
        intentDetalleEquipo.putExtra(this.PARTIDOS_GANADOS, this.ganados);
        intentDetalleEquipo.putExtra(this.PARTIDOS_PERDIDOS, this.perdidos);

        if (this.empatados != null) {
            intentDetalleEquipo.putExtra(this.PARTIDOS_EMPATADOS, this.empatados);
        }
        view.getContext().startActivity(intentDetalleEquipo);
    }

    /**
     * Set Nombre
     * @param nombre String
     */
    public void setNombre(String nombre) {
        TextView nombreEquipo = (TextView) mView.findViewById(R.id.nombre_equipo);
        this.nombreEquipo = nombre;
        nombreEquipo.setText(nombre);
    }

    /**
     * Set Ganados
     * @param ganados String
     */
    public void setGanados(String ganados) {
        TextView partidosGanados = (TextView) mView.findViewById(R.id.numero_ganados);
        partidosGanados.setText(ganados);
        this.ganados = ganados;
    }

    /**
     * Set Perdidos
     * @param perdidos String
     */
    public void setPerdidos(String perdidos) {
        TextView partidosPerdidos = (TextView) mView.findViewById(R.id.numero_perdidos);
        partidosPerdidos.setText(perdidos);
        this.perdidos = perdidos;
    }

    /**
     * Set Empates
     * @param empates String
     */
    public void setEmpates(String empates) {
        TextView partidosEmpatados = (TextView) mView.findViewById(R.id.numero_empatados);
        partidosEmpatados.setText(empates);
        this.empatados = empates;
    }

    /**
     * Set Porcentajes
     * @param porcentaje String
     */
    public void setPorcentaje(String porcentaje) {
        TextView porcentajes = (TextView) mView.findViewById(R.id.porcentaje_juegos_ganados);
        porcentajes.setText(porcentaje);
    }

    /**
     * Set Background Color
     * @param backgrounColor String
     */
    public void setBackgrounColor(String backgrounColor) {
        TableRow renglon = (TableRow) mView.findViewById(R.id.renglon_equipos);
        renglon.setBackgroundColor(Color.parseColor(backgrounColor));
    }

    /**
     * Set Imagen Logo
     * @param imagenLogo int
     */
    public void setImagenLogo(int imagenLogo) {
        ImageView imageView = (ImageView) mView.findViewById(R.id.logo_equipo_row_equipos);
        imageView.setImageResource(imagenLogo);
    }

}
