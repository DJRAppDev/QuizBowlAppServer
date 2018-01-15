package quizbowl;

import com.googlecode.jsonrpc4j.JsonRpcService;

import java.sql.SQLException;
import java.util.ArrayList;

@JsonRpcService("/quizbowl.json")
public interface QuizBowl {
    Player addUser(String name, String teamId, int gameMaster) throws SQLException;
    Team addTeam(String name) throws SQLException;
    ArrayList<Team> getTeams() throws SQLException;
    ArrayList<Player> getPlayers() throws SQLException;
    void buzz(Player player);
    void clearBuzz();
    int checkBuzz();
    Player getWhoBuzzed();
    void addPoints(int points, Player player) throws SQLException;
    void setLobbyState();
    void setEndState();
    int getLobbyState();
    int getEndState();
}
