package com.example.playpal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {
    private SearchView searchView;
    private RecyclerView searchResultsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.searchView);
        searchResultsRecyclerView = view.findViewById(R.id.searchResultsRecyclerView);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}