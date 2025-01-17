package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView cityList;
    private EditText editTextCityName;
    private Button buttonAddCity, buttonDeleteCity;

    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;
    private String selectedCity; // To store the currently selected city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            cityList = findViewById(R.id.city_list);
            editTextCityName = findViewById(R.id.text_box);
            buttonAddCity = findViewById(R.id.button_add_city);
            buttonDeleteCity = findViewById(R.id.button_delete_city);

            // Initialize city list and adapter
            String[] cities = {"Edmonton", "Paris", "London", "Calgary"};
            dataList = new ArrayList<>(Arrays.asList(cities));
            cityAdapter = new ArrayAdapter<>(this,R.layout.content, dataList);
            cityList.setAdapter(cityAdapter);

            // Handle ListView Item Selection
            cityList.setOnItemClickListener((parent, view, position, id) -> {
                selectedCity = dataList.get(position);
            });

            // Handle Add City Button
            buttonAddCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newCity = editTextCityName.getText().toString();
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    editTextCityName.setText(""); // Clear input field
                }
            });

            // Handle Delete City Button
            buttonDeleteCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedCity != null && dataList.contains(selectedCity)) {
                        dataList.remove(selectedCity);
                        cityAdapter.notifyDataSetChanged();
                        selectedCity = null; // Reset selection
                    }
                }
            });
            return insets;
        });
    }
}
