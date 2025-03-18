package com.example.tts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edtGet;
    private SeekBar seekPitch;
    private SeekBar seekSpeed;
    private Button btnSay;

    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtGet = findViewById(R.id.edtGet);
        btnSay = findViewById(R.id.btnSay);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {

                    int result = tts.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(MainActivity.this, "Language is not supported", Toast.LENGTH_LONG).show();
                    } else {
                        btnSay.setEnabled(true);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Initialization has failed", Toast.LENGTH_LONG).show();
                }

            }
        });


        btnSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtGet.getText().toString();

                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }


        super.onDestroy();
    }
}