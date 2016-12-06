package projects.brainiacs.formtest.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Partido /*implements Parcelable*/{

    private String idPartido;
    private String puntaje;
   // private Equipo equipo1;
    //private Equipo equipo2;
    private String horaInicio;
    private String equipo1;
    private String equipo2;
    private String fecha;

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }
/*
    public Equipo getEquipo(int position)
    {
        return equipos[position];
    }

    public Equipo[] getEquiposEnEvento() {
        return equipos;
    }

    public void setEquipo(Equipo team, int posicion) {
        this.equipos[posicion] = team;
    }

    public void setEquipos(Equipo[] equipos) {
        this.equipos = equipos;
    }
*/
/*
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
*/


    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Partido()
    {
        //puntaje = null;
        //equipos = new Equipo[2];

    }

    public Partido(String equipo1, String equipo2, String horaInicio, String fecha)
    {
        //puntaje = null;
        //this.equipos = new Equipo[2];
        //this.equipos[0] = equipo1;
        //this.equipos[1] = equipo2;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.horaInicio = horaInicio;
        this.fecha = fecha;
    }


    public Partido(String equipo1, String equipo2, String horaInicio, String fecha, String idPartido)
    {
        //puntaje = null;
        //this.equipos = new Equipo[2];
        //this.equipos[0] = equipo1;
        //this.equipos[1] = equipo2;
        this.idPartido = idPartido;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.horaInicio = horaInicio;
        this.fecha = fecha;
    }



}
