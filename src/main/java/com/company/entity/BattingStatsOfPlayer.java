package com.company.entity;

public class BattingStatsOfPlayer {


    private int runsScored;
    int centuries;
    private int sixes;
    private int fours;
    private int ballsPlayed;



    public BattingStatsOfPlayer(int runsScored, int centuries, int sixes, int fours, int ballsPlayed) {
        this.runsScored = runsScored;
        this.centuries = centuries;
        this.sixes = sixes;
        this.fours = fours;
        this.ballsPlayed= ballsPlayed;
    }

}
