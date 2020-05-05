package BL;

import DAO.UsuarioDAO;
import Entities.Usuario;

import spark.Request;
import spark.Response;

public class UsuarioBL {

    public static boolean loginBL(String email, String password) throws Exception {
        boolean login = false;

        boolean credentials = UsuarioDAO.checkCredentials(email, password);
        if(credentials)
        {
            login = true;
            Usuario user = UsuarioDAO.getByCredentials(email, password);

            // Create Session
            // Redirect? another controller, where are our views?
        }
        else
        {
            throw new Exception("Credenciais Incorretas, favor inserir o email e/ou senha novamente");
        }

        return login;
    }

    //Adiciona usuario
    public static Object add(Request request, Response response){
        String cpf = request.queryParams("cpf");
        String name = request.queryParams("name");
        String adress = request.queryParams("adress");
        String password = request.queryParams("password");

        Usuario usuario = new Usuario(cpf, name, adress, password);

        UsuarioDAO.add(usuario);

        response.status(201);

        return usuario;
    }

}
