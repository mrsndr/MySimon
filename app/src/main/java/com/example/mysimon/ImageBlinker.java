package com.example.mysimon;


import android.os.CountDownTimer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageBlinker extends CountDownTimer {
    private ImageView[] imagesToBlink; /** Position 1-4 are the images and position 0 is not used **/
    private int[] correctSequence;  /** Positions 1-10 for this game, position 0 is is the current level **/
    private int currentPosition;    /** The active image to be blinking **/
    private boolean blinkTiming;    /** 0-Turn on 1-Turn off and next **/


    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     **/
    public ImageBlinker(long millisInFuture, long countDownInterval, int[] sequenceData, ImageView[] imagesArray) {
        //super(((millisInFuture * 2)*sequenceData[0]), countDownInterval);
        super((millisInFuture * 2), countDownInterval);
        correctSequence = sequenceData;
        imagesToBlink = imagesArray;
        blinkTiming = false;    /** Set first blinking position **/
        currentPosition = 1;    /** Set first position in sequence **/
    }

    @Override
    public void onTick(long millisUntilFinished) {

        //ImageView imageName = findViewById(R.id.gamebutton2);



        if (blinkTiming) {
            /** turn off and set next **/
            imagesToBlink[correctSequence[currentPosition]].setImageResource(R.drawable.ic_plus_button);
            currentPosition++;

        } else {
            /** turn on **/
            imagesToBlink[correctSequence[currentPosition]].setImageResource(R.drawable.ic_circle);
        }

        blinkTiming = !blinkTiming; /** Flip boolean **/


    }

    @Override
    public void onFinish() {
        //myImage.setImageResource(R.drawable.ic_plus_button);
    }
}
