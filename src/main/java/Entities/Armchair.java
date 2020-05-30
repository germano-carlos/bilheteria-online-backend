package Entities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Armchair {
    private String id;
    private String userId;
    private String cineId;
    private String movieId;

    public Armchair(String armchair_id, String userId, String cineId) {
        this.id = armchair_id;
        this.userId = userId;
        this.cineId = cineId;
    }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id;}
    public String getUserId() { return this.userId;}
    public void setUserId(String userId) { this.userId = userId;}
    public String getMovieId() { return this.movieId;}
    public void setMovieId(String movieId) {  this.movieId = movieId;}
    public String getCineId() { return this.cineId;}
    public void setCineId(String cineId) { this.cineId = cineId;}

    public JsonObject to_Object(Armchair armchair)
    {
        Gson gson = new Gson();
        String json = gson.toJson(armchair);

        JsonParser jsonParser = new JsonParser();
        JsonObject object = (JsonObject) jsonParser.parse(json);

        return object;
    }
}
