package Entities;

import Enums.Permissao;

import java.util.List;

public class Usuario {
    private String cpf;
    private String name;
    private String adress;
    private String password;
    private Character sex;
    private Permissao userType;
    private String birth;
    private List<Operadora> wallet;

    public Usuario()
    {

    }
    public Usuario(String cpf, String name, String adress, String password,String birth, Character sex){
        this.cpf = cpf;
        this.name = name;
        this.adress = adress;
        this.password = password;
        this.birth = birth;
        this.sex = sex;
    }

    public String getCpf() { return this.cpf;}
    public void setCpf(String cpf) { this.cpf = cpf;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public String getAdress() { return this.adress;}
    public void setAdress(String adress) { this.adress = adress;}
    public String getBirth() { return this.birth;}
    public void setBirth(String birth) { this.birth = birth;}
    public String getPassword() { return this.password;}
    public void setPassword(String password) { this.password = password;}
    public Character getSex() { return this.sex;}
    public void setSex(Character sex) { this.sex = sex;}
    public Permissao getPermissao() { return this.userType;}
    public void setPermissao(Permissao userType) { this.userType = userType;}
    public List<Operadora> getWallet() { return this.wallet;}
    public void setWallet(List<Operadora> wallet) { this.wallet = wallet;}
}
