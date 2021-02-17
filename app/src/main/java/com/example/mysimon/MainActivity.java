package com.example.mysimon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** When the user presses the startbutton on the main screen this will happen **/
    public void startbuttonPressed (View view) {
        Intent simplesimonStart = new Intent ( this, FourLightsGame.class);
        startActivity(simplesimonStart);

    }





}