package com.example.mysimon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FourLightsGame extends AppCompatActivity {


    private int gameState;  /** 0-init 1-Watch 2-Play 3-End **/
    // private Integer currentLevel;   /** Used to communicate how many blinks or button presses to do/expect **/
    private TextView stateDisplay;  /** This is the text view that tells the user what is happening **/
    private ImageView B1, B2, B3, B4;   /** quick names for the buttons **/

    private ImageView[] imageViews; /** This will be all the buttons in a passable array **/
    private int[] correctSequence;  /** This will be the sequence that the game displays and expects from the player
                                        The first position [0] will be the level number **/
    private String playerEntry = "";     /** This will record each button played by the player for each round **/

    private ImageBlinker iconBlinker;

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
        correctSequence[0] = 2;
        correctSequence[1] = 1;
        correctSequence[2] = 2;
        correctSequence[3] = 3;
        correctSequence[4] = 4;
        correctSequence[5] = 2;
        correctSequence[6] = 3;
        correctSequence[7] = 4;
        correctSequence[8] = 2;
        correctSequence[9] = 4;
        correctSequence[10] = 3;


        iconBlinker = new ImageBlinker(((10 * 2000) + 2000),1000, correctSequence,imageViews);
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
                //ImageBlinker testBlinker = new ImageBlinker(((correctSequence[0] * 2000) + 2000),1000, correctSequence,imageViews);
                correctSequence[0] = 6;

                    iconBlinker.begin(correctSequence[0]);  /** sending a new round number through correctSequence[0] **/

                gameState = 2;  /** Set the game to state 2 for the player's turn **/
                break;
            case 1:
                /* In a future version maybe this will record all button presses until the round is over */
                break;
            case 2:
                /* Maybe for a reset button in game? */
                break;
            case 3:
                /** Play Again **/
                setModeToReady();

                break;
            default:

        }


    }


    public void button1Click (View view) {

        if ((gameState == 2) && iconBlinker.isPlaybackDone()) {
            playerEntry += "1";
            checkPlayerEntry();
        }

    }

    private void checkPlayerEntry () {
        /** After each play, the moves will be checked.
         * If the user makes a mistake, the game ends.
         * If the user completes the entry, the next sequence will be started.
         * If all ten rounds are done correctly, they win. **/

        Toast.makeText(getApplicationContext(), playerEntry,Toast.LENGTH_SHORT).show();

    }



}