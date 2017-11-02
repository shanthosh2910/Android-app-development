package com.example.hp.texttospeech;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.speech.tts.TextToSpeech;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import java.util.logging.Logger;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends Activity  implements TextToSpeech.OnInitListener, OnClickListener {
    EditText input;
    Button button_clear, button_speak;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input=(EditText) findViewById(R.id.input);
        button_clear = (Button) findViewById(R.id.button_clear);
        button_speak = (Button) findViewById(R.id.button_speak);
        button_clear.setOnClickListener(this);
        button_speak.setOnClickListener(this);

        tts = new TextToSpeech(this, this);
    }


    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_clear:
                input.setText("");
                break;
            case R.id.button_speak:
                String text = input.getText().toString();
                if(text.isEmpty()){
                    Toast.makeText(MainActivity.this,"Text is empty", LENGTH_SHORT).show();
                }else {
                    tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);

                }
                break;

        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            Locale bahasa = tts.getLanguage();
            int result = tts.setLanguage(bahasa);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "this language is not supported");
            } else {
                //do nothing;
            }
        }else {
            Log.e("TTS","initialisation failed");
        }
    }
 }
