package projects.brainiacs.formtest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Team {

    private ArrayList<Player> members;
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Player> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Player> members) {
        this.members = members;
    }

    // -------------------------------------------------------------
    public Team(ArrayList<Player> members, String teamName) {
        this.members = members;
        this.teamName = teamName;
    }

    public Team()
    {
        members = new ArrayList<>();
    }

    public JSONObject teamToJSON()
    {
        JSONObject team = new JSONObject();
        try
        {
            //team.put("id", "3");
            team.put("name", this.teamName);
            //team.put("year", "3rd");
            //team.put("curriculum", "Arts");
            //team.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return team;
    }


}
