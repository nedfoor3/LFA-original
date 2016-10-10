package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.proyectofootball.titanes.lfa.model.Blog;

public class NewsVideoActivity extends AppCompatActivity {

    private WebView videoWebView;
    private static final String VIDEO = "video";
    private Bundle extras;
    private Blog nota = new Blog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_video);

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

        videoWebView = (WebView) findViewById(R.id.web_view_only_video);
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
    }

    /**
     * Metodo para cargar los detalles del equipo en la toolbar
     */
    private void cargaDetalles() {
        nota.setVideoNota(extras.getString(VIDEO));

        videoWebView.loadUrl(nota.getVideoNota());
    }
}
