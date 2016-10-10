package com.proyectofootball.titanes.lfa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofootball.titanes.lfa.menu.MenuGenerico;
import com.proyectofootball.titanes.lfa.model.Blog;
import com.proyectofootball.titanes.lfa.viewHolders.NewsViewHolder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mBlogList;

    private DatabaseReference mDadabaseReference;

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


        FirebaseRecyclerAdapter<Blog, NewsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, NewsViewHolder>(
                Blog.class,
                R.layout.blog_row,
                NewsViewHolder.class,
                mDadabaseReference

        ) {
            @Override
            public void populateViewHolder(NewsViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitleNota(model.getEncabezadoNota());
                viewHolder.setImagenNota(getApplicationContext(), model.getImagenNota());


                if (model.getTextoNota() != null) {
                    viewHolder.setCuerpoNota(model.getTextoNota());
                } else {
                    viewHolder.setCuerpoNota("");
                }


                if (model.getVideoNota() != null && !model.getVideoNota().isEmpty()) {
                    viewHolder.setVideoNota(model.getVideoNota());
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
