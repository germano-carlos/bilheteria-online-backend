package Enums;

public enum Permissao
{

    FULLACESS ("1"),
    CADASTRO ("2"),
    FINANCEIRO ("3"),
    USUARIO ("4"),
    GUEST("5");

    private String idPermissao;

    Permissao(String id) {
        this.idPermissao = id;
    }
}
