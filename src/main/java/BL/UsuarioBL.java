package BL;

import DAO.UsuarioDAO;
import Entities.Usuario;

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


}
