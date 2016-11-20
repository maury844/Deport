package projects.brainiacs.formtest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Player {

    private String firstName;
    private String LastName;
    private int code;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public JSONObject teamToJSON()
    {
        JSONObject player = new JSONObject();
        try
        {
            player.put("code", this.code);
            player.put("name", this.firstName);

            //team.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return player;
    }

}
