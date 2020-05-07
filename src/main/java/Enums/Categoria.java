package Enums;

public enum Categoria
{
    ACAO("1"),
    ANIMACAO("2"),
    AVENTURA("3"),
    COMEDIA("4"),
    DOCUMENTARIO("5"),
    FANTASIA("6"),
    FAROESTE("7"),
    FICCAO("8"),
    GUERRA("9"),
    MUSICAL("10"),
    ROMANCE("11"),
    SUSPENSE("12"),
    TERROR("13"),
    DRAMA("14");

    private String idCategoria;

    Categoria(String id) {
        this.idCategoria = id;
    }

    public String getId () { return this.idCategoria; }
}
