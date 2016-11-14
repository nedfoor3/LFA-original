package com.proyectofootball.titanes.lfa.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofootball.titanes.lfa.R;
import com.proyectofootball.titanes.lfa.model.Calendario;
import com.proyectofootball.titanes.lfa.viewHolders.CalendarViewHolder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CalendarFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private String nombreEquipo;
    /*Nombre Equipo Static*/
    private final static String NOMBRE_EQUIPO = "nombreEquipo";


    private DatabaseReference calendarioDbReference;
    private RecyclerView mCalendarRecyclerView;

    public CalendarFragment() {
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);


        calendarioDbReference = FirebaseDatabase.getInstance().getReference().child("2016/equipo/" + nombreEquipo + "/partidos");

        mCalendarRecyclerView = (RecyclerView) rootView.findViewById(R.id.calendar_list);

        mCalendarRecyclerView.setHasFixedSize(true);

        mCalendarRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerAdapter<Calendario, CalendarViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Calendario, CalendarViewHolder>(Calendario.class, R.layout.row_calendar, CalendarViewHolder.class, calendarioDbReference) {
            @Override
            protected void populateViewHolder(CalendarViewHolder viewHolder, Calendario calendario, int position) {

                if (calendario.getEstatus() != null) {

                    viewHolder.setLocal(calendario.getMarcadorLocal());
                    viewHolder.setVisitante(calendario.getMarcadorVisitante());

                    if ("terminado".compareTo(calendario.getEstatus().toLowerCase()) == 0) {
                        viewHolder.setEstatus(getString(R.string.final_texto));
                    } else {
                        viewHolder.setEstatus(getString(R.string.jugando_texto));
                    }
                } else {
                    viewHolder.setLocal(calendario.getLocal().substring(0, 3));
                    viewHolder.setVisitante(calendario.getVisitante().substring(0, 3));
                }
                int resourceIdLocal = getResources().getIdentifier("logo_" + calendario.getLocal().toLowerCase() + "_fondo_color_sin_texto", "drawable", "com.proyectofootball.titanes.lfa");
                viewHolder.setImagenLocal(resourceIdLocal);
                int primaryColorLocal = getResources().getIdentifier("primary_" + calendario.getLocal().toLowerCase(), "color", "com.proyectofootball.titanes.lfa");
                primaryColorLocal = getResources().getColor(primaryColorLocal);
                viewHolder.setBackgroundLocal(primaryColorLocal);


                int resourceIdVisitante = getResources().getIdentifier("logo_" + calendario.getVisitante().toLowerCase() + "_fondo_color_sin_texto", "drawable", "com.proyectofootball.titanes.lfa");
                viewHolder.setImagenVisitante(resourceIdVisitante);
                int primaryColorVisitante = getResources().getIdentifier("primary_" + calendario.getVisitante().toLowerCase(), "color", "com.proyectofootball.titanes.lfa");
                primaryColorVisitante = getResources().getColor(primaryColorVisitante);
                viewHolder.setBackgroundVisitante(primaryColorVisitante);
                viewHolder.setHorario(calendario.getFecha().concat(" ").concat(calendario.getHora()));
            }
        };
        mCalendarRecyclerView.setAdapter(firebaseRecyclerAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mCalendarRecyclerView.setLayoutManager(llm);

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
