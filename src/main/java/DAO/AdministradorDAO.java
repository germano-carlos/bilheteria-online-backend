package DAO;

import BL.UsuarioBL;
import Entities.Filme;
import Entities.Usuario;
import Enums.Permissao;

import java.util.ArrayList;

import static spark.Spark.get;


public class AdministradorDAO {
    public Filme AddMoviesByCine()
    {
        Usuario usuario = UsuarioBL.getById();
        if(usuario.getPermissao() == Permissao.FULLACESS)
        {
            ArrayList<Filme> array = new ArrayList();
            get("/", (req, res) -> res.body());
        }

    }
}
