package com.proyectofootball.titanes.lfa.viewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.NewsDetailActivity;
import com.proyectofootball.titanes.lfa.R;
import com.proyectofootball.titanes.lfa.model.Blog;
import com.squareup.picasso.Picasso;

/**
 * Created by Ricardo Rodriguez on 9/29/2016.
 */

public final class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    private Blog blog = new Blog();


    public NewsViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(this);
    }

    public void setTitleNota(String titleNota) {
        TextView post_title = (TextView) mView.findViewById(R.id.post_title);
        post_title.setText(titleNota);
        blog.setEncabezadoNota(titleNota);

    }

    public void setCuerpoNota(String textoNota) {
        TextView post_cuerpo = (TextView) mView.findViewById(R.id.post_text);
        post_cuerpo.setText(textoNota);
        blog.setTextoNota(textoNota);

    }

    public void setImagenNota(Context ctx, String imagenNota) {
        ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
        Picasso.with(ctx).load(imagenNota).into(post_image);
        blog.setImagenNota(imagenNota);
    }

    @Override
    public void onClick(View view) {

        Log.v("AQUIII", "");
        Intent intentDetalleNota = new Intent(view.getContext(), NewsDetailActivity.class);
        intentDetalleNota.putExtra("titulo", this.blog.getEncabezadoNota());
        intentDetalleNota.putExtra("texto", this.blog.getTextoNota());
        intentDetalleNota.putExtra("imagen", this.blog.getImagenNota());
        view.getContext().startActivity(intentDetalleNota);

    }
}
