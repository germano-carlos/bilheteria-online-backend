package BL;

import DAO.UsuarioDAO;
import Entities.Usuario;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    public static Usuario add(Request request, Response response){
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        String cpf = params.get("cpf").toString().replace("\"","");
        String name = params.get("name").toString().replace("\"","");;
        String adress = params.get("adress").toString().replace("\"","");;
        String password = params.get("password").toString().replace("\"","");;
        String birth = params.get("birth").toString().replace("\"","");;
        Character sex = params.get("sex").toString().charAt(1);


        Usuario usuario = new Usuario(cpf, name, adress, password,birth,sex);

        UsuarioDAO.add(usuario);

        response.status(201);

        return usuario;
    }

}
