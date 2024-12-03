package com.example.playpal;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.List;
import java.util.Map;

public class MapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);


        view.findViewById(R.id.pinCommonsField).setOnClickListener(this::onPinClick);
        view.findViewById(R.id.pinNonisSportsField).setOnClickListener(this::onPinClick);
        view.findViewById(R.id.CourtyardsBuilding).setOnClickListener(this::onPinClick);
        view.findViewById(R.id.pinGymnasium).setOnClickListener(this::onPinClick);

        return view;
    }

    public void onPinClick(View view) {
        String location = null;

        // Identify the clicked pin
        int viewId = view.getId();
        if (viewId == R.id.pinCommonsField) {
            location = "Commons Field";
        } else if (viewId == R.id.pinNonisSportsField) {
            location = "Nonis Sports Field";
        } else if (viewId == R.id.CourtyardsBuilding) {
            location = "Courtyards Building";
        } else if (viewId == R.id.pinGymnasium) {
            location = "Gymnasium";
        }

        if (location != null) {

            Map<String, List<Event>> eventsByLocation = ((MainActivity) requireActivity()).getEventsByLocation();
            List<Event> events = eventsByLocation.get(location);


            if (events != null && !events.isEmpty()) {
                showEventsDialog(location, events);
            } else {
                Toast.makeText(getContext(), "No events found at " + location, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showEventsDialog(String location, List<Event> events) {

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_event_list);


        TextView titleText = dialog.findViewById(R.id.dialogTitle);
        titleText.setText("Events at " + location);


        LinearLayout eventContainer = dialog.findViewById(R.id.eventListContainer);
        for (Event event : events) {
            View eventView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, eventContainer, false);
            TextView eventDetails = eventView.findViewById(R.id.sportText);
            eventDetails.setText(event.getSport() + " - " + event.getTime() + " (" + event.getPlayersNeeded() + " players needed)");
            eventContainer.addView(eventView);
        }

        dialog.findViewById(R.id.dialogCloseButton).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
