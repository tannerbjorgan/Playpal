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

public class EventDetailsFragment extends Fragment {
    private TextView sportText, dateText, timeText, locationText, playersText, skillLevelText, descriptionText;
    private Button chatButton;
    private ImageButton shareButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        sportText = view.findViewById(R.id.sportText);
        dateText = view.findViewById(R.id.dateText);
        timeText = view.findViewById(R.id.timeText);
        locationText = view.findViewById(R.id.locationText);
        playersText = view.findViewById(R.id.playersText);
        skillLevelText = view.findViewById(R.id.skillLevelText);
        descriptionText = view.findViewById(R.id.descriptionText);
        chatButton = view.findViewById(R.id.chatButton);
        shareButton = view.findViewById(R.id.shareButton);

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

            shareButton.setOnClickListener(v -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Check out this event:\n"
                        + "Sport: " + sport + "\n"
                        + "Date: " + date + "\n"
                        + "Time: " + time + "\n"
                        + "Location: " + location + "\n"
                        + "Skill Level: " + skillLevel + "\n"
                        + "Description: " + description;
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share Event Using"));
            });
        }
        chatButton.setOnClickListener(v -> {
            Fragment chatFragment = new ChatFragment();
            Bundle chatArgs = new Bundle();
            chatArgs.putString("eventName", getArguments().getString("sport")); // Pass the event name
            chatFragment.setArguments(chatArgs);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, chatFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
