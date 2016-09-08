package com.proyectofootball.titanes.lfa.model;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.proyectofootball.titanes.lfa.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContenidoNoticias {
    private String name;
    private String adress;
    private String url;

    public ContenidoNoticias(String name, String adress, String url) {
        this.name = name;
        this.adress = adress;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ContenidoNoticias() {
        // Required empty public constructor
    }


}
