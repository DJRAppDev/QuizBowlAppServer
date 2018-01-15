package quizbowl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
@AutoJsonRpcServiceImpl
public class QuizBowlImpl implements QuizBowl {
    private int buzz;
    private Player buzzPlayer;
    private int lobbyState = 0;
    private int endState = 0;
    private Connection connection = null;
    private Statement statement = null;

    public QuizBowlImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Player addUser(String user, String teamId, int gameMaster) throws SQLException {
        Player player = new Player(user,teamId,gameMaster);
        statement.executeUpdate("create table if not exists player (name string primary key, teamId string, gameMaster integer, score integer)");
        statement.executeUpdate("insert into player values('"+user+"','"+teamId+"','"+gameMaster+"','"+0+"')");
        return player;
    }
    public Team addTeam(String name) throws SQLException {
        Team team = new Team(name);
        statement.executeUpdate("create table if not exists team (name string primary key, score integer)");
        statement.executeUpdate("insert into team values ('"+name+"','"+0+"')");
        return team;
    }
    public ArrayList<Player> getPlayers() throws SQLException {
        ArrayList<Player> result = new ArrayList<>();
        ResultSet rs = null;
        Player player = null;
            rs = statement.executeQuery("select * from player");
        while (rs.next()) {
                player = new Player(rs.getString("name"),rs.getString("teamId"),rs.getInt("gameMaster"),rs.getInt("score"));
            result.add(player);
        }
        return result;
    }
    public ArrayList<Team> getTeams() throws SQLException {
        ArrayList<Team> result = new ArrayList<>();
        ResultSet rs = null;
        Team team = null;
        rs = statement.executeQuery("select * from team");
        while (rs.next()) {
            team = new Team(rs.getString("name"),rs.getInt("score"));
            result.add(team);
        }

        return result;
    }
    public void buzz(Player player) {
        if (buzz == 0) {
            buzz = 1;
            buzzPlayer = player;
        }
    }
    public void clearBuzz() {
        buzz = 0;
        buzzPlayer = null;
    }
    public int checkBuzz() {
        return buzz;
    }
    public Player getWhoBuzzed() {
        return buzzPlayer;
    }
    public void addPoints(int points, Player player) throws SQLException {
        String username = player.getUsername();
        String team = player.getTeamId();
        int score = 0;
        ResultSet rs = statement.executeQuery("select score from player where player.name =" + username);
        while (rs.next()) {
            score = rs.getInt("score");
        }
        score+=points;
        statement.executeUpdate("update player set score ="+points+"where player.name ="+username);
        rs = statement.executeQuery("select score from team where team.name ="+team);
        while (rs.next()) {
            score = rs.getInt("score");
        }
        score+=points;
        statement.executeUpdate("update team set score ="+points+"where team.name ="+team);
    }
    public int getLobbyState() {
        return lobbyState;
    }
    public void setLobbyState() {
        lobbyState = 1;
    }
    public int getEndState() {
        return endState;
    }
    public void setEndState() {
        endState = 1;
    }
}