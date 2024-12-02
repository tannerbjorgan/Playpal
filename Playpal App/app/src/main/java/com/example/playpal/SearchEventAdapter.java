package com.example.playpal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchEventAdapter extends RecyclerView.Adapter<SearchEventAdapter.EventViewHolder>{

        private List<Event> events;
        public void setFilteredList(List<Event> fitleredList){
            this.events = fitleredList;
            notifyDataSetChanged();
        }
        public SearchEventAdapter(List<Event> events) {
            this.events = events;
        }

        @NonNull
        @Override
        public com.example.playpal.SearchEventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_event, parent, false);
            return new com.example.playpal.SearchEventAdapter.EventViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.playpal.SearchEventAdapter.EventViewHolder holder, int position) {
            Event event = events.get(position);
            holder.bind(event);
        }

        @Override
        public int getItemCount() {
            return events.size();
        }

        class EventViewHolder extends RecyclerView.ViewHolder {
            private TextView sportText;
            private TextView dateTimeText;
            private TextView locationText;

            public EventViewHolder(@NonNull View itemView) {
                super(itemView);
                sportText = itemView.findViewById(R.id.sportText);
                dateTimeText = itemView.findViewById(R.id.dateTimeText);
                locationText = itemView.findViewById(R.id.locationText);
            }

            public void bind(Event event) {
                sportText.setText(event.getSport());
                dateTimeText.setText(String.format("%s at %s", event.getDate(), event.getTime()));
                locationText.setText(event.getLocation());

               itemView.setOnClickListener(v -> {
                    Fragment detailsFragment = new EventDetailsFragmentJoin();
                    Bundle bundle = new Bundle();
                    bundle.putString("sport", event.getSport());
                    bundle.putString("date", event.getDate());
                    bundle.putString("time", event.getTime());
                    bundle.putString("location", event.getLocation());
                    bundle.putInt("players", event.getPlayersNeeded());
                    bundle.putString("skillLevel", event.getSkillLevel());
                    bundle.putString("description", event.getDescription());
                    detailsFragment.setArguments(bundle);

                    ((MainActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, detailsFragment)
                            .addToBackStack(null)
                            .commit();
                });
            }
        }


}
