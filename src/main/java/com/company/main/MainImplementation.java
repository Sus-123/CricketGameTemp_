package com.company.main;

import constants.Constants;
import com.company.entity.Team;
import com.company.scoreboard.PreviousMatchResult;
import com.company.service.GameService;
import com.company.util.Util;

import java.sql.SQLException;

public class MatchController {

    static Util util = new Util();

    public static void startNewMatch () throws SQLException, ClassNotFoundException {

        GameService gameService = new GameService();

        System.out.println("Enter Match Name : ");
        String matchName = util.getValidStringType();

        System.out.println("Enter Number Of overs to be played, it can be between 1 to 50");
        int numOfOver = util.getIntegerInput(Constants.loweBoundOfOver,Constants.upperBoundOfOver);

        System.out.println("Enter Team1 name: ");
        String team1Name = util.getValidStringType();
        Team team1 = new Team(team1Name);


        System.out.println("Enter Team2  name: ");
        String team2Name = util.getValidStringType();
        Team team2 = new Team(team2Name);

        gameService.initializeNewGame(matchName, team1,team2,numOfOver);
        gameService.showFinalScoreBoard();

    }


    public static void displayPreviouslyPlayedMatch() throws SQLException, ClassNotFoundException {

        System.out.println("Enter Match Name for which you need Results: ");

        String matchName = util.getValidStringType();

        PreviousMatchResult.displayPreviouslyPlayedMatch(matchName);

    }



}
