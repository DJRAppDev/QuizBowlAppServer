package quizbowl;

public class Team {
    private String name;
    private int score;

    public Team(String name) {
        this.name = name;
        score = 0;
    }
    public Team(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
