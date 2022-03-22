package com.example.stopwatchapp;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Locale;

public class Stopwatch {
    private int hours, minutes, seconds;

    Stopwatch(){
        hours = minutes = seconds = 0;
    }
    Stopwatch(String value){
        System.out.println("value: " + value);
        String[] parts = value.split(":");
        System.out.println(Arrays.toString(parts));
        hours = Integer.parseInt(parts[0]);
        minutes = Integer.parseInt(parts[1]);
        seconds = Integer.parseInt(parts[2]);
    }

    void tick() {
        ++seconds;
        if(seconds > 59){
            seconds = 0;
            ++minutes;
            if (minutes > 59) {
                minutes = 0;
                ++hours;
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
    }
}
