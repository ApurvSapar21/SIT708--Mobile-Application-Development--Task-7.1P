package com.example.lostandfoundapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {

    private RadioGroup postTypeRadioGroup;
    private EditText nameEditText, phoneEditText, descriptionEditText, dateEditText, locationEditText;
    private LostAndFoundDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        dbHelper = new LostAndFoundDbHelper(this);

        postTypeRadioGroup = findViewById(R.id.postTypeRadioGroup);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dateEditText = findViewById(R.id.dateEditText);
        locationEditText = findViewById(R.id.locationEditText);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> saveAdvert());
    }

    private void saveAdvert() {
        int selectedId = postTypeRadioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        String postType = radioButton.getText().toString();
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.insertAdvert(postType, name, phone, description, date, location);
        Toast.makeText(this, "Advert saved successfully", Toast.LENGTH_SHORT).show();

        clearInputFields();
    }

    private void clearInputFields() {
        nameEditText.getText().clear();
        phoneEditText.getText().clear();
        descriptionEditText.getText().clear();
        dateEditText.getText().clear();
        locationEditText.getText().clear();
    }
}
