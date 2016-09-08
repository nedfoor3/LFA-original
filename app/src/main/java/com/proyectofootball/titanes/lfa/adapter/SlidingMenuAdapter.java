package com.proyectofootball.titanes.lfa.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.R;
import com.proyectofootball.titanes.lfa.model.ItemSlideMenu;

import java.util.List;

/**
 * Created by Tonatiuh on 15/08/2016.
 */
public class SlidingMenuAdapter extends BaseAdapter {

    private Context context;
    private List<ItemSlideMenu> lstItem;

    public SlidingMenuAdapter(Context context, List<ItemSlideMenu> lstItem) {
        this.context = context;
        this.lstItem = lstItem;
    }

    @Override
    public int getCount() {
        return lstItem.size();
    }

    @Override
    public Object getItem(int position) {
        return lstItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_sliding_menu, null);
        ImageView img =(ImageView)v.findViewById(R.id.item_img);
        TextView tv =(TextView)v.findViewById(R.id.item_title);
        tv.startAnimation(AnimationUtils.loadAnimation(context.getApplicationContext(), android.R.anim.slide_in_left));
        img.startAnimation(AnimationUtils.loadAnimation(context.getApplicationContext(), android.R.anim.slide_in_left));
        ItemSlideMenu item = lstItem.get(position);
        img.setImageResource(item.getImgId());
        tv.setText(item.getTitle());
        return v;
    }

}
