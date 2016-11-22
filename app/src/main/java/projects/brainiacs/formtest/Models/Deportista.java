package projects.brainiacs.formtest.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mauricio on 31/10/2016.
 */

public class Deportista {

    private int codigo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int ci;
    private int celular;
    private String email;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JSONObject playerToJSON()
    {
        JSONObject player = new JSONObject();
        try
        {
            player.put("codigo", this.codigo);
            player.put("nombre", this.nombre);

            //team.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return player;
    }

    @Override
    public String toString()
    {
        //return nombre + " " + apellidoPaterno;
        return Integer.toString(codigo);
    }

}
