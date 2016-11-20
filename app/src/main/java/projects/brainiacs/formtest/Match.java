package projects.brainiacs.formtest;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Match {

    private int score;
    private Team[] teams;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Team getTeam(int position)
    {
        return teams[position];
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeam(Team team, int position) {
        this.teams[position] = team;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public Match()
    {
        //score = null;
        teams = new Team[2];
    }





}
