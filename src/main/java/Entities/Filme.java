package Entities;

import Enums.Categoria;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class Filme {
    private int id;
    private String name;
    private String synopsis;
    private List<Cinema> cineList;
    private String releaseData;
    private String finalDate;
    private String poster;
    private List<Categoria> categoryList;
    private boolean carousel;

    public Filme(String name,String synopsis,String releaseData,String finalDate,List<Cinema> cineList,List<Categoria> categorylist, String poster)
    {
        this.name = name;
        this.synopsis = synopsis;
        this.releaseData = releaseData;
        this.finalDate = finalDate;
        this.cineList  = cineList;
        this.categoryList = categorylist;
        this.poster = poster;
    }

    public Filme() {

    }
    public Filme(String name,String synopsis,String releaseData,String finalDate,List<Cinema> cineList,List<Categoria> categorylist, String poster, int id, String carousel)
    {
        this.name = name;
        this.synopsis = synopsis;
        this.releaseData = releaseData;
        this.finalDate = finalDate;
        this.cineList  = cineList;
        this.categoryList = categorylist;
        this.poster = poster;
        this.id = id;
        this.carousel = carousel.equals("S")  ? true : false;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id;}
    public String getPoster() { return this.poster; }
    public void setPoster(String poster) { this.poster = poster;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public String getSynopsis() { return this.synopsis;}
    public void setSynopsis(String synopsis) { this.synopsis = synopsis;}
    public List<Cinema> getCineList() { return this.cineList;}
    public void setCineList(List<Cinema> cineList) { this.cineList = cineList;}
    public String getReleaseData() { return this.releaseData;}
    public void setReleaseData(String releaseData) { this.releaseData = releaseData;}
    public String getFinalDate() { return this.finalDate;}
    public void setFinalDate(String finalDate) { this.finalDate = finalDate;}
    public List<Categoria> getCategoryList() { return this.categoryList;}
    public void setCategoryList(List<Categoria> categoryList) { this.categoryList = categoryList;}
    public JsonObject to_Object(Filme movie)
    {
        Gson gson = new Gson();
        String json = gson.toJson(movie);

        JsonParser jsonParser = new JsonParser();
        JsonObject object = (JsonObject) jsonParser.parse(json);

        return object;
    }
}
