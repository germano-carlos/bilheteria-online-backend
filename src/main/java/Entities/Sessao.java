package Entities;

import Enums.SituacaoSessao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Dictionary;
import java.util.List;

public class Sessao {
    private int id;
    private String movieId;
    private String cineId;
    private List<Armchair> userArmChair;
    private SituacaoSessao situacaoSessao;

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id;}
    public String getMovieId() { return this.movieId;}
    public void setMovieId(String movieId) { this.movieId = movieId;}
    public String getCineId() { return this.cineId;}
    public void setCineId(String cineId) { this.cineId = cineId;}
    public List<Armchair> getUserArmChair() { return this.userArmChair;}
    public void setUserArmChair(List<Armchair> userArmChair) { this.userArmChair = userArmChair;}
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
