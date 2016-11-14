package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofootball.titanes.lfa.menu.MenuGenerico;
import com.proyectofootball.titanes.lfa.model.Blog;
import com.proyectofootball.titanes.lfa.viewHolders.NewsOnlyVideoViewHolder;
import com.proyectofootball.titanes.lfa.viewHolders.NewsViewHolder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mBlogList;

    private DatabaseReference mDadabaseReference;

    private static final int NEWS_ONLY_VIDEO = 1;
    private static final int NEWS_GENERIC = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDadabaseReference = FirebaseDatabase.getInstance().getReference().child("2016/noticias");
        mBlogList = (RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mBlogList.setLayoutManager(linearLayoutManager);

        MenuGenerico mMenu = new MenuGenerico();

        mMenu.crearMenu(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        FirebaseRecyclerAdapter<Blog, RecyclerView.ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, RecyclerView.ViewHolder>(
                Blog.class,
                R.layout.blog_row,
                RecyclerView.ViewHolder.class,
                mDadabaseReference

        ) {

            @Override
            public void populateViewHolder(RecyclerView.ViewHolder viewHolder, Blog model, int position) {


                if (model.getTextoNota() == null && model.getVideoNota() != null) {
                    NewsOnlyVideoViewHolder onlyVideoViewHolder = (NewsOnlyVideoViewHolder) viewHolder;
                    onlyVideoViewHolder.setVideoNota(model.getVideoNota());
                    onlyVideoViewHolder.setTitleNota(model.getEncabezadoNota());


                } else {
                    NewsViewHolder newsViewHolder = (NewsViewHolder) viewHolder;
                    newsViewHolder.setTitleNota(model.getEncabezadoNota());
                    newsViewHolder.setImagenNota(getApplicationContext(), model.getImagenNota());


                    if (model.getTextoNota() != null) {
                        newsViewHolder.setCuerpoNota(model.getTextoNota());
                    } else {
                        newsViewHolder.setCuerpoNota("");
                    }


                    if (model.getVideoNota() != null && !model.getVideoNota().isEmpty()) {
                        newsViewHolder.setVideoNota(model.getVideoNota());
                    }
                }

            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                switch (viewType) {
                    case NEWS_ONLY_VIDEO:
                        View viewOnlyVideo = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news_only_video, parent, false);
                        return new NewsOnlyVideoViewHolder(viewOnlyVideo);
                    case NEWS_GENERIC:
                        View viewGenericNews = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row, parent, false);
                        return new NewsViewHolder(viewGenericNews);
                }

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public int getItemViewType(int position) {

                Blog blog = getItem(position);

                if (blog.getTextoNota() == null && blog.getVideoNota() != null) {
                    return NEWS_ONLY_VIDEO;
                } else {
                    return NEWS_GENERIC;
                }

            }
        };


        mBlogList.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
