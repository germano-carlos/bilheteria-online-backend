package BL;

import DAO.UsuarioDAO;
import Entities.Usuario;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

public class UsuarioBL {

    public static JsonObject loginBL(Request request, Response response) throws Exception {
        boolean login = false;

        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        String cpf = params.get("cpf").toString().replace("\"","");
        String password =  params.get("password").toString().replace("\"","");

        boolean credentials = UsuarioDAO.checkCredentials(cpf, password);
        if(credentials)
        {
            login = true;
            Usuario user = UsuarioDAO.getByCredentials(cpf, password);

            request.session(true);
            request.session().attribute("userName",user.getName());
            request.session().attribute("userBirth",user.getBirth());
            request.session().attribute("userSex", user.getSex());
            request.session().attribute("userPermission",user.getPermissao().toString());

            return user.to_Object(user);
        }
        else
        {
            throw new Exception("Credenciais Incorretas, favor inserir o email e/ou senha novamente");
        }

    }

    //Adiciona usuario
    public static Usuario add(Request request, Response response){
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        String cpf = params.get("cpf").toString().replace("\"","");
        String name = params.get("name").toString().replace("\"","");;
        String address = params.get("address").toString().replace("\"","");;
        String password = params.get("password").toString().replace("\"","");;
        String birth = params.get("birth").toString().replace("\"","");;
        Character sex = params.get("sex").toString().charAt(1);


        Usuario usuario = new Usuario(cpf, name, address, password,birth,sex);

        UsuarioDAO.add(usuario);

        response.status(201);

        return usuario;
    }

}
