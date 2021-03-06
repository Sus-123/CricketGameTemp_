package com.company.scoreboard;
import com.company.entity.Inning;
import com.company.entity.Player;
import com.company.util.InningUtil;
import com.company.util.PlayerUtil;
import com.company.util.Util;

public class ScoreBoard {


    public  static  void showMatchResult (Inning inning1 , Inning inning2) {
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");

        System.out.println(" Final Result : ");
        int scoreOfInning1 = InningUtil.getScoreOfInning(inning1);
        int scoreOfInning2 = InningUtil.getScoreOfInning(inning2);

        if( scoreOfInning1 > scoreOfInning2 ) {
            System.out.println(inning1.getBattingTeam().getName() + " Won the Match by : " + (scoreOfInning1 - scoreOfInning2));
        } else if (scoreOfInning1 < scoreOfInning2 ){
            System.out.println(inning2.getBattingTeam().getName() + " Won the Match by : " + (scoreOfInning2 - scoreOfInning1));
        } else {
            System.out.println(" Match Draw with : " + scoreOfInning1 +" Run");
        }

        showPlayerWiseResult(inning1, inning2);
        showPlayerWiseResult(inning2, inning1);
    }



    public static void showPlayerWiseResult (Inning inning1, Inning inning2) {
        System.out.println("--------------------------------------------");
        System.out.println(inning1.getBattingTeam().getName() + " : " + InningUtil.getScoreOfInning(inning1));

        System.out.println("PlayerName\t PlayerType\t  Runs\t BallsPlayed\t WicketTaken");
        for (int i = 0; i <= 10; i++) {
            Player player = inning1.getBattingTeam().getPlayers().get(i);
            System.out.println(player.getPlayerName() +"\t\t " +  player.getPlayerType() + "\t\t " + PlayerUtil.getPlayerWiseScore(player,inning1) + " \t\t" + PlayerUtil.getTotalBallsPlayed(player, inning1) + " \t\t" + PlayerUtil.getWicketTakenByBowler(player,inning2));
        }
    }
}
