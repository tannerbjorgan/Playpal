package com.example.playpal;

// MainActivity.java
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private List<Event> eventsList = new ArrayList<>(); // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                selectedFragment = new DashboardFragment();
            } else if (itemId == R.id.nav_search) {
                selectedFragment = new SearchFragment();
            } else if (itemId == R.id.nav_add_event) {
                selectedFragment = new AddEventFragment();
            } else if (itemId == R.id.nav_map) {
                selectedFragment = new MapFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });

        // Set default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DashboardFragment())
                    .commit();
        }
    }

    public void addEventToDashboard(Event event) {
        // Add the event to the list
        eventsList.add(event);

        // Create new instance of DashboardFragment
        DashboardFragment dashboardFragment = new DashboardFragment();

        // Create bundle to pass events
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("events", new ArrayList<>(eventsList));
        dashboardFragment.setArguments(bundle);

        // Replace current fragment with dashboard
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, dashboardFragment)
                .commit();

        // Set bottom navigation to home
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    // Getter for events list
    public List<Event> getEventsList() {
        return eventsList;
    }
}


