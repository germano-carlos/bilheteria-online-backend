package Enums;

public enum SituacaoCartao
{

    ATIVO (1,"CARTAOATIVO"),
    INATIVO (2,"CARTAOINATIVO");

    private int idSituacao;
    private String nomeSituacao;

    SituacaoCartao(int id, String nomeSituacao) {
        this.idSituacao = id;
        this.nomeSituacao = nomeSituacao;
    }
}
