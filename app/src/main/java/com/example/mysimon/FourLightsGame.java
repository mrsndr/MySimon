package com.example.mysimon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FourLightsGame extends AppCompatActivity {


    private int gameState;  /** 0-init 1-Watch and Play 2-End **/
    // private Integer currentLevel;   /** Used to communicate how many blinks or button presses to do/expect **/
    private TextView stateDisplay;  /** This is the text view that tells the user what is happening **/
    //private ImageView B1, B2, B3, B4;   /** quick names for the buttons **/

    private ImageView[] imageViews; /** This will be all the buttons in a passable array **/
    private int[] correctSequence;  /** This will be the sequence that the game displays and expects from the player
                                        The first position [0] will be the level number **/
    private int inRoundClickNumber; /** This will track the click the user is currently on, so click #4 of #6 in round 6 **/
    private String playerEntry;     /** This will record each button played by the player for each round **/

    private ImageBlinker iconBlinker;
    private correctPlayBlinker playCorrectMove;
    private incorrectPlayBlinker playWrongMove;

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

        correctSequence[0] = 1; /** Set the starting round number to one **/

        for (int i = 1; i <= 10; i++) {
            /** Set the correct play sequence for new round **/
            correctSequence[i] = randomishNumber(1,4);
        }

        /** create the blinker that will be used for the game **/
        iconBlinker = new ImageBlinker(((10 * 1000) + 1000),500, correctSequence,imageViews,stateDisplay);
        playCorrectMove = new correctPlayBlinker(75,75);
        playWrongMove = new incorrectPlayBlinker(2000,2000);

        playerEntry = "";   /** Initialize the player entry string **/
        inRoundClickNumber = 1; /** Initialize the in round click counter **/
    }



    public void stateTextClick (View view) {

        switch (gameState) {
            case 2:
                /** Play Again **/
                newRound();
            case 0:
                /** Game has been initialized but not started **/
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

        //helpMeString( String.valueOf(playerEntry.charAt(correctSequence[0]-1)) );    // Show the played button number (test)
        //helpMeString( "Full string " + playerEntry );  // Show all played buttons (test)

        /** Oof, got help from beginners book on this formula
         * https://beginnersbook.com/2019/04/java-char-to-int-conversion/
         * This gets the value of the last play from the string then compares that to the current correct play for the click in the round
         */
            int playerPlayed = (Integer.parseInt(String.valueOf(playerEntry.charAt(inRoundClickNumber - 1))));
            int playerShouldHavePlayed = correctSequence[inRoundClickNumber];

        if ( playerPlayed == playerShouldHavePlayed ) {
            /** Correct play **/
            playCorrectMove.begin(imageViews[playerPlayed]);
                if (inRoundClickNumber == correctSequence[0]) {
                    /** Round done **/
                    //helpMeString("length " + String.valueOf(playerEntry.length()));
                    if (correctSequence[0] == 10) {
                        /** Successfully completed 10 rounds, Winner! **/
                        gameState = 2;  /** End of Game **/
                        stateDisplay.setText("You Win! Play Again?");     //helpMeString("4");
                        // Some sort of win thing here maybe

                    } else {
                        /** Start the next round **/
                        correctSequence[0] += 1;    /** Add to correctSequence for next level number **/
                        inRoundClickNumber = 1;     /** Reset the round click number counter **/
                        playerEntry = "";           /** Reset the player entry tracker **/
                        stateDisplay.setText("Watch");   //helpMeString("3");
                        iconBlinker.begin(correctSequence[0]);  /** Sending a new round number through correctSequence[0] **/
                    }

                } else {
                    /** Ongoing round, keep going **/
                    inRoundClickNumber ++;  //helpMeString("2");
                }

        } else {
            /** Incorrect play **/
            gameState = 2;  /** End of Game **/
            stateDisplay.setText("You Lost Play Again?"); //helpMeString("1");
            playWrongMove.begin(imageViews[playerPlayed]);
            // Loser routine maybe

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

    private void helpMeString(String thisToast) {
        Toast.makeText(getApplicationContext(), thisToast ,Toast.LENGTH_SHORT).show();
    }

}