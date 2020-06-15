package Entities;

import Enums.SituacaoCartao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Operadora {
    private int id;
    private String nickname;
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private SituacaoCartao situacaoCartao;

    public Operadora(String nickname, String cardNumber, String cvv, String expirationDate){
        this.nickname = nickname;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.situacaoCartao = SituacaoCartao.ATIVO;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id;}
    public String getNickname() { return this.nickname;}
    public void setNickname(String nickname) { this.nickname = nickname;}
    public String getCardNumber() { return this.cardNumber;}
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber;}
    public String getCvv() { return this.cvv;}
    public void setCvv(String cvv) { this.cvv = cvv;}
    public String getExpirationDate() { return this.expirationDate;}
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate;}
    public SituacaoCartao getSituacaoCartao() { return this.situacaoCartao;}
    public void setActive(SituacaoCartao situacaoCartao) { this.situacaoCartao = situacaoCartao;}
    public JsonObject to_Object(Operadora card)
    {
        Gson gson = new Gson();
        String json = gson.toJson(card);

        JsonParser jsonParser = new JsonParser();
        JsonObject object = (JsonObject) jsonParser.parse(json);

        return object;
    }
}
