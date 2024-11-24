package com.example.playpal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private ImageView profileImage;
    private TextView userNameText;
    private Button editProfileButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImage = view.findViewById(R.id.profileImage);
        userNameText = view.findViewById(R.id.userNameText);
        editProfileButton = view.findViewById(R.id.editProfileButton);

        return view;
    }
}
