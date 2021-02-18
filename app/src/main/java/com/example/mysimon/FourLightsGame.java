package com.example.mysimon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FourLightsGame extends AppCompatActivity {


    private int gameState;  /** 0-init 1-Watch 2-Play 3-End **/
    // private Integer currentLevel;   /** Used to communicate how many blinks or button presses to do/expect **/
    private TextView stateDisplay;  /** This is the text view that tells the user what is happening **/
    private ImageView B1, B2, B3, B4;   /** quick names for the buttons **/

    private int[] correctSequence;  /** This will be the sequence that the game displays and expects from the player
                                        The first position [0] will be the level number **/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_lights_game);

        /** Initialize the array for later use **/
        correctSequence = new int[11];  /** Positions 1-10 for this game, position 0 is is the current level **/

        /** Get the display textview for game mode instructions **/
         stateDisplay = findViewById(R.id.statetextView);

        /** Set the mode to initialization **/
        setModeToReady();


    }


    private void setModeToReady () {
        /** Call this to reset for next round **/
        gameState = 0;
        stateDisplay.setText("Ready?");



    }





}