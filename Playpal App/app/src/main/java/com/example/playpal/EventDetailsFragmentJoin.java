package com.example.playpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EventDetailsFragmentJoin extends Fragment {
    private TextView sportText, dateText, timeText, locationText, playersText, skillLevelText, descriptionText;
    private Button joinButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_details_tojoin, container, false);

        sportText = view.findViewById(R.id.sportTextJoin);
        dateText = view.findViewById(R.id.dateTextJoin);
        timeText = view.findViewById(R.id.timeTextJoin);
        locationText = view.findViewById(R.id.locationTextJoin);
        playersText = view.findViewById(R.id.playersTextJoin);
        skillLevelText = view.findViewById(R.id.skillLevelTextJoin);
        descriptionText = view.findViewById(R.id.descriptionTextJoin);
        joinButton = view.findViewById(R.id.ButtonJoin);


        if (getArguments() != null) {
            String sport = getArguments().getString("sport");
            String date = getArguments().getString("date");
            String time = getArguments().getString("time");
            String location = getArguments().getString("location");
            String description = getArguments().getString("description");
            int players = getArguments().getInt("players");
            String skillLevel = getArguments().getString("skillLevel");

            sportText.setText(sport);
            dateText.setText("Date: " + date);
            timeText.setText("Time: " + time);
            locationText.setText("Location: " + location);
            playersText.setText("Number of Players: " + players);
            skillLevelText.setText("Skill Level: " + skillLevel);
            descriptionText.setText("Description: " + description);


        }
        /*joinButton.setOnClickListener(v -> {
            Fragment chatFragment = new ChatFragment();
            Bundle chatArgs = new Bundle();
            chatArgs.putString("eventName", getArguments().getString("sport")); // Pass the event name
            chatFragment.setArguments(chatArgs);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, chatFragment)
                    .addToBackStack(null)
                    .commit();
        });*/

        return view;
    }
}
