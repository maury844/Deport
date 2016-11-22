package projects.brainiacs.formtest;

/**
 * Created by Mauricio on 21/11/2016.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

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
    String ENDPOINT = "http://10.0.0.9:8080";

    @GET("/persona")
    Call<Deportista> getDeportista(@Query("codigo") String codigo);

    @POST("/persona")
    Call<ResponseBody> postDeportista (@Body Deportista body);

    @GET("/persona")
    Call<List<Deportista>> getDeportistas();

    @GET("/partido")
    Call<List<Partido>> getPartidos();

    @GET("/equipo")
    Call<Equipo> getEquipo();

    @GET("/evento")
    Call<List<Evento>> getEvento();

    /*
    @GET("/disciplina")
    Call<List<Championship>> getEvento();

    @GET("/tasks")
    Call<List<Task>> getTasks(@Query("sort") String order);

    */

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

}
