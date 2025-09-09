package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cityList = findViewById(R.id.city_list);

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