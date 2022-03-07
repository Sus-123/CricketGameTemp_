package com.company.controllers;
import com.company.entity.*;
import com.company.repozitory.InningRepository;
import com.company.repozitory.MatchRepository;
import com.company.repozitory.PlayersRepository;
import com.company.util.PlayerUtil;
import constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PlayerController {


    @Autowired
    PlayersRepository playersRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    InningRepository inningRepository;


    @GetMapping("/players/{matchId}/{playerId}")
    public ResponseEntity<PlayerStatsInSingleMatch> getPlayerInfoByMatchId(@PathVariable("matchId") int matchId, @PathVariable("playerId") int playerId) throws SQLException, ClassNotFoundException {

        ArrayList<Integer> inningsIds = matchRepository.getInningId(matchId);

        Inning inning1 = inningRepository.createInning(inningsIds.get(0));
        Inning inning2 = inningRepository.createInning(inningsIds.get(1));


        PlayerStatsInSingleMatch playerStatsInSingleMatch = initialisePlayerStats(inning1, inning2, playerId);

        if(matchId == Constants.notExist) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(playerStatsInSingleMatch, HttpStatus.OK);

        }

    }


    public  PlayerStatsInSingleMatch initialisePlayerStats(Inning inning1, Inning inning2, int playerId) throws SQLException, ClassNotFoundException {

        Player player = playersRepository.createPlayer(playerId);


        String playerName = player.getPlayerName();
        int runsScored = PlayerUtil.getPlayerWiseScore(player, inning1) + PlayerUtil.getPlayerWiseScore(player,inning2);
        int centuries = PlayerUtil.getCenturies(player, inning1) + PlayerUtil.getCenturies(player, inning2);
        int sixes = PlayerUtil.getSixes(player, inning1) + PlayerUtil.getSixes(player, inning2);
        int fours = PlayerUtil.getFours(player, inning1) + PlayerUtil.getFours(player, inning2);
        int ballsPlayed = PlayerUtil.getTotalBallsPlayed(player, inning1) + PlayerUtil.getTotalBallsPlayed(player,inning2);


        int wicketTaken = PlayerUtil.getWicketTakenByBowler(player, inning1) + PlayerUtil.getWicketTakenByBowler(player,inning2);


        BattingStatsOfPlayer battingStatsOfPlayer = new BattingStatsOfPlayer(runsScored,centuries,sixes,fours,ballsPlayed);
        BowlingStatsOfPlayer bowlingStatsOfPlayer = new BowlingStatsOfPlayer(wicketTaken,ballsPlayed);

        PlayerStatsInSingleMatch playerStatsInSingleMatch = new PlayerStatsInSingleMatch( playerName, battingStatsOfPlayer, bowlingStatsOfPlayer );

        return playerStatsInSingleMatch;


    }





}
