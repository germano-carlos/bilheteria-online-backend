package Entities;

import Enums.Permissao;
import Enums.SituacaoCinema;
import Enums.SituacaoSessao;

import java.util.Dictionary;
import java.util.List;

public class Usuario {
    private String cpf;
    private String name;
    private String adress;
    private String password;
    private Permissao userType;
    private List<Operadora> wallet;

    public String getCpf() { return this.cpf;}
    public void setCpf(String cpf) { this.cpf = cpf;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public String getAdress() { return this.adress;}
    public void setAdress(String adress) { this.adress = adress;}
    public String getPassword() { return this.password;}
    public void setPassword(String password) { this.password = password;}
    public Permissao getPermissao() { return this.userType;}
    public void setPermissao(Permissao userType) { this.userType = userType;}
    public List<Operadora> getWallet() { return this.wallet;}
    public void setWallet(List<Operadora> wallet) { this.wallet = wallet;}
}
