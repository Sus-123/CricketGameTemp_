package com.company.entity;

public class PlayerStatsInSingleMatch {

    private String name;
    private BattingStatsOfPlayer battingStatsOfPlayer;
    private BowlingStatsOfPlayer bowlingStatsOfPlayer;


    public PlayerStatsInSingleMatch(String playerName, BattingStatsOfPlayer battingStatsOfPlayer, BowlingStatsOfPlayer bowlingStatsOfPlayer) {
        this.name = playerName;
        this.battingStatsOfPlayer = battingStatsOfPlayer;
        this.bowlingStatsOfPlayer = bowlingStatsOfPlayer;
    }
}
