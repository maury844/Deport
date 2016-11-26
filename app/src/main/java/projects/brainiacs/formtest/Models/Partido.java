package projects.brainiacs.formtest.Models;

import java.util.Date;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Partido {

    private String score;
    private Equipo[] equipos;
    private String horario;
    private Date fecha;


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Partido()
    {
        //score = null;
        equipos = new Equipo[2];

    }

    public Partido(Equipo equipo1, Equipo equipo2, String horario, Date fecha)
    {
        //score = null;
        this.equipos = new Equipo[2];
        this.equipos[0] = equipo1;
        this.equipos[1] = equipo2;
        this.horario = horario;
        this.fecha = fecha;
    }






}
