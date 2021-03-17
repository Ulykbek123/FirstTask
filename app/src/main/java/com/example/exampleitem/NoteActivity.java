package com.example.exampleitem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        TextView text = findViewById(R.id.textView1);
        Bundle extras = getIntent().getExtras();
        String username = "Username not set";
        if (extras != null){
            username = extras.getString("select_note");

        }

        text.setText(username);





    }
}