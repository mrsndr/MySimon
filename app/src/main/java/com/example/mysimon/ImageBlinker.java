package com.example.mysimon;


import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageBlinker extends CountDownTimer {
    private ImageView[] imagesToBlink; /** Position 1-4 are the images and position 0 is not used **/
    private TextView stateDisplay;
    private int[] correctSequence;  /** Positions 1-10 for this game, position 0 should be ignored as it may be out of date **/
    private int roundNumber;        /** Current round number to be passed in through begin() **/
    private int currentPosition;    /** The active image to be blinking **/
    private boolean blinkTiming;    /** 0-Turn on 1-Turn off and next **/
    private boolean playbackDone;       /** Can be checked to see if the blinking is done **/


    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     **/
    public ImageBlinker(long millisInFuture, long countDownInterval, int[] sequenceData, ImageView[] imagesArray, TextView textDisplay) {
        //super(((millisInFuture * 2)*sequenceData[0]), countDownInterval);
        super(millisInFuture, countDownInterval);
        stateDisplay = textDisplay;
        correctSequence = sequenceData;
        imagesToBlink = imagesArray;
        blinkTiming = false;    /** Set first blinking position **/
        currentPosition = 0;    /** Set first position in sequence **/
    }

    public void begin(int rNumber) {
        //android.os.SystemClock.sleep(5000);    /** Wait for two seconds before running **/

        playbackDone = false;
        roundNumber = rNumber;
        start();
    }


    @Override
    public void onTick(long millisUntilFinished) {

        //ImageView imageName = findViewById(R.id.gamebutton2);


        if (currentPosition == 0) {
            currentPosition++;
        } else {
            if (currentPosition <= roundNumber) {

                if (blinkTiming) {
                    /** turn off and set next **/
                    imagesToBlink[correctSequence[currentPosition]].setImageResource(R.drawable.ic_plus_button);
                    currentPosition++;

                } else {
                    /** turn on **/

                    imagesToBlink[correctSequence[currentPosition]].setImageResource(R.drawable.ic_circle);
                }

                blinkTiming = !blinkTiming; /** Flip boolean **/
            } else {
                onFinish();
                super.cancel();
            }
        }

    }


    @Override
    public void onFinish() {
        //myImage.setImageResource(R.drawable.ic_plus_button);


        playbackDone = true;
        currentPosition = 0;
        stateDisplay.setText(R.string.flg_playerTurn);
    }


    public boolean isPlaybackDone () {
        return playbackDone;

    }


}
