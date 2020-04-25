package Entities;

import Enums.SituacaoCartao;

public class Operadora {
    private int id;
    private String nickname;
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private SituacaoCartao situacaoCartao;

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
}
