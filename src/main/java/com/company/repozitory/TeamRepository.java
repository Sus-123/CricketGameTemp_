package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.Inning;
import com.company.entity.Team;
import com.company.util.InningUtil;

import java.sql.*;

public class TeamRepository {


    //Table- TeamTable (TeamId, TeamName)
    public static int insertTeam(Team team) throws SQLException, ClassNotFoundException {

        String teamName = team.getName();

        if(getTeamIdFromTeamName(teamName) != -1) {
            return getTeamIdFromTeamName(teamName);
        }

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query =  "INSERT INTO TeamTable (`TeamName`) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,teamName);

        int teamId = 0;
        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                teamId = rs.getInt(1);
            }

            PlayersRepository.insertTeamPlayers(teamId, team.getPlayers());
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return teamId;
    }

    public static Team createTeam (int teamId) throws SQLException, ClassNotFoundException {

        String query = " select * FROM TeamTable where TeamId = " + teamId ;
        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        String teamName = "";
        if(rs.next()) {
            teamName = rs.getString(2);
        }

        Team team = new Team(teamName);
        return team;

    }


    public static int getTeamIdFromTeamName (String team) throws SQLException, ClassNotFoundException {

        String teamName = "'" + team + "'";
        String query = " select * FROM TeamTable where TeamName = " + teamName ;

        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        int id =-1;

        if(rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }

    public static int getWinningTeamId(Inning inning1, Inning inning2) throws SQLException, ClassNotFoundException {

        if(InningUtil.getScoreOfInning(inning1) > InningUtil.getScoreOfInning(inning2)) {
            return getTeamIdFromTeamName(inning1.getBattingTeam().getName());
        }
        return  getTeamIdFromTeamName(inning2.getBattingTeam().getName());
    }


}
