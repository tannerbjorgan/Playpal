package com.example.playpal;

// AddEventFragment.java
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import android.widget.Toast;

public class AddEventFragment extends Fragment {
    private AutoCompleteTextView sportSelector;
    private TextInputEditText dateSelector;
    private TextInputEditText timeSelector;
    private TextInputEditText locationSelector;
    private TextInputEditText playersCount;
    private AutoCompleteTextView skillLevelSelector;
    private TextInputEditText description;
    private Button createEventButton;

    // Arrays for dropdowns
    private final String[] sports = {"Basketball", "Football", "Soccer", "Tennis", "Volleyball", "Baseball"};
    private final String[] skillLevels = {"Beginner", "Intermediate", "Advanced", "All Levels"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);

        initializeViews(view);
        setupDropdowns();
        setupDateTimePickers();
        setupCreateButton();

        return view;
    }

    private void initializeViews(View view) {
        sportSelector = view.findViewById(R.id.sportSelector);
        dateSelector = view.findViewById(R.id.dateSelector);
        timeSelector = view.findViewById(R.id.timeSelector);
        locationSelector = view.findViewById(R.id.locationSelector);
        playersCount = view.findViewById(R.id.playersCount);
        skillLevelSelector = view.findViewById(R.id.skillLevelSelector);
        description = view.findViewById(R.id.description);
        createEventButton = view.findViewById(R.id.createEventButton);
    }

    private void setupDropdowns() {
        // Set up sports dropdown
        ArrayAdapter<String> sportsAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                sports
        );
        sportSelector.setAdapter(sportsAdapter);

        // Set up skill levels dropdown
        ArrayAdapter<String> skillAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                skillLevels
        );
        skillLevelSelector.setAdapter(skillAdapter);
    }

    private void setupDateTimePickers() {
        // Date Picker
        dateSelector.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(),
                    (view, year, month, dayOfMonth) -> {
                        String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d",
                                month + 1, dayOfMonth, year);
                        dateSelector.setText(selectedDate);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        // Time Picker
        timeSelector.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    requireContext(),
                    (view, hourOfDay, minute) -> {
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d",
                                hourOfDay, minute);
                        timeSelector.setText(selectedTime);
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false
            );
            timePickerDialog.show();
        });
    }

    private void setupCreateButton() {
        if (createEventButton != null) {
            createEventButton.setOnClickListener(v -> {
                if (validateInputs()) {
                    createEvent();
                }
            });
        }
    }

    private boolean validateInputs() {
        if (sportSelector.getText().toString().isEmpty()) {
            showError("Please select a sport");
            return false;
        }
        if (Objects.requireNonNull(dateSelector.getText()).toString().isEmpty()) {
            showError("Please select a date");
            return false;
        }
        if (Objects.requireNonNull(timeSelector.getText()).toString().isEmpty()) {
            showError("Please select a time");
            return false;
        }
        if (Objects.requireNonNull(locationSelector.getText()).toString().isEmpty()) {
            showError("Please enter a location");
            return false;
        }
        if (Objects.requireNonNull(playersCount.getText()).toString().isEmpty()) {
            showError("Please enter number of players needed");
            return false;
        }
        if (skillLevelSelector.getText().toString().isEmpty()) {
            showError("Please select skill level");
            return false;
        }
        return true;
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void createEvent() {
        try {
            Event newEvent = new Event(
                    sportSelector.getText().toString(),
                    Objects.requireNonNull(dateSelector.getText()).toString(),
                    Objects.requireNonNull(timeSelector.getText()).toString(),
                    Objects.requireNonNull(locationSelector.getText()).toString(),
                    Integer.parseInt(Objects.requireNonNull(playersCount.getText()).toString()),
                    skillLevelSelector.getText().toString(),
                    Objects.requireNonNull(description.getText()).toString()
            );

            // Pass event to MainActivity
            ((MainActivity) requireActivity()).addEventToDashboard(newEvent);

            // Show success message
            Toast.makeText(requireContext(), "Event created successfully!", Toast.LENGTH_SHORT).show();

            // Clear inputs and return to dashboard
            clearInputs();
            requireActivity().getSupportFragmentManager().popBackStack();
        } catch (NumberFormatException e) {
            showError("Please enter a valid number for players needed");
        }
    }

    private void clearInputs() {
        sportSelector.setText("");
        dateSelector.setText("");
        timeSelector.setText("");
        locationSelector.setText("");
        playersCount.setText("");
        skillLevelSelector.setText("");
        description.setText("");
    }
}