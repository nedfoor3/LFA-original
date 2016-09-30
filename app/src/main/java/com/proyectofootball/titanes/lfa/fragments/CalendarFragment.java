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
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {


    private OnFragmentInteractionListener mListener;


    private DatabaseReference calendarioDbReference;
    private RecyclerView mCalendarRecyclerView;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);


        calendarioDbReference = FirebaseDatabase.getInstance().getReference().child("2016/calendario/jornada");

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
                int resourceIdVisitante = getResources().getIdentifier("logo_" + calendario.getVisitante().toLowerCase() + "_fondo_color_sin_texto", "drawable", "com.proyectofootball.titanes.lfa");
                viewHolder.setImagenVisitante(resourceIdVisitante);
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
