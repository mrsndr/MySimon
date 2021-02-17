package com.example.mysimon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FourLightsGame extends AppCompatActivity {

    private ImageView B1, B2, B3, B4;
    private Integer gameState;  /** 0-init 1-Watch 2-Play 3-End **/
    private TextView stateDisplay;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_lights_game);

        /** Get the display textview for game mode instructions **/
         stateDisplay = findViewById(R.id.statetextView);

        /** Set the mode to initialization **/
        setModeToInitialized();


    }


    private void setModeToInitialized () {
        /** Call this to reset for next round **/
        gameState = 0;
        stateDisplay.setText("Ready?");



    }





}