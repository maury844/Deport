package projects.brainiacs.formtest;

/**
 * Created by Mauricio on 21/11/2016.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import projects.brainiacs.formtest.Models.Evento;
import projects.brainiacs.formtest.Models.Partido;
import projects.brainiacs.formtest.Models.Deportista;
import projects.brainiacs.formtest.Models.Equipo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DeportesService {

    //String ENDPOINT = "";
    String ENDPOINT = "http://10.0.0.22:8080";
    //String ENDPOINT = "http://192.168.40.149:8080";

    @GET("/persona")
    Call<Deportista> getDeportista(@Query("codigo") String codigo);

    @POST("/equipo/persona")
    Call<ResponseBody> postDeportista (@Body Deportista body);

    @GET("/persona")
    Call<List<Deportista>> getDeportistas();


    @GET("/partido")
    Call<List<Partido>> getPartidos();

    //
    @GET("/partido")
    Call<List<Partido>> getPartidosDeporte(@Query("deporte") String deporte);

    //Crear un partido
    @POST("/partido")
    Call<ResponseBody> postPartido(@Body Partido body);

    //Get de los partidos especificos de un evento
    @GET("/partido/evento")
    Call<List<Partido>> getPartidosEvento(@Query("nombreEvento") String nombreEvento);

    //Crear un equipo
    @POST("/equipo")
    Call<ResponseBody> postEquipo(@Body Equipo body);

    //Anhadir un resultado
    @POST("/partido/resultado")
    Call<ResponseBody> postResultado(@Body Partido body);

    //Get los equipos del anho en curso
    @GET("/equipo/actuales")
    Call<List<Equipo>> getEquiposActuales();

    //Get los equipos registrados en un evento buscando por Codigo de evento (sin usar)
    @GET("/equipo/evento")
    Call<List<Equipo>> getEquiposEnEvento(@Query("codigoEvento") int codigoEvento);

    //Get los equipos registrados en un evento buscando por nombre de evento
    @GET("/equipo/evento")
    Call<List<Equipo>> getEquiposEnEventoPorNombre(@Query("nombre") String nombreEvento);

    //Get de todos los eventos registrados
    @GET("/evento")
    Call<List<Evento>> getEventos();

    //Crea un evento
    @POST("/evento")
    Call<Evento> postEvento (@Body Evento body);

    //Get de las disciplinas
    @GET("/disciplina")
    Call<List<String>> getDisciplinas ();

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


}
