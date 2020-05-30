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

    public static JsonObject add(Request request, Response response)
    {
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        String usuarioId = params.get("userId").toString();
        String movieId = params.get("movieId").toString();
        String cineId = params.get("cineId").toString();

        if(!SessaoDAO.checkSession(movieId,cineId))
        {
            Sessao session = SessaoDAO.add(movieId,cineId);
            return  session.to_Object(session);
        }

        Sessao session = SessaoDAO.getByParams(movieId,cineId);
        return session.to_Object(session);
    }

    public List<Armchair> free(Request request, Response response) throws SQLException, ClassNotFoundException {

        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        String sessionId = params.get("sessionId").toString();

        return SessaoDAO.getArmChairFree(sessionId);
    }
}
