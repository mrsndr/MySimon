package com.example.mysimon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FourLightsGame extends AppCompatActivity {


    private int gameState;  /** 0-init 1-Watch 2-Play 3-End **/
    // private Integer currentLevel;   /** Used to communicate how many blinks or button presses to do/expect **/
    private TextView stateDisplay;  /** This is the text view that tells the user what is happening **/
    private ImageView B1, B2, B3, B4;   /** quick names for the buttons **/

    private ImageView[] imageViews; /** This will be all the buttons in a passable array **/
    private int[] correctSequence;  /** This will be the sequence that the game displays and expects from the player
                                        The first position [0] will be the level number **/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_lights_game);

        /** Initialize the array for later use **/
        correctSequence = new int[11];  /** Positions 1-10 for this game, position 0 is is the current level **/

        imageViews = new ImageView[5];  /** Position 1-4 for my lights to blink and position 0 is not used **/
        imageViews[1] = findViewById(R.id.gamebutton1);
        imageViews[2] = findViewById(R.id.gamebutton2);
        imageViews[3] = findViewById(R.id.gamebutton3);
        imageViews[4] = findViewById(R.id.gamebutton4);

        /** Get the display textview for game mode instructions **/
         stateDisplay = findViewById(R.id.statetextView);

        /** Set the mode to initialization **/
        setModeToReady();



    }


    private void setModeToReady () {
        /** Call this to reset for next round **/
        gameState = 0;
        stateDisplay.setText("Tap to start");

        /** Set the correct sequence **/
        correctSequence[0] = 3;
        correctSequence[1] = 1;
        correctSequence[2] = 2;
        correctSequence[3] = 3;
        correctSequence[4] = 1;

    }

    //Ready to play



    public void stateTextClick (View view) {

        switch (gameState) {
            case 0:
                stateDisplay.setText("Watch");
                //android.os.SystemClock.sleep(2000);    /** Wait for two seconds before running **/
                //myDelayTimer shortWait = new myDelayTimer(2000,1000);
                    //shortWait.start();
                    //SystemClock.sleep(2000);
                ImageBlinker testBlinker = new ImageBlinker(((correctSequence[0] * 2000) + 2000),1000, correctSequence,imageViews);
                    testBlinker.begin();
                //gameState = 1;
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:
                /** Play Again **/
                setModeToReady();

                break;
            default:

        }


    }




}