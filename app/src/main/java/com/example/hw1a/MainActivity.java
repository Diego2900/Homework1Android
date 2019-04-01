package com.example.hw1a;

import android.app.ActionBar;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static String current_name = "";
    private int current_sound = 0;
    private MediaPlayer buttonPlayer;
//    static public Uri[] sounds;
    private int pause = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        sounds = new Uri[5];
//        sounds[0]=Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.mario);
//        sounds[1]=Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ring01);
//        sounds[2]=Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ring02);
//        sounds[3]=Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ring03);
//        sounds[4]=Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ring04);
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
//                buttonPlayer.reset();
                    if(pause==0) {
                        onPause();
                    } else {
                        onResume();
                    }
//                buttonPlayer.prepareAsync();
            }
        });



        Button contactButton = findViewById(R.id.contact);
        contactButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent contactIntent = new Intent(getApplicationContext(),Main2Activity.class);
                buttonPlayer.stop();
                contactIntent.putExtra("current_sound",current_sound);
                startActivity(contactIntent);
            }
        });

        Button soundButton = findViewById(R.id.sound);
        soundButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent soundIntent = new Intent(getApplicationContext(),Main3Activity.class);
                buttonPlayer.stop();
                soundIntent.putExtra("current_sound",current_sound);
                startActivity(soundIntent);
            }
        });

        TextView name = findViewById(R.id.name);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(intent.getStringExtra("contactName")==null) {
            name.setText(current_name);
        }
        else{
            String getName = (String) bd.get("contactName");
            name.setText(getName);
            current_name=getName;
        }

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   // @Override
    protected void onResume(){
        super.onResume();
        Intent soundIntent = getIntent();
        Bundle sd = soundIntent.getExtras();
        if(sd!=null) {
            current_sound = soundIntent.getIntExtra("soundID",current_sound);
            switch (current_sound){
                case 0: buttonPlayer = MediaPlayer.create(this,R.raw.ringd); break;
                case 1: buttonPlayer = MediaPlayer.create(this,R.raw.ring01); break;
                case 2: buttonPlayer = MediaPlayer.create(this,R.raw.ring02); break;
                case 3: buttonPlayer = MediaPlayer.create(this,R.raw.ring03); break;
                case 4: buttonPlayer = MediaPlayer.create(this,R.raw.ring04); break;
            }
        }
            else{
            switch (current_sound){
                case 0: buttonPlayer = MediaPlayer.create(this,R.raw.ringd); break;
                case 1: buttonPlayer = MediaPlayer.create(this,R.raw.ring01); break;
                case 2: buttonPlayer = MediaPlayer.create(this,R.raw.ring02); break;
                case 3: buttonPlayer = MediaPlayer.create(this,R.raw.ring03); break;
                case 4: buttonPlayer = MediaPlayer.create(this,R.raw.ring04); break;
            }
        }
        buttonPlayer.start();
        buttonPlayer.setLooping(true);
        pause=0;
    }
    @Override
    protected void onPause(){
        super.onPause();
        buttonPlayer.pause();
        pause=1;
    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//        buttonPlayer = MediaPlayer.create(this,R.raw.mario);
//        buttonPlayer.setOnPreparedListener(
//                new MediaPlayer.OnPreparedListener(){
//                    @Override
//                    public void onPrepared(MediaPlayer mp){
//                        mp.setLooping(true);
//                        mp.start();
//                    }
//                }
//        );
//        pause=0;
//    }

    @Override
    protected void onStop(){
        super.onStop();
        buttonPlayer.release();
    }

}
