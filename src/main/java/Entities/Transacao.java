package Entities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Transacao {
    private int compradorId;
    private int sessaoId;
    private int qtIngressos;
    private double valorIngresso;
    private double valorTotal;
    private int operadoraId;
    private boolean aprovado;

    public Transacao(int compradorId, int sessaoId, int qtIngressos, double valorIngresso, double valorTotal, int operadoraId, boolean aprovado) {
        this.compradorId = compradorId;
        this.sessaoId = sessaoId;
        this.qtIngressos = qtIngressos;
        this.valorIngresso = valorIngresso;
        this.valorTotal = valorTotal;
        this.operadoraId = operadoraId;
        this.aprovado = aprovado;
    }

    public void setCompradorId(int compradorId) {
        this.compradorId = compradorId;
    }

    public void setSessaoId(int sessaoId) {
        this.sessaoId = sessaoId;
    }

    public void setQtIngressos(int qtIngressos) {
        this.qtIngressos = qtIngressos;
    }

    public void setValorIngresso(double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public int getSessaoId() {
        return sessaoId;
    }

    public int getQtIngressos() {
        return qtIngressos;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public int getCompradorId() {
        return compradorId;
    }

    public JsonObject to_Object(Transacao transaction)
    {
        Gson gson = new Gson();
        String json = gson.toJson(transaction);

        JsonParser jsonParser = new JsonParser();
        JsonObject object = (JsonObject) jsonParser.parse(json);

        return object;
    }
}
