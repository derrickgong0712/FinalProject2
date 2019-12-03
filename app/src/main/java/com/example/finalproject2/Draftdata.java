package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Draftdata extends AppCompatActivity {
    private String[] legends = new String[] {"AXE", "ANTI-MAGE", "VOID", "TINKER", "WINDRANGER"};

    private String selection;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draftdata);
        Spinner legend = findViewById(R.id.spinner);
        EditText kill = findViewById(R.id.kill);
        EditText dead = findViewById(R.id.dead);
        EditText assist = findViewById(R.id.assist);
        TextView kda = findViewById(R.id.kda);
        Button upload = findViewById(R.id.upload);
        Button back = findViewById(R.id.back);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        ArrayAdapter x = new ArrayAdapter(this, android.R.layout.simple_spinner_item, legends);
        x.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        legend.setAdapter(x);
        legend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                selection = parent.getItemAtPosition(position).toString();
                // Called when the user selects a different item in the dropdown
                // The position parameter is the selected index
                // The other parameters can be ignored
            }
            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });
        String stringkill = kill.getText().toString();
        Double kills = Double.parseDouble(stringkill);
        String stringdead = dead.getText().toString();
        Double deads = Double.parseDouble(stringdead);
        String stringassist = assist.getText().toString();
        Double assists = Double.parseDouble(stringassist);
        Intent mainintent = new Intent(this, MainActivity.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainintent);
                finish();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double kdas = (kills + assists) / deads;
                kda.setText(kdas.toString());
            }
        });
    }
}
