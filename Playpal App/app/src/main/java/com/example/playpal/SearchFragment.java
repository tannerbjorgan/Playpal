package com.example.playpal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private SearchView searchView;
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView searchResultsRecyclerView;

    private SearchEventAdapter eventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchResultsRecyclerView = view.findViewById(R.id.searchResultsRecyclerView);
        eventAdapter = new SearchEventAdapter(eventList);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getActivity() != null){
            eventList.clear();
            eventList.addAll(((MainActivity) getActivity()).getEventsList());
        }
        searchView = view.findViewById(R.id.searchView);
        //searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


        searchResultsRecyclerView.setAdapter(eventAdapter);
        return view;
    }
    private void filterList(String text){
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event: eventList){
            if(event.getSport().toLowerCase().startsWith((text.toLowerCase()))){
                filteredEvents.add(event);
            }
        }
        if (filteredEvents.isEmpty()){
            Toast.makeText(getContext(), "No Events", Toast.LENGTH_SHORT).show();
        }else{

            eventAdapter.setFilteredList(filteredEvents);
        }
    }
}