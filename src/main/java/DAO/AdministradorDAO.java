package DAO;

import BL.UsuarioBL;
import Entities.Filme;
import Entities.Usuario;
import Enums.Permissao;

import java.util.ArrayList;

import static spark.Spark.get;


public class AdministradorDAO {
    public void AddMoviesByCine()
    {
        Usuario usuario = new Usuario();//UsuarioBL.getById();
        if(usuario.getPermissao() == Permissao.FULLACESS)
        {

        }

    }
}
