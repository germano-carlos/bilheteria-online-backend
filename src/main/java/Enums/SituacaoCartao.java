package Enums;

public enum SituacaoCartao
{

    ATIVO ("1"),
    INATIVO ("2");

    private String idSituacao;

    SituacaoCartao(String id) {
        this.idSituacao = id;
    }
    public String getId () { return this.idSituacao; }
}
