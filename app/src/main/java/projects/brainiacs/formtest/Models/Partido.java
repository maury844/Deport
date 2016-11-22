package projects.brainiacs.formtest.Models;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Partido {

    private int score;
    private Equipo[] equipos;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Equipo getEquipo(int position)
    {
        return equipos[position];
    }

    public Equipo[] getEquipos() {
        return equipos;
    }

    public void setEquipo(Equipo team, int posicion) {
        this.equipos[posicion] = team;
    }

    public void setEquipos(Equipo[] equipos) {
        this.equipos = equipos;
    }

    public Partido()
    {
        //score = null;
        equipos = new Equipo[2];
    }





}
