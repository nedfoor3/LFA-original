package com.proyectofootball.titanes.lfa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.R;
import com.proyectofootball.titanes.lfa.model.Blog;

/**
 * Created by Ricardo Rodriguez on 10/25/2016.
 */

public class NewsOnlyVideoViewHolder extends RecyclerView.ViewHolder {

    View mView;
    private Blog blog = new Blog();
    private static final String HTML_HEADER = "<html><body>";


    private static final String HTML__FOTTER = "</body></html>";

    public NewsOnlyVideoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setTitleNota(String titleNota) {
        TextView post_title = (TextView) mView.findViewById(R.id.post_title);
        post_title.setText(titleNota);
    }

    public void setVideoNota(String videoNota) {

        WebView videoWebView = (WebView) mView.findViewById(R.id.web_view_only_video);
        videoWebView.getSettings().setJavaScriptEnabled(true);
        videoWebView.getSettings().setLoadWithOverviewMode(true);
        videoWebView.getSettings().setUseWideViewPort(true);
        videoWebView.clearCache(true);


        videoWebView.loadData(HTML_HEADER + videoNota + HTML__FOTTER, "text/html; charset=utf-8", "UTF-8");


    }

}
