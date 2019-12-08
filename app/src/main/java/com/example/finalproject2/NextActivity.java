package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NextActivity extends AppCompatActivity {
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent previousintent = getIntent();
        email = previousintent.getStringExtra("email");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Button forinputdata = findViewById(R.id.data);
        Button forstart = findViewById(R.id.start);
        forinputdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendraftdata();
            }
        });
        forstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAnalysis();
            }
        });
    }
    public void opendraftdata() {
        Intent intent = new Intent(this, Draftdata.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
    public void openAnalysis() {
        Intent analysisintent = new Intent(this, Analysis.class);
        startActivity(analysisintent);
    }
}
