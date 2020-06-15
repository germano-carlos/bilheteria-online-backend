package BL;

import DAO.UsuarioDAO;
import Entities.Cinema;
import Entities.Usuario;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

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
            request.session().attribute("cineId", CinemaBL.getByUserId(user.getCpf()));

            return user.to_Object(user);
        }
        else
        {
            throw new Exception("Credenciais Incorretas, favor inserir o email e/ou senha novamente");
        }

    }

    //Adiciona usuario
    public static JsonObject add(Request request, Response response){
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

        return usuario.to_Object(usuario);
    }

    public static Usuario getByCPF(String cpf) throws SQLException, ClassNotFoundException {
        return UsuarioDAO.getByCPF(cpf);
    }

}
