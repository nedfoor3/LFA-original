package com.proyectofootball.titanes.lfa.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofootball.titanes.lfa.R;
import com.proyectofootball.titanes.lfa.model.Jugador;
import com.proyectofootball.titanes.lfa.viewHolders.RosterViewHolder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RosterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RosterFragment extends Fragment {
    private CalendarFragment.OnFragmentInteractionListener mListener;
    private String nombreEquipo;
    /*Nombre Equipo Static*/
    private final static String NOMBRE_EQUIPO = "nombreEquipo";

    private static final int PER = 0;
    private static final int UNEVEN = 1;

    String[] color = {"#FFFFFF", "#D1FFFFFF"};

    private DatabaseReference rosterDBReference;
    private RecyclerView mRosterRecyclerView;

    public RosterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.nombreEquipo = getArguments().getString(NOMBRE_EQUIPO).toLowerCase();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roster, container, false);


        rosterDBReference = FirebaseDatabase.getInstance().getReference().child("2016/equipo/" + nombreEquipo + "/jugador");

        mRosterRecyclerView = (RecyclerView) rootView.findViewById(R.id.roster_list);

        mRosterRecyclerView.setHasFixedSize(true);

        mRosterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerAdapter<Jugador, RosterViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Jugador, RosterViewHolder>(Jugador.class, R.layout.row_player, RosterViewHolder.class, rosterDBReference) {
            @Override
            protected void populateViewHolder(RosterViewHolder viewHolder, Jugador jugador, int position) {

                String fullName = jugador.getNombre().substring(0, 1).toUpperCase() + jugador.getNombre().substring(1).toLowerCase();
                fullName = fullName.concat(" ");
                fullName = fullName.concat(jugador.getApellidoPaterno().substring(0, 1) + jugador.getApellidoPaterno().substring(1).toLowerCase());

                viewHolder.setPlayerName(fullName);

                viewHolder.setPlayerNumber(jugador.getNumero());
                viewHolder.setPlayerPosition(jugador.getPosicion());
                viewHolder.setPlayerHeight(jugador.getEstatura());
                viewHolder.setPlayerWeight(jugador.getPeso());

            }

            @Override
            public RosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (viewType) {
                    case PER:
                        View viewPer = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_player, parent, false);
                        viewPer.setBackgroundColor(getResources().getColor(R.color.rojo_transparente_light));
                        return new RosterViewHolder(viewPer);
                    case UNEVEN:
                        View viewUneven = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_player, parent, false);
                        return new RosterViewHolder(viewUneven);
                }

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public int getItemViewType(int position) {

                int resultadoModulo = position % 2;
                Log.v("Resultado", "" + resultadoModulo);
                if (resultadoModulo == 0) {
                    return PER;
                } else {
                    return UNEVEN;
                }


            }
        };

        mRosterRecyclerView.setAdapter(firebaseRecyclerAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRosterRecyclerView.setLayoutManager(llm);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
