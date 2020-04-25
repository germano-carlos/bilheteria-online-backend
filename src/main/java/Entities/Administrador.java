package Entities;

import Enums.Permissao;

import java.util.List;

public class Administrador extends Usuario
{
    private Permissao userType = Permissao.FULLACESS;
    private List<Cinema> cineList;

    public Permissao getUserType() { return this.userType;}
    public void setUserType(Permissao userType) { this.userType = userType;}
    public List<Cinema>  getCineList() { return this.cineList;}
    public void setCineList(List<Cinema>  name) { this.cineList = cineList;}
}
