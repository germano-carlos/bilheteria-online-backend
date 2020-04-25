package Enums;

public enum SituacaoCinema {

    ATIVO (1,"CARTAOATIVO"),
    INATIVO (2,"CARTAOINATIVO");

    private int idSituacao;
    private String nomeSituacao;

    SituacaoCinema(int id, String nomeSituacao) {
        this.idSituacao = id;
        this.nomeSituacao = nomeSituacao;
    }

}
