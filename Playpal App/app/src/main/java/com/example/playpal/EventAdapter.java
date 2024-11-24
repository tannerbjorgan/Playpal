package com.example.playpal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> events;

    public EventAdapter(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView sportText;
        private TextView dateTimeText;
        private TextView locationText;
        private TextView playersText;
        private TextView skillLevelText;
        private TextView descriptionText;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            sportText = itemView.findViewById(R.id.sportText);
            dateTimeText = itemView.findViewById(R.id.dateTimeText);
            locationText = itemView.findViewById(R.id.locationText);
            playersText = itemView.findViewById(R.id.playersText);
            skillLevelText = itemView.findViewById(R.id.skillLevelText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
        }

        public void bind(Event event) {
            sportText.setText(event.getSport());
            dateTimeText.setText(String.format("%s at %s", event.getDate(), event.getTime()));
            locationText.setText(event.getLocation());
            playersText.setText(String.format("Players needed: %d", event.getPlayersNeeded()));
            skillLevelText.setText(event.getSkillLevel());
            descriptionText.setText(event.getDescription());
        }
    }
}
