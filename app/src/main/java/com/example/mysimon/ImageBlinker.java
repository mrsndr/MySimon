package com.example.mysimon;

import android.os.CountDownTimer;

public class ImageBlinker extends CountDownTimer {
    private int[] correctSequence;
    private boolean blinkTiming;    /** 0-Turn on 1-Turn off and next **/
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public ImageBlinker(long millisInFuture, long countDownInterval, int[] sequenceData) {
        super(((millisInFuture * 2)*sequenceData[0]), countDownInterval);
        blinkTiming = false;    /** first position **/
        correctSequence = sequenceData;
    }

    @Override
    public void onTick(long millisUntilFinished) {



        if (blinkTiming) {
            /** turn off and set next **/

        } else {
            /** turn on **/

        }

        blinkTiming = !blinkTiming; /** Flip boolean **/


    }

    @Override
    public void onFinish() {
        //myImage.setImageResource(R.drawable.ic_circle);
    }
}
