package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.model.Blog;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {


    private ImageView imagenCabecera;
    private TextView tvTitulo;
    private TextView tvTexto;
    private TextView tvFecha;
    private Bundle extras;
    private Blog nota = new Blog();
    private static final String TITULO = "titulo";
    private static final String NOTA = "nota";
    private static final String IMAGEN = "imagen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        instanciarElementos();

        cargaDetalleNoticia();
    }

    /**
     * Metodo para instanciar los elementos
     */
    private void instanciarElementos() {
        imagenCabecera = (ImageView) findViewById(R.id.post_image);
        tvFecha = (TextView) findViewById(R.id.post_date);
        tvTexto = (TextView) findViewById(R.id.post_text);
        tvTitulo = (TextView) findViewById(R.id.post_title);

        extras = getIntent().getExtras();
        nota.setImagenNota(extras.getString(IMAGEN));
        nota.setEncabezadoNota(extras.getString(TITULO));
        nota.setTextoNota(extras.getString(NOTA));
    }

    /**
     * Metodo para cargar los elementos de la noticia
     */
    private void cargaDetalleNoticia() {

        Picasso.with(this).load(nota.getImagenNota()).into(imagenCabecera);
        tvTitulo.setText(nota.getEncabezadoNota());
        tvTexto.setText(Html.fromHtml(nota.getTextoNota()));
    }
}
