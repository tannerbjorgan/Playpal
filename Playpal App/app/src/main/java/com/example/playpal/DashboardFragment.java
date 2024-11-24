package com.example.playpal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private RecyclerView eventsRecyclerView;
    private TextView titleTextView;
    private EventAdapter eventAdapter;
    private List<Event> eventsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Initialize views
        eventsRecyclerView = view.findViewById(R.id.eventsRecyclerView);
        titleTextView = view.findViewById(R.id.titleTextView);

        // Get events from arguments if available
        if (getArguments() != null) {
            ArrayList<Event> events = getArguments().getParcelableArrayList("events");
            if (events != null) {
                eventsList.clear();
                eventsList.addAll(events);
            }
        }

        // If no events from arguments, get from MainActivity
        if (eventsList.isEmpty() && getActivity() != null) {
            eventsList.addAll(((MainActivity) getActivity()).getEventsList());
        }

        // Set up RecyclerView
        eventAdapter = new EventAdapter(eventsList);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventsRecyclerView.setAdapter(eventAdapter);

        return view;
    }

    public void updateEvents(List<Event> events) {
        eventsList.clear();
        eventsList.addAll(events);
        if (eventAdapter != null) {
            eventAdapter.notifyDataSetChanged();
        }
    }
}

