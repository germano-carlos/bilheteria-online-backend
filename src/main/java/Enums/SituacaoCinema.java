package Enums;

public enum SituacaoCinema {

    ATIVO ("1"),
    INATIVO ("2");

    private String idSituacao;

    SituacaoCinema(String id) {this.idSituacao = id;}
    public String getId () { return this.idSituacao; }
}
