package com.example.lostandfoundapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LostAndFoundAdapter adapter;
    private List<LostAndFoundItem> itemList;

    private static final int REMOVE_ITEM_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        recyclerView = findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();
        adapter = new LostAndFoundAdapter(this, itemList);

        adapter.setOnItemClickListener(position -> {
            LostAndFoundItem selectedItem = itemList.get(position);

            Intent intent = new Intent(ShowItemsActivity.this, RemoveItemActivity.class);
            intent.putExtra("itemId", selectedItem.getId());
            intent.putExtra("itemName", selectedItem.getName());
            intent.putExtra("itemPhone", selectedItem.getPhone());
            intent.putExtra("itemDescription", selectedItem.getDescription());
            intent.putExtra("itemDate", selectedItem.getDate());
            intent.putExtra("itemLocation", selectedItem.getLocation());

            startActivityForResult(intent, REMOVE_ITEM_REQUEST_CODE);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Retrieve and display all Lost & Found items from SQLite database
        retrieveItemsFromDatabase();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REMOVE_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            // Refresh the list by retrieving items from the database again
            retrieveItemsFromDatabase();
        }
    }

    private void retrieveItemsFromDatabase() {
        itemList.clear();

        LostAndFoundDbHelper dbHelper = new LostAndFoundDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                LostAndFoundDbHelper.COLUMN_ID,
                LostAndFoundDbHelper.COLUMN_POST_TYPE,
                LostAndFoundDbHelper.COLUMN_NAME,
                LostAndFoundDbHelper.COLUMN_PHONE,
                LostAndFoundDbHelper.COLUMN_DESCRIPTION,
                LostAndFoundDbHelper.COLUMN_DATE,
                LostAndFoundDbHelper.COLUMN_LOCATION
        };

        Cursor cursor = db.query(
                LostAndFoundDbHelper.TABLE_ADVERTS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(LostAndFoundDbHelper.COLUMN_ID));
            String postType = cursor.getString(cursor.getColumnIndexOrThrow(LostAndFoundDbHelper.COLUMN_POST_TYPE));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(LostAndFoundDbHelper.COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(LostAndFoundDbHelper.COLUMN_PHONE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(LostAndFoundDbHelper.COLUMN_DESCRIPTION));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(LostAndFoundDbHelper.COLUMN_DATE));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(LostAndFoundDbHelper.COLUMN_LOCATION));

            LostAndFoundItem item = new LostAndFoundItem(id, postType, name, phone, description, date, location);
            itemList.add(item);
        }

        cursor.close();
        dbHelper.close();

        adapter.notifyDataSetChanged();
    }
}
