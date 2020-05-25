package Entities;

import Enums.SituacaoSessao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Dictionary;

public class Sessao {
    private int id;
    private int movieId;
    private int cineId;
    private Dictionary<String, String> userArmChair;
    private SituacaoSessao situacaoSessao;

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id;}
    public int getMovieId() { return this.movieId;}
    public void setMovieId(int movieId) { this.movieId = movieId;}
    public int getCineId() { return this.cineId;}
    public void setCineId(int cineId) { this.cineId = cineId;}
    public Dictionary<String, String> getUserArmChair() { return this.userArmChair;}
    public void setUserArmChair(Dictionary<String, String> userArmChair) { this.userArmChair = userArmChair;}
    public SituacaoSessao getSituacaoSessao() { return this.situacaoSessao;}
    public void setSituacaoSessao(SituacaoSessao situacaoSessao) { this.situacaoSessao = situacaoSessao;}
    public JsonObject to_Object(Sessao session)
    {
        Gson gson = new Gson();
        String json = gson.toJson(session);

        JsonParser jsonParser = new JsonParser();
        JsonObject object = (JsonObject) jsonParser.parse(json);

        return object;
    }
}
