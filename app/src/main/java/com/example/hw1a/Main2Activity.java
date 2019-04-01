package com.example.hw1a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity {
    private static String selected_name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button okButton=findViewById(R.id.buttonNameOk);
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = getIntent();
                Bundle bd = intent.getExtras();
                int song_id=(int) bd.get("current_sound");
                Intent contactIntent = new Intent(getApplicationContext(),MainActivity.class);
                contactIntent.putExtra("soundID",song_id);
                contactIntent.putExtra("contactName",selected_name);
                startActivity(contactIntent);
            }
        });

        Button cancelButton = findViewById(R.id.buttonNameCancel);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent contactIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(contactIntent);
            }
        });
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        if(checked){
            RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
            switch (view.getId()){
                case R.id.rBJohn: selected_name = "John Doe"; break;
                case R.id.rBJane: selected_name = "Jane Doe"; break;
                case R.id.rBJan: selected_name = "Jan Kowalski"; break;
                case R.id.rBJakub: selected_name = "Jakub Anonimowy"; break;
                case R.id.rBKrystyna: selected_name = "Krystyna Kowalska"; break;
            }
        }
    }
}
