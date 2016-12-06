package projects.brainiacs.formtest.Models;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Equipo {

    private String nombre;
    private int anio;
    private String evento;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Equipo() {}

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
