package projects.brainiacs.formtest.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Equipo {

    //private ArrayList<Deportista> miembros;
    private String nombre;
    private int anio;
    Evento evento;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*public ArrayList<Deportista> getMiembros() {
        return miembros;
    }

    public void setMiembros(ArrayList<Deportista> miembros) {
        this.miembros = miembros;
    }

    // -------------------------------------------------------------
    public Equipo(ArrayList<Deportista> miembros, String nombre) {
        this.miembros = miembros;
        this.nombre = nombre;
    }

    public Equipo()
    {
        miembros = new ArrayList<>();
    }
*/


    public Equipo(String nombre)
    {
        this.nombre = nombre;
    }

    public JSONObject teamToJSON()
    {
        JSONObject team = new JSONObject();
        try
        {
            //team.put("id", "3");
            team.put("name", this.nombre);
            //team.put("year", "3rd");
            //team.put("curriculum", "Arts");
            //team.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return team;
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
