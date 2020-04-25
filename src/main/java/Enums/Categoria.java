package Enums;

public enum Categoria
{
    ACAO(1,"ACAO"),
    ANIMACAO(2,"ANIMACAO"),
    AVENTURA(3,"AVENTURA"),
    COMEDIA(4,"COMEDIA"),
    DOCUMENTARIO(5, "DOCUMENTARIO"),
    FANTASIA(6, "FANTASIA"),
    FAROESTE(7,"FAROESTE"),
    FICCAO(8,"FICCAO"),
    GUERRA(9,"GUERRA"),
    MUSICAL(10,"MUSICAL"),
    ROMANCE(11,"ROMANCE"),
    SUSPENSE(12,"SUSPENSE"),
    TERROR(13,"TERROR"),
    DRAMA(14,"DRAMA");

    private int idCategoria;
    private String nomeCategoria;

    Categoria(int i, String nomeCategoria) {
        this.idCategoria = i;
        this.nomeCategoria = nomeCategoria;
    }
}
