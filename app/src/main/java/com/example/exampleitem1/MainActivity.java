package com.example.exampleitem1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int EDIT_NOTE_REQUEST = 2;
    private ArrayList<ExampleItem> mExampleList;
    public static final int ADD_NOTE_REQUEST = 1;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView selectAllbtn;
    private TextView deleteAllbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAddNote = findViewById(R.id.button_add_note);
        selectAllbtn = findViewById(R.id.selectAllbtn);
        deleteAllbtn = findViewById(R.id.deleteAllbtn);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        selectAllbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllbtn.setVisibility(View.VISIBLE);
                selectAllbtn.setVisibility(View.GONE);
                mAdapter.setChekBoxVisible(true);
            }
        });
        deleteAllbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllbtn.setVisibility(View.GONE);
                selectAllbtn.setVisibility(View.VISIBLE);
                mAdapter.setChekBoxVisible(false);
                mAdapter.deleteAll();
            }
        });

        createExampleList();
        buildRecyclerView();

    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audio, "Line 3", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_sun, "Line 5", "Line 6"));
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
            public void onItemClick(int id,ExampleItem note) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                intent.putExtra(AddNoteActivity.EXTRA_ID, id);
                intent.putExtra(AddNoteActivity.EXTRA_TITLE, note.getText1());
                intent.putExtra(AddNoteActivity.EXTRA_DESCRIPTION, note.getText2());
                startActivityForResult(intent, ADD_NOTE_REQUEST);

                //intent.putExtra("Example Item", mExampleList.get(position));

            }
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    private void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String text1 = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String text2 = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int index = data.getIntExtra(AddNoteActivity.EXTRA_ID, -1);
            ExampleItem note = new ExampleItem(text1, text2);
            if (index == -1){

                mAdapter.insert(note);
                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
            }
            else {


                mAdapter.update(index, note);
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();

            }


        }
        else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}