package com.example.mysimon;

public class GameState {

        private int roundNumber;

    public void setRound (int RoundNumber) {
        roundNumber = RoundNumber;
    }

    public int getRound () {
        return roundNumber;
    }


        private boolean inPlayback;

    public void setInPlayback (boolean isInPlayback) {
        inPlayback = isInPlayback;
    }

    public boolean getInPlayback () {
        return inPlayback;
    }
    public boolean getIsDonePlayback () {
        return !inPlayback;
    }


}
