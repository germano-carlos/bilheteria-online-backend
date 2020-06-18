package BL;

import DAO.OperadoraDAO;
import DAO.TransacaoDAO;
import Entities.Armchair;
import Entities.Operadora;
import Entities.Transacao;
import Entities.Usuario;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;
import BL.OperadoraBL;
import DAO.FilmeDAO;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransacaoBL {

    //Adiciona transacao
    public static JsonObject add(Request request, Response response) throws SQLException, ClassNotFoundException {
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        //AdicionaOperadora
        OperadoraBL.add(request, response);
        int operadoraId = Integer.parseInt(OperadoraDAO.lastInsertId());

        //AdicionaTransacao
        String compradorId = params.get("userId").toString().replace("\"", "");
        int sessaoId = Integer.parseInt(params.get("sessaoId").toString().replace("\"", ""));
        int qtIngressos = Integer.parseInt(params.get("qtIngressos").toString().replace("\"", ""));
        double valorIngresso = Double.parseDouble(params.get("valorIngresso").toString().replace("\"", ""));
        double valorTotal = Double.parseDouble(params.get("valorTotal").toString().replace("\"", ""));
        boolean aprovado = true;

        Transacao transacao = new Transacao(compradorId, sessaoId, qtIngressos, valorIngresso, valorTotal, operadoraId, aprovado);
        TransacaoDAO.add(transacao);

        String[] chairs = params.get("chairs").toString().replace("\"", "").split(",");
        SessaoBL.addUserInSession(compradorId, chairs, sessaoId);

        Usuario user = UsuarioBL.getByCPF(compradorId);
        transacao.setPagador(user.getName());

        List<Armchair> cadeiras = new ArrayList<Armchair>();
        for (int i = 0; i < chairs.length; i++) {
            cadeiras.add(new Armchair(chairs[i]));
        }

        transacao.setChairs(cadeiras);
        response.status(201);

        return transacao.to_Object(transacao);
    }

    public static void getTrasacoesByUser(Request request, Response response) {
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        int userId = Integer.parseInt(params.get("userId").toString().replace("\"", ""));
        //Retorna objeto com as transacoes
        //return TransacaoDAO.trasacoesByUser(userId);
    }

    public static void getTransacoes(Request request, Response response) {
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        //Retorna objeto com as transacoes
//        return new Gson().toJson(TransacaoDAO.transacoes());
    }

    public static String getCountTransacoes(Request request, Response response) {
        String method = request.params(":method");
        String filter = request.params(":filter");

        //Retorna objeto com as transacoes
        return TransacaoDAO.countTransacoes(method, filter);
    }

    public static String getTotalBySession(Request request, Response response) {
        return new Gson().toJson(TransacaoDAO.getTotalBySession());
    }

    public static String addTransacaoManual(Request request, Response response) {
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        double valor = Double.parseDouble(params.get("venda").toString().replace("\"", ""));
        String sessao = params.get("sessao").toString().replace("\"", "");
        String qtdeIngressos = params.get("qtdeIngressos").toString().replace("\"", "");

        return TransacaoDAO.addTransacaoManual(valor, sessao, qtdeIngressos);
    }
}
