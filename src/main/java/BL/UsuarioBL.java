package BL;

import DAO.UsuarioDAO;
import Entities.Usuario;

import spark.Request;
import spark.Response;

public class UsuarioBL {

    public static boolean loginBL(Request request, Response response) throws Exception {
        boolean login = false;
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        boolean credentials = UsuarioDAO.checkCredentials(email, password);
        if(credentials)
        {
            login = true;
            Usuario user = UsuarioDAO.getByCredentials(email, password);

            request.session(true);
            request.session().attribute("userName",user.getName());
            request.session().attribute("userBirth",user.getBirth());
            request.session().attribute("userSex",user.getSex());
            request.session().attribute("userPermission",user.getPermissao());
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
        String birth = request.queryParams("birth");
        Character sex = request.queryParams("sex").charAt(0);


        Usuario usuario = new Usuario(cpf, name, adress, password,birth,sex);

        UsuarioDAO.add(usuario);

        response.status(201);

        return usuario;
    }

}
