package com.example.exampleitem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button buttonInsert;
    private EditText editTextInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createExampleList();
        buildRecyclerView();
        setButtons();
        buttonInsert = findViewById(R.id.button_insert);

        editTextInsert = findViewById(R.id.edittext_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });
    }
    public void insertItem(int position) {
        mExampleList.add(position, new ExampleItem(R.drawable.ic_android, "New Item At Position" + position, "This is Line 2"));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
    public void changeItem(int position, String text) {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);

    }
    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 3", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 5", "Line 6"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 7", "Line 8"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 9", "Line 10"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 11", "Line 12"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 13", "Line 14"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 15", "Line 16"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 17", "Line 18"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 19", "Line 20"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 21", "Line 22"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 23", "Line 24"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 25", "Line 26"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 27", "Line 28"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 29", "Line 30"));
    }
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                intent.putExtra("selected_note",mExampleList.get(position).getText1());
                startActivity(intent);
                changeItem(position, "Clicked");
            }
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }
    public void setButtons() {
        buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = Integer.parseInt(buttonInsert.getText().toString());
                insertItem(position);
            }
        });

    }

}