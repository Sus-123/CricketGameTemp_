package com.company.entity;

public class TeamStats {

    private  int id;
    private String name;
    private int runs;
    private int wickets;
    private float oversPlayed;


    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public TeamStats(int id, String name, int runs, int wickets, float oversPlayed ) {
        this.id = id;
        this.name = name;
        this.runs = runs;
        this.wickets = wickets;
        this.oversPlayed = oversPlayed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getOversPlayed() {
        return oversPlayed;
    }

    public void setOversPlayed(float oversPlayed) {
        this.oversPlayed = oversPlayed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public void setId(int id) {
        this.id = id;
    }

}
