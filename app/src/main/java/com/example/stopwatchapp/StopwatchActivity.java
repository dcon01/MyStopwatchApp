package com.example.stopwatchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    private Stopwatch stopwatch;
    private Handler handler;
    private boolean isRunning;
    private TextView display;
    private Button toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.output);
        toggle = findViewById(R.id.toggle);

        isRunning = false;
        if (savedInstanceState == null) {
            stopwatch = new Stopwatch();
        } else {
            stopwatch = new Stopwatch(savedInstanceState.getString("value"));
            boolean running = savedInstanceState.getBoolean("running");
            if (running) {
                enableStopwatch();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("running", isRunning);
        outState.putString("value", stopwatch.toString());
    }


    private void enableStopwatch(){
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isRunning){
                    stopwatch.tick();
                    display.setText(stopwatch.toString());
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
    private void disableStopwatch(){
        isRunning = false;
    }

    public void toggleClicked(View view) {
        if (!isRunning) {
            enableStopwatch();
            toggle.setText(isRunning ? "stop" : "start");
        }else {
            disableStopwatch();
            toggle.setText(isRunning ? "stop" : "start");
        }
    }
    public void settingsClicked(View view) {
        Intent intent = new Intent();
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == Result_OK) {
                if (data != null) {
                    speed = data.getIntExtra("speed", 1000);
                }
            }
        }
    }
}