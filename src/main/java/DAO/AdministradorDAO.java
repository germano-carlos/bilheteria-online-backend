package DAO;

import Entities.Filme;
import Entities.Usuario;
import Enums.Permissao;

import java.util.ArrayList;


public class AdministradorDAO {
    public void AddMoviesByCine(Usuario user, ArrayList<String> params) throws Exception {
        if(user.getPermissao() != Permissao.FULLACESS)
        {
            throw new Exception("Você não possui permissão para acessar este recurso");
        }

        Filme filme = new Filme();


    }
}
