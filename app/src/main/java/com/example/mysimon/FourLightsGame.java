package com.example.mysimon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class FourLightsGame extends AppCompatActivity {


    private int gameState;  /** 0-init 1-Watch and Play 2-End **/
    // private Integer currentLevel;   /** Used to communicate how many blinks or button presses to do/expect **/
    private TextView stateDisplay;  /** This is the text view that tells the user what is happening **/
    private ImageView B1, B2, B3, B4;   /** quick names for the buttons **/

    private ImageView[] imageViews; /** This will be all the buttons in a passable array **/
    private int[] correctSequence;  /** This will be the sequence that the game displays and expects from the player
                                        The first position [0] will be the level number **/
    private String playerEntry;     /** This will record each button played by the player for each round **/

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
        gameState = 0;
        stateDisplay.setText("Tap to start");

        newRound();
    }


    private void newRound () {
        /** Call this to reset for next round **/


        /** Set the starting round number to one **/
            correctSequence[0] = 1;

        /** Set the correct play sequence for new round **/

        /*for (int i = 1; i <= randomishNumber(10,20); i++) {
            int throwAway = randomishNumber(1,4);
        }*/

        for (int i = 1; i <= 10; i++) {
            correctSequence[i] = randomishNumber(1,4);
        }

            
        /* Manual for testing
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
         */

        iconBlinker = new ImageBlinker(((10 * 1000) + 1000),500, correctSequence,imageViews,stateDisplay);

        playerEntry = ""; /** initialize the player entry string **/
    }



    public void stateTextClick (View view) {

        switch (gameState) {
            case 2:
                /** Play Again **/
                newRound();
            case 0:
                stateDisplay.setText("Watch");
                iconBlinker.begin(correctSequence[0]);  /** sending a new round number through correctSequence[0] **/
                gameState = 1;  /** Set the game to state 1 now that the round has started **/
                break;
            case 1:
                /** For testing only, comment each out before deploying **/
                //Toast.makeText(getApplicationContext(), Arrays.toString(correctSequence), Toast.LENGTH_SHORT).show();   // Print the winning string on screen (test)
                //Toast.makeText(getApplicationContext(), String.valueOf(randomishNumber(1,4)), Toast.LENGTH_SHORT).show();   // Print a randomish number to screen (test)
                break;

            default:

        }


    }


    public void button1Click (View view) {
        if ((gameState == 1) && iconBlinker.isPlaybackDone()) {
            playerEntry += "1";
            checkPlayerEntry();
        }
    }
    public void button2Click (View view) {
        if ((gameState == 1) && iconBlinker.isPlaybackDone()) {
            playerEntry += "2";
            checkPlayerEntry();
        }
    }
    public void button3Click (View view) {
        if ((gameState == 1) && iconBlinker.isPlaybackDone()) {
            playerEntry += "3";
            checkPlayerEntry();
        }
    }
    public void button4Click (View view) {
        if ((gameState == 1) && iconBlinker.isPlaybackDone()) {
            playerEntry += "4";
            checkPlayerEntry();
        }
    }

    private void checkPlayerEntry () {
        /** After each play, the moves will be checked.
         * If the user makes a mistake, the game ends.
         * If the user completes the entry, the next sequence will be started.
         * If all ten rounds are done correctly, they win. **/

        Toast.makeText(getApplicationContext(), String.valueOf(playerEntry.charAt(correctSequence[0]-1)),Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "Full string " + playerEntry,Toast.LENGTH_SHORT).show();

        if (correctSequence[0] == 10) {
            /** Winner **/
            gameState = 2;  /** End of Game **/
            stateDisplay.setText("Play Again?");

        } else {
            if (playerEntry.length() == correctSequence[0]) {
                /** Round successfully finished **/
                correctSequence[0] += 1;    /** Next level **/

                stateDisplay.setText("Watch");
                iconBlinker.begin(correctSequence[0]);  /** sending a new round number through correctSequence[0] **/

            } else {
                /** Round not done yet **/

            }
        }



    }







    private int randomishNumber (int min, int max) {
        /** Takes two arguments for the low and high to give a some what random number inclusive
         * Got help from stack overflow on this one
         * https://stackoverflow.com/questions/21049747/how-can-i-generate-a-random-number-in-a-certain-range/21049922
         */
        final int randishNumber = new Random().nextInt((max - min) + 1) + min;



        return randishNumber;
    }



}