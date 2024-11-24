package com.example.playpal;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        preferences = getSharedPreferences("PlayPalPrefs", MODE_PRIVATE);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button signUpButton = findViewById(R.id.signUpButton);
        TextView loginTextView = findViewById(R.id.loginTextView);

        signUpButton.setOnClickListener(v -> {
            if (validateInputs()) {
                registerUser();
            }
        });

        loginTextView.setOnClickListener(v -> finish());
    }

    private boolean validateInputs() {
        // Validation code remains the same as before
        // (The validation code you provided earlier)
        return true;
    }

    private void registerUser() {
        // Save user data
        preferences.edit()
                .putBoolean("isLoggedIn", true)
                .putString("userName", nameEditText.getText().toString())
                .putString("userEmail", emailEditText.getText().toString())
                .apply();

        // Navigate to Dashboard
        Intent intent = new Intent(SignUpActivity.this, DashboardFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}