package BL;

import DAO.SessaoDAO;
import Entities.Armchair;
import Entities.Sessao;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.*;

public class SessaoBL {

    public static JsonObject add(Request request, Response response) throws SQLException, ClassNotFoundException {
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        String usuarioId = params.get("userId").toString().replace("\"","");;
        String movieId = params.get("movieId").toString().replace("\"","");;
        String cineId = params.get("cineId").toString().replace("\"","");;
        String date = params.get("date").toString().replace("\"","");;

        if(SessaoDAO.checkSession(movieId,cineId, date))
        {
            Sessao session = SessaoDAO.add(movieId,cineId,date);
            return  session.to_Object(session);
        }

        Sessao session = SessaoDAO.getByParams(movieId,cineId,date);
        return session.to_Object(session);
    }

    public List<Armchair> free(Request request, Response response) throws SQLException, ClassNotFoundException {

        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        String sessionId = params.get("sessionId").toString();

        return SessaoDAO.getArmChairFree(sessionId);
    }

    public static void addUserInSession(String compradorId, String[] chairs, int sessaoId) throws SQLException, ClassNotFoundException {
        SessaoDAO.addUserInSession(compradorId,chairs, sessaoId);
    }
}
