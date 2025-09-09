package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // References
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityEntryInputField;
    Button cityEntryConfirmButton;
    Button addCityButton;
    Button deleteCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Populate references from layout
        this.cityList = findViewById(R.id.city_list);
        this.cityEntryInputField = findViewById(R.id.city_entry_input_field);
        this.cityEntryConfirmButton = findViewById(R.id.city_entry_confirm_button);
        this.addCityButton = findViewById(R.id.add_city_button);
        this.deleteCityButton = findViewById(R.id.delete_city_button);

        this.addCityButton.setOnClickListener(v -> {

        });

        this.deleteCityButton.setOnClickListener(v -> {
            String selectedCity = (String)this.cityList.getItemAtPosition(this.cityList.getCheckedItemPosition());
            if (selectedCity == null)
                return;

            this.cityAdapter.remove(selectedCity);
            this.cityAdapter.notifyDataSetChanged();
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
}