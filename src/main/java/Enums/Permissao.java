package Enums;

public enum Permissao
{

    FULLACESS (1,"administrador"),
    CADASTRO (2,"cadastroComercial"),
    FINANCEIRO (3,"cadastroFinanceiro"),
    USUARIO (4,"usuario");

    private int idPermissao;
    private String nomePermissao;

    Permissao(int id, String nomePermissao) {
        this.idPermissao = id;
        this.nomePermissao = nomePermissao;
    }
}
