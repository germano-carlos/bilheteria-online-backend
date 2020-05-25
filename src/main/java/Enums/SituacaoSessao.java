package Enums;

public enum SituacaoSessao {
    ATIVO ("1"),
    INATIVO ("2");

    private String idSituacao;

    SituacaoSessao(String id) {
        this.idSituacao = id;
    }
    public String getId () { return this.idSituacao; }
}
