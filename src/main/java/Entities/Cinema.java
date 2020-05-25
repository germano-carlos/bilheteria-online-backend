package Entities;

import Enums.SituacaoCinema;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Cinema {
    private int id;
    private String name;
    private String initials;
    private String adress;
    private String cnpj;
    private SituacaoCinema situacao;

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public String getInitials() { return this.initials;}
    public void setInitials(String initials) { this.initials = initials;}
    public String getAdress() { return this.adress;}
    public void setAdress(String adress) { this.adress = adress;}
    public String getCnpj() { return this.cnpj;}
    public void setCnpj(String cnpj) { this.cnpj = cnpj;}
    public SituacaoCinema getSituacaoCinema() { return this.situacao;}
    public void setActive(SituacaoCinema situacao) { this.situacao = situacao;}
    public JsonObject to_Object(Cinema cine)
    {
        Gson gson = new Gson();
        String json = gson.toJson(cine);

        JsonParser jsonParser = new JsonParser();
        JsonObject object = (JsonObject) jsonParser.parse(json);

        return object;
    }
}
