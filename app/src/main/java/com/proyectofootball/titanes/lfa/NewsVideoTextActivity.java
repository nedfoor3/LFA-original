package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.proyectofootball.titanes.lfa.model.Blog;

public class NewsVideoTextActivity extends AppCompatActivity {

    private WebView videoWebView;
    private WebView textWebView;
    private static final String TITULO = "titulo";
    private static final String NOTA = "nota";
    private static final String IMAGEN = "imagen";
    private static final String VIDEO = "video";
    private Bundle extras;
    private Blog nota = new Blog();

    private static final String HTML_HEADER = "<html><body>";
    private static final String HTML__FOTTER = "</body></html>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_video_text);
        instanciarElementos();
        configurarWebViews();
        cargaDetalles();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    /**
     * Metodo para instanciar los elementos a usar en la actividad
     */
    private void instanciarElementos() {

        videoWebView = (WebView) findViewById(R.id.web_view_video);
        textWebView = (WebView) findViewById(R.id.web_view_text);
        extras = getIntent().getExtras();

    }

    /**
     * Metodo para configurar los parametros de los web view
     */
    private void configurarWebViews() {

        videoWebView.getSettings().setJavaScriptEnabled(true);
        videoWebView.getSettings().setLoadWithOverviewMode(true);
        videoWebView.getSettings().setUseWideViewPort(true);
        videoWebView.clearCache(true);


        textWebView.getSettings().setJavaScriptEnabled(true);
        textWebView.clearCache(true);
    }

    /**
     * Metodo para cargar los detalles del equipo en la toolbar
     */
    private void cargaDetalles() {


        nota.setImagenNota(extras.getString(IMAGEN));
        nota.setEncabezadoNota(extras.getString(TITULO));
        nota.setTextoNota(extras.getString(NOTA));
        nota.setVideoNota(extras.getString(VIDEO));

        videoWebView.loadData(HTML_HEADER + nota.getVideoNota() + HTML__FOTTER, "text/html; charset=utf-8", "UTF-8");
        textWebView.loadData(nota.getTextoNota(), "text/html; charset=UTF-8", null);


    }

}
