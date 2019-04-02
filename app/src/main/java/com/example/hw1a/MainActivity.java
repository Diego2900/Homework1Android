package com.example.hw1a;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static String current_name = "John Doe";
    private int current_sound = 0;
    private MediaPlayer buttonPlayer;
    private boolean pause = true;
    public static final String SOUND_ID = "soundID";
    public static final String CONTACT_ID = "contactID";
    public static final int SOUND_REQUEST = 1;
    public static final int CONTACT_REQUEST = 1;
    public static final String INTENT_ID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buttonPlayer=new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        buttonPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(!pause){
                        buttonPlayer.stop();
                        pause=true;
                    } else{
                        Resume();
                        pause=false;
                    };
            }
        });

        Button contactButton = findViewById(R.id.contact);
        contactButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent contactIntent = new Intent(getApplicationContext(),Main2Activity.class);
                buttonPlayer.stop();
                pause=true;
                contactIntent.putExtra("current_sound",current_sound);
                startActivityForResult(contactIntent,CONTACT_REQUEST);
            }
        });

        Button soundButton = findViewById(R.id.sound);
        soundButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent soundIntent = new Intent(getApplicationContext(),Main3Activity.class);
                buttonPlayer.stop();
                pause=true;
                soundIntent.putExtra("current_sound",current_sound);
                startActivityForResult(soundIntent,SOUND_REQUEST);
            }
        });


        TextView name = findViewById(R.id.name);
        name.setText(current_name);


        final ImageView avatar = findViewById(R.id.avatar);
        Random rand = new Random();
        int value = rand.nextInt(16) + 1;
        switch (value){
            case 1: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_1));break;
            case 2: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_2));break;
            case 3: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_3));break;
            case 4: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_4));break;
            case 5: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_5));break;
            case 6: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_6));break;
            case 7: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_7));break;
            case 8: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_8));break;
            case 9: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_9));break;
            case 10: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_10));break;
            case 11: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_11));break;
            case 12: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_12));break;
            case 13: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_13));break;
            case 14: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_14));break;
            case 15: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_15));break;
            case 16: avatar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.avatar_16));break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void Resume(){
            switch (current_sound){
                case 0: buttonPlayer = MediaPlayer.create(this,R.raw.ringd); break;
                case 1: buttonPlayer = MediaPlayer.create(this,R.raw.ring01); break;
                case 2: buttonPlayer = MediaPlayer.create(this,R.raw.ring02); break;
                case 3: buttonPlayer = MediaPlayer.create(this,R.raw.ring03); break;
                case 4: buttonPlayer = MediaPlayer.create(this,R.raw.ring04); break;
            }
        buttonPlayer.start();
        buttonPlayer.setLooping(true);
        pause=false;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        buttonPlayer.release();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            String id = data.getStringExtra(INTENT_ID);
            if(requestCode==SOUND_REQUEST && id.equals("song")){
                current_sound=data.getIntExtra(SOUND_ID,0);
            }
            if(requestCode==CONTACT_REQUEST && id.equals("name")){
                current_name=data.getStringExtra(CONTACT_ID);
                TextView name = findViewById(R.id.name);
                name.setText(current_name);
            }
        }
    }

}
