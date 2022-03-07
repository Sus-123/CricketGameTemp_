package com.company.controllers;

import com.company.entity.Inning;
import com.company.entity.MatchStats;
import com.company.entity.TeamStats;
import com.company.repozitory.InningRepository;
import com.company.repozitory.MatchRepository;
import com.company.repozitory.TeamRepository;
import com.company.util.InningUtil;
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
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private InningRepository inningRepository;

    @Autowired
    private TeamRepository teamRepository;


    @GetMapping("/match/{id}")
    public ResponseEntity<MatchStats> getMatchById (@PathVariable("id") int id) throws SQLException, ClassNotFoundException {

        ArrayList<Integer> inningsIds = matchRepository.getInningId(id);

        Inning inning1 = inningRepository.createInning(inningsIds.get(0));
        Inning inning2 = inningRepository.createInning(inningsIds.get(1));

        MatchStats matchStats=  initialiseMatchStats(inning1, inning2, id);

        if(id == Constants.notExist) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        }
        else {
            return new ResponseEntity<>(matchStats, HttpStatus.OK);
        }

    }

    private MatchStats initialiseMatchStats(Inning inning1, Inning inning2, int matchId) throws SQLException, ClassNotFoundException {


        int teamAid = teamRepository.getTeamIdFromTeamName(inning1.getBattingTeam().getName());
        int teamBid = teamRepository.getTeamIdFromTeamName(inning2.getBattingTeam().getName());
        int overs = inning1.getNumOfOver();



        TeamStats teamAStats = new TeamStats(teamAid, inning1.getBattingTeam().getName(), InningUtil.getScoreOfInning(inning1), InningUtil.getOversPlayedOfInning(inning1), InningUtil.getWicketFallen(inning1) );

        TeamStats teamBStats = new TeamStats(teamBid, inning2.getBattingTeam().getName(), InningUtil.getScoreOfInning(inning2), InningUtil.getOversPlayedOfInning(inning2), InningUtil.getWicketFallen(inning2) );

        MatchStats matchStats = new MatchStats(matchId, teamAStats, teamBStats, overs, teamRepository.getWinningTeamId(inning1, inning2) );

        return matchStats;

    }


}
