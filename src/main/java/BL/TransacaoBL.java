package BL;

import DAO.OperadoraDAO;
import DAO.TransacaoDAO;
import Entities.Operadora;
import Entities.Transacao;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

public class TransacaoBL {
    //Adiciona transacao
    public static Transacao add(Request request, Response response){
        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(request.body());

        //AdicionaOperadora
        int operadoraId = addOperadora(params);

        //AdicionaTransacao
        int compradorId = Integer.parseInt(params.get("compradorId").toString().replace("\"",""));
        int sessaoId = Integer.parseInt(params.get("sessaoId").toString().replace("\"",""));
        int qtIngressos = Integer.parseInt(params.get("qtIngressos").toString().replace("\"",""));
        double valorIngresso = Double.parseDouble(params.get("valorIngresso").toString().replace("\"",""));
        double valorTotal = Double.parseDouble(params.get("valorTotal").toString().replace("\"",""));
        boolean aprovado = true;


        Transacao transacao = new Transacao(compradorId, sessaoId, qtIngressos, valorIngresso, valorTotal, operadoraId, aprovado);

        TransacaoDAO.add(transacao);

        response.status(201);

        return transacao;
    }

    //Adiciona Operadora
    public static int addOperadora(JsonObject params){
        String nomeCartao = params.get("nomeCartao").toString().replace("\"","");
        String numeroCartao = params.get("numeroCartao").toString().replace("\"","");
        String validade = params.get("validade").toString().replace("\"","");
        String cvv = params.get("cvv").toString().replace("\"","");
        Operadora operadora = new Operadora(nomeCartao, numeroCartao, validade, cvv);
        int operadoraId = Integer.parseInt(OperadoraDAO.add(operadora));

        return operadoraId;
    }
}
