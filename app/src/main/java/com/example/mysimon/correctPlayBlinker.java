package com.example.mysimon;

import android.os.CountDownTimer;
import android.widget.ImageView;

public class correctPlayBlinker extends CountDownTimer {
    private ImageView myImage;
    boolean lightOn;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public correctPlayBlinker(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //if (lightOn) {
            myImage.setImageResource(R.drawable.ic_plus_button);
        //} else {
            //myImage.setImageResource(R.drawable.ic_circle);
        //}
    }
                //myImage.setImageResource(R.drawable.ic_wrong);

    @Override
    public void onFinish() {
        myImage.setImageResource(R.drawable.ic_plus_button);
    }

    public void begin (ImageView imageToBlink) {
    myImage = imageToBlink;
    lightOn = false;
    myImage.setImageResource(R.drawable.ic_circle);     /** set the color I want immediately then turn it off after the first tick **/
    start();
}

}
