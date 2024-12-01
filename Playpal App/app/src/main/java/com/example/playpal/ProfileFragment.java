package com.example.playpal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private EditText fullNameInput, majorInput, bioInput;
    private Spinner yearDropdown;
    private LinearLayout sportsContainer;
    private Button addSportButton, saveButton;

    private static final String PREFS_NAME = "ProfilePrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fullNameInput = view.findViewById(R.id.fullNameInput);
        majorInput = view.findViewById(R.id.majorInput);
        bioInput = view.findViewById(R.id.bioInput);
        yearDropdown = view.findViewById(R.id.yearDropdown);
        sportsContainer = view.findViewById(R.id.sportsContainer);
        addSportButton = view.findViewById(R.id.addSportButton);
        saveButton = view.findViewById(R.id.saveButton);

        loadProfileData();

        addSportButton.setOnClickListener(v -> showAddSportDialog());

        saveButton.setOnClickListener(v -> saveProfileData());

        return view;
    }

    private void loadProfileData() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        fullNameInput.setText(prefs.getString("fullName", ""));
        majorInput.setText(prefs.getString("major", ""));
        bioInput.setText(prefs.getString("bio", ""));
        yearDropdown.setSelection(prefs.getInt("year", 0));

        String savedSports = prefs.getString("sports", "");
        if (!savedSports.isEmpty()) {
            String[] sportsArray = savedSports.split(";");
            for (String sportEntry : sportsArray) {
                String[] parts = sportEntry.split(":");
                if (parts.length == 2) {
                    addSportToContainer(parts[0], parts[1]);
                }
            }
        }
    }

    private void showAddSportDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Sport");

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_sport, null);
        builder.setView(dialogView);

        Spinner sportSpinner = dialogView.findViewById(R.id.sportSpinner);
        RadioGroup skillGroup = dialogView.findViewById(R.id.skillGroup);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String selectedSport = sportSpinner.getSelectedItem().toString();
            int selectedSkillId = skillGroup.getCheckedRadioButtonId();
            String skillLevel = selectedSkillId == R.id.skillBeginner ? "Beginner"
                    : selectedSkillId == R.id.skillIntermediate ? "Intermediate" : "Expert";

            addSportToContainer(selectedSport, skillLevel);
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void addSportToContainer(String sport, String skillLevel) {
        Button sportButton = new Button(getContext());
        sportButton.setText(sport + " " + skillLevel);
        sportButton.setAllCaps(false);
        sportButton.setTextSize(14);
        sportButton.setTextColor(getResources().getColor(android.R.color.black));
        sportButton.setBackgroundResource(R.drawable.sport_button_background);
        sportButton.setPadding(16, 8, 16, 8);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 0);
        sportButton.setLayoutParams(params);

        sportsContainer.addView(sportButton);
    }

    private void saveProfileData() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("fullName", fullNameInput.getText().toString());
        editor.putString("major", majorInput.getText().toString());
        editor.putString("bio", bioInput.getText().toString());
        editor.putInt("year", yearDropdown.getSelectedItemPosition());

        StringBuilder sportsData = new StringBuilder();
        for (int i = 0; i < sportsContainer.getChildCount(); i++) {
            Button sportButton = (Button) sportsContainer.getChildAt(i);
            String[] parts = sportButton.getText().toString().split(" ");
            if (parts.length > 1) {
                sportsData.append(parts[0]).append(":").append(parts[1]).append(";");
            }
        }
        editor.putString("sports", sportsData.toString());

        editor.apply();

        Toast.makeText(getContext(), "Profile Saved!", Toast.LENGTH_SHORT).show();
    }
}
