package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        // Retrieve item details from Intent
        int itemId = getIntent().getIntExtra("itemId", -1);
        String itemName = getIntent().getStringExtra("itemName");
        String itemPhone = getIntent().getStringExtra("itemPhone");
        String itemDescription = getIntent().getStringExtra("itemDescription");
        String itemDate = getIntent().getStringExtra("itemDate");
        String itemLocation = getIntent().getStringExtra("itemLocation");

        // Display item details in TextViews
        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        itemNameTextView.setText(itemName);

        TextView itemPhoneTextView = findViewById(R.id.itemPhoneTextView);
        itemPhoneTextView.setText(itemPhone);

        TextView itemDescriptionTextView = findViewById(R.id.itemDescriptionTextView);
        itemDescriptionTextView.setText(itemDescription);

        TextView itemDateTextView = findViewById(R.id.itemDateTextView);
        itemDateTextView.setText(itemDate);

        TextView itemLocationTextView = findViewById(R.id.itemLocationTextView);
        itemLocationTextView.setText(itemLocation);

        // Handle Remove button click
        Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(v -> removeItem(itemId));
    }

    private void removeItem(int itemId) {
        LostAndFoundDbHelper dbHelper = new LostAndFoundDbHelper(this);
        dbHelper.deleteItem(itemId);

        // Display success message
        Toast.makeText(this, "Item removed successfully", Toast.LENGTH_SHORT).show();

        // Notify ShowItemsActivity to refresh the list
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish(); // Finish activity and return to previous screen
    }


}
