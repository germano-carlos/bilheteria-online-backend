package Entities;

import Enums.Categoria;
import java.util.List;

public class Filme {
    private int id;
    private String name;
    private String synopsis;
    private List<Cinema> cineList;
    private String releaseData;
    private String finalDate;
    private List<Categoria> categoryList;

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id;}
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
}
