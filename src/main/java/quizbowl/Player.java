package quizbowl;

public class Player {
    //Hello
    private String username;
    private int score;
    private String teamId;
    private int gameMaster;

    public Player(String username, String teamId, int gameMaster) {
        this.username = username;
        this.teamId = teamId;
        this.gameMaster = gameMaster;
        score = 0;
    }
    public Player(String username, String teamId, int gameMaster, int score) {
        this.username = username;
        this.teamId = teamId;
        this.gameMaster = gameMaster;
        this.score = score;
    }
    public String getUsername() {
        return username;
    }
    public int getScore() {
        return score;
    }
    public String getTeamId() {
        return teamId;
    }
    public int getGameMaster() {
        return gameMaster;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    public void setGameMaster(int gameMaster) {
        this.gameMaster = gameMaster;
    }
}