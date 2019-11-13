package br.unifor.uniflix.controller;

import br.unifor.uniflix.model.Filme;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/filmes")
public class FilmesController extends filmeFactory{

    public FilmesController(JSONObject novofilme) {
		super(novofilme);
		// TODO Auto-generated constructor stub
	}


	/*@GET
    @Produces(MediaType.APPLICATION_JSON)//CRIAR FACTORY PARA CHAMAR SÓ A FACTORY E NÃO CRIAR AQUI UM DE CADA VEZ
    public Response airingToday() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3" + "/tv/airing_today?api_key=<API-KEY-AQUI>")
                .build();

        Call call = client.newCall(request);

        okhttp3.Response response = call.execute();
        if (response.isSuccessful()) {
            JSONObject jsonResponse = new  JSONObject(response.body().string());
            JSONArray result = jsonResponse.getJSONArray("results");
            List<Filme> filmes = new ArrayList<>();
            for (int i = 0; i < result.length(); ++i) {
                JSONObject movieJson = result.getJSONObject(i);
                Filme filme = new Filme();
                filme.setTitulo(movieJson.getString("title"));
                filme.setSinopse(movieJson.getString("overview"));
                filme.setAdulto(movieJson.getBoolean("adult"));
                filme.setNota(movieJson.getDouble("vote_average"));
                filmes.add(filme);
            }
                filmes.add(filme);
            }
            return Response.ok(filmes).build();
        }
        return Response.serverError().build();
    }
      */  
        
    @GET
    @Produces(MediaType.APPLICATION_JSON) //CRIAR FACTORY PARA CHAMAR SÓ ELE E NÃO PRECISAR CRIAR AQUI CADA UM DELES
    public Response popularMovies() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3" + "/movie/popular?api_key=<API-KEY-AQUI>")
                .build();

        Call call = client.newCall(request);

        okhttp3.Response response = call.execute();
        if (response.isSuccessful()) {
            JSONObject jsonResponse = new  JSONObject(response.body().string());
            JSONArray result = jsonResponse.getJSONArray("results");
            List<Filme> filmes = new ArrayList<>();
            for (int i = 0; i < result.length(); ++i) {
                JSONObject movieJson = result.getJSONObject(i);//aí passa a chamar pela factory
                filmeFactory filmeFactory = new filmeFactory(movieJson);
                /*Filme filme = new Filme();
                filme.setTitulo(movieJson.getString("title"));
                filme.setSinopse(movieJson.getString("overview"));
                filme.setAdulto(movieJson.getBoolean("adult"));
                filme.setNota(movieJson.getDouble("vote_average"));
             */   
                filmes.add(filmeFactory.filme);
            }
            return Response.ok(filmes).build();
        }
        return Response.serverError().build();
    }
}
