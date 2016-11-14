package com.proyectofootball.titanes.lfa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.R;

/**
 * Created by Ricardo Rodriguez on 11/14/2016.
 */
public class RosterViewHolder extends RecyclerView.ViewHolder {
    /*View*/
    View mView;

    public RosterViewHolder(View itemView) {
        super(itemView);
        this.mView = itemView;
    }

    public void setPlayerName(String name) {
        TextView tvName = (TextView) mView.findViewById(R.id.player_name);
        tvName.setText(name);
    }

    public void setPlayerNumber(String number) {
        TextView tvName = (TextView) mView.findViewById(R.id.player_number);
        tvName.setText(number);

    }

    public void setPlayerPosition(String position) {
        TextView tvName = (TextView) mView.findViewById(R.id.player_position);
        tvName.setText(position);

    }

    public void setPlayerHeight(String height) {
        TextView tvName = (TextView) mView.findViewById(R.id.player_height);
        tvName.setText(height);
    }

    public void setPlayerWeight(String weight) {
        TextView tvName = (TextView) mView.findViewById(R.id.player_weight);
        tvName.setText(weight);
    }

}
