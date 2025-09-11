package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // References
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    ConstraintLayout cityEntryLayout;
    EditText cityEntryInputField;
    Button cityEntryConfirmButton;
    Button addCityButton;
    Button deleteCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Account for on-screen keyboard and other system insets.
        // Referenced from: https://developer.android.com/develop/ui/views/layout/edge-to-edge#java
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        View root = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime());
            Insets sysInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // Add bottom padding equal to the on-screen keyboard height when it is visible
            v.setPadding(
                sysInsets.left,
                sysInsets.top,
                sysInsets.right,
                imeInsets.bottom > 0 ? imeInsets.bottom : sysInsets.bottom
            );

            return WindowInsetsCompat.CONSUMED;
        });

        // Populate references from layout
        this.cityList = findViewById(R.id.city_list);
        this.cityEntryLayout = findViewById(R.id.city_entry_layout);
        this.cityEntryInputField = findViewById(R.id.city_entry_input_field);
        this.cityEntryConfirmButton = findViewById(R.id.city_entry_confirm_button);
        this.addCityButton = findViewById(R.id.add_city_button);
        this.deleteCityButton = findViewById(R.id.delete_city_button);

        // Hide the city entry layout at the start
        this.cityEntryLayout.setVisibility(View.GONE);

        this.addCityButton.setOnClickListener(v -> {
            if (this.cityEntryLayout.getVisibility() == View.VISIBLE)
            {
                this.hideCityEntryLayout();
            }
            else
                this.showCityEntryLayout();
        });

        this.deleteCityButton.setOnClickListener(v -> {
            // Guard against empty list
            if (this.cityAdapter.isEmpty())
                return;

            // Gets the position in the city list of the selected city
            int cityPositionInList = this.cityList.getCheckedItemPosition();

            // Guard against getting an out-of-bounds position
            if (cityPositionInList > this.cityList.getCount() - 1)
                return;

            // Gets the currently selected city in the list
            String selectedCity = (String)this.cityList.getItemAtPosition(cityPositionInList);

            // Guard in case there is no selected city
            if (selectedCity == null)
                return;

            // Remove the selected city from the list
            this.cityAdapter.remove(selectedCity);
            this.cityAdapter.notifyDataSetChanged();

            // If selection is out of bounds, clamp it to bounds
            if (cityPositionInList > this.cityList.getCount() - 1)
            {
                if (cityPositionInList > 0) {
                    cityPositionInList -= 1;
                    this.cityList.setItemChecked(cityPositionInList, true);
                }
                return;
            }
        });

        this.cityEntryConfirmButton.setOnClickListener(v -> {
            String cityName = this.cityEntryInputField.getEditableText().toString();

            if (cityName.isBlank())
                return;

            this.cityAdapter.add(cityName);
            this.cityAdapter.notifyDataSetChanged();

            this.hideCityEntryLayout();
        });



        String[] cities = {
            "Edmonton",
            "Vancouver",
            "Moscow",
            "Sydney",
            "Berlin",
            "Vienna",
            "Tokyo",
            "Beijing",
            "Osaka",
            "New Delhi",
        };

        this.dataList = new ArrayList<String>();
        this.dataList.addAll(Arrays.asList(cities));

        this.cityAdapter = new ArrayAdapter<String>(this, R.layout.content, this.dataList);
        this.cityList.setAdapter(this.cityAdapter);
    }

    protected void showCityEntryLayout()
    {
        this.cityEntryInputField.setText("");
        this.cityEntryLayout.setVisibility(View.VISIBLE);

        // Focus the city input field and show the on-screen keyboard.
        // Reference: https://developer.android.com/develop/ui/views/touch-and-input/keyboard-input/visibility
        if (cityEntryInputField.requestFocus()) {
            InputMethodManager imm = getSystemService(InputMethodManager.class);
            imm.showSoftInput(cityEntryInputField, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    protected void hideCityEntryLayout()
    {
        this.cityEntryInputField.setText("");
        this.cityEntryLayout.setVisibility(View.GONE);
    }
}