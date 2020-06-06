package BL;

import DAO.OperadoraDAO;
import Entities.Operadora;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

public class OperadoraBL {
    public static void add(Request request, Response response){
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        String nomeCartao = params.get("nomeCartao").toString().replace("\"","");
        String numeroCartao = params.get("numeroCartao").toString().replace("\"","");
        String validade = params.get("validade").toString().replace("\"","");
        String cvv = params.get("cvv").toString().replace("\"","");
        Operadora operadora = new Operadora(nomeCartao, numeroCartao, validade, cvv);
        OperadoraDAO.add(operadora);
    }
}

