package com.example.hw1a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Main3Activity extends AppCompatActivity {
    private static int song_id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button okButton=findViewById(R.id.buttonSongOk);
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Spinner spinner = findViewById(R.id.spinner);
                String text = spinner.getSelectedItem().toString();
                switch (text){
                    case "Sound 1": song_id=0; break;
                    case "Sound 2": song_id=1; break;
                    case "Sound 3": song_id=2; break;
                    case "Sound 4": song_id=3; break;
                    case "Sound 5": song_id=4; break;
                }

                Intent data = new Intent();
                data.putExtra(MainActivity.SOUND_ID,song_id);
                data.putExtra(MainActivity.INTENT_ID,"song");
                setResult(RESULT_OK,data);
                finish();
            }
        });

        Button cancelButton=findViewById(R.id.buttonSongCancel);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
}

