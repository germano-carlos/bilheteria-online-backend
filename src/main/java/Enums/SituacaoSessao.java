package Enums;

public enum SituacaoSessao {
    ATIVO (1,"CARTAOATIVO"),
    INATIVO (2,"CARTAOINATIVO");

    private int idSituacao;
    private String nomeSituacao;

    SituacaoSessao(int id, String nomeSituacao) {
        this.idSituacao = id;
        this.nomeSituacao = nomeSituacao;
    }
}
