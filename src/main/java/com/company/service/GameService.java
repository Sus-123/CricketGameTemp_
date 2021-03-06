package com.company.service;
import com.company.util.InningUtil;
import constants.Constants;
import com.company.entity.*;
import com.company.repozitory.InningRepository;
import com.company.repozitory.MatchRepository;
import com.company.repozitory.TeamRepository;
import com.company.scoreboard.ScoreBoard;
import com.company.util.Util;

import java.sql.SQLException;

public class GameService {

    GameServiceHelper gameServiceHelper = new GameServiceHelper();
    Inning inning1;
    Inning inning2;

    /**
     * instantiateNewGame  will initialise a new match, between two team
     * @param team1
     * @param team2
     * @param numOfOver : total num of over to be played
     */

    public void initializeNewGame(String matchName, Team team1, Team team2, int numOfOver) throws SQLException, ClassNotFoundException {

        int team1Id = TeamRepository.insertTeam(team1);
        int team2Id = TeamRepository.insertTeam(team2);

        int inning1Id = InningRepository.insertInning(team1Id, team2Id, numOfOver);
        int inning2Id = InningRepository.insertInning(team2Id, team1Id, numOfOver);

        MatchRepository.insertMatch(inning1Id, inning2Id, matchName);


        if(Util.playToss() == Constants.ZERO) {

            Strike strike1 = new Strike();
            inning1 = new Inning(team1, team2, false, 0, numOfOver, strike1);
            gameServiceHelper.playInning(inning1, inning1Id);
            System.out.println(team1.getName() + " ended game with " + InningUtil.getScoreOfInning(inning1));

            System.out.println("--------Second Inning -------");


            Strike strike2 = new Strike();
            inning2 = new Inning(team2, team1, true, InningUtil.getScoreOfInning(inning1), numOfOver, strike2);
            gameServiceHelper.playInning(inning2, inning2Id);
            System.out.println(team2.getName() + " ended game with " + InningUtil.getScoreOfInning(inning2));

        }
        else {

            Strike strike1 = new Strike();
            inning1 = new Inning(team2, team1, false, 0, numOfOver, strike1);
            gameServiceHelper.playInning(inning1, inning1Id);
            System.out.println(team2.getName() + " ended game with " + InningUtil.getScoreOfInning(inning1));

            System.out.println("--------Second Inning -------");

            Strike strike2 = new Strike();
            inning2 = new Inning(team1, team2, true, InningUtil.getScoreOfInning(inning1), numOfOver, strike2);
            gameServiceHelper.playInning(inning2, inning2Id);
            System.out.println(team1.getName() + " ended game with " + InningUtil.getScoreOfInning(inning2));
        }

    }

    public void  showFinalScoreBoard () {

        ScoreBoard.showMatchResult(inning1, inning2);

    }


}
