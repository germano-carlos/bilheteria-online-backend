package DAO;

import Entities.DadosGrafico;
import Entities.Transacao;
import Utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.Request;
import spark.Response;

public class TransacaoDAO {

    public static void add(Transacao transacao) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        try {
            DB connection = new DB();
            String sql = "INSERT INTO transaction (user_id, sessao_id, qt_ingresso, valor_ingressos, valor_total, operadora_id, status, data_transacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, transacao.getCompradorId());
            stmt.setString(2, Integer.toString(transacao.getSessaoId()));
            stmt.setString(3, Integer.toString(transacao.getQtIngressos()));
            stmt.setString(4, Double.toString(transacao.getValorIngresso()));
            stmt.setString(5, Double.toString(transacao.getValorTotal()));
            stmt.setString(6, Integer.toString(transacao.getOperadoraId()));
            stmt.setString(7, "1");
            stmt.setString(8, dateString);
            stmt.execute();
            stmt.close();
            connection.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void trasacoesByUser(int userId) {
        try {
            DB connection = new DB();
            String sql = "selext * from transaction where ";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void transacoes() {
        try {
            DB connection = new DB();
            String query = "select * from transaction";
            Statement stmt = connection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            stmt.close();
            connection.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String countTransacoes(String method, String filter) {
        try {
            DB connection = new DB();

            String querySuffix = "";

            if (filter.equals("site")) {
                querySuffix = "WHERE user_id != 9999";
            } else {
                querySuffix = "WHERE user_id = 9999";
            }

            String methodStr = "";

            if (method.equals("count")) {
                methodStr = "COUNT(*)";
            } else {
                methodStr = "SUM(valor_total)";
            }

            String query = "SELECT " + methodStr + " as total FROM transaction " + querySuffix;
            PreparedStatement stmt = connection.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            Integer total = 0;
            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }

            stmt.close();
            connection.closeConnection();
            return total.toString();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static List<DadosGrafico> getTotalBySession() {
        List<DadosGrafico> totalBySession = new ArrayList<>();

        try {
            DB connection = new DB();

            String query = "SELECT session.id, SUM(CASE WHEN user_id = 9999 THEN valor_total ELSE 0 END) as total_bilheteria, SUM(CASE WHEN user_id != 9999 THEN valor_total ELSE 0 END) as total_site FROM session LEFT JOIN transaction ON session.id = transaction.sessao_id GROUP BY id";
            PreparedStatement stmt = connection.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            while (rs != null && rs.next()) {
                totalBySession.add(new DadosGrafico(rs.getString("id"),
                        rs.getDouble("total_bilheteria"),
                        rs.getDouble("total_site")));
            }

            stmt.close();
            connection.closeConnection();
            return totalBySession;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return totalBySession;
    }

    public static String addTransacaoManual(double valor, String sessao, String qtdeIngressos) {
        try {
            DB connection = new DB();

            String sql = "INSERT INTO transaction (user_id, sessao_id, qt_ingresso, valor_ingressos, valor_total, operadora_id, status) VALUES (?, ?, ?, 0, ?, 0, 1)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setInt(1, 9999);
            stmt.setString(2, sessao);
            stmt.setString(3, qtdeIngressos);
            stmt.setDouble(4, valor);
            stmt.execute();

            stmt.close();
            connection.closeConnection();
            return "";
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static String getData(Request request, Response response) throws SQLException, ClassNotFoundException {

        List<String> jsonStringParcial = new ArrayList<String>();
        List<String> jsonStringParcial1 = new ArrayList<String>();

        DB connection = new DB();
        String query = "SELECT count(id) as qtde, data_transacao as data_transacao " +
                        "FROM `alinkdig_bilheteria-digital`.transaction " +
                        "WHERE user_id != '9999'" +
                        "GROUP BY DAY(data_transacao)";

        PreparedStatement stmt = connection.getConnection().prepareStatement(query);
        ResultSet rs = stmt.executeQuery(query);

        while (rs != null && rs.next()) {
            jsonStringParcial.add("[\"" + rs.getString("data_transacao") + "\", " + rs.getString("qtde") +"]");
        }

        query = "SELECT count(id) as qtde, data_transacao as data_transacao " +
                "FROM `alinkdig_bilheteria-digital`.transaction " +
                "WHERE user_id = '9999'" +
                "GROUP BY DAY(data_transacao)";

        stmt = connection.getConnection().prepareStatement(query);
        rs = stmt.executeQuery(query);


        while (rs != null && rs.next()) {
            jsonStringParcial1.add("[\"" + rs.getString("data_transacao") + "\", " + rs.getString("qtde") +"]");
        }

        connection.closeConnection();

        String concat5 = "";
        for(int i=0;i<jsonStringParcial1.size();i++)
        {
            if(i != jsonStringParcial1.size() - 1)
            {
                concat5 += jsonStringParcial1.get(i).toString() + ",";
            }
            else
            {
                concat5 += jsonStringParcial1.get(i).toString();
            }
        }

        String concat6 = "";
        for(int i=0;i<jsonStringParcial.size();i++)
        {
            if(i != jsonStringParcial.size() - 1)
            {
                concat6 += jsonStringParcial.get(i).toString() + ",";
            }
            else
            {
                concat6 += jsonStringParcial.get(i).toString();
            }
        }

        String teste  = "{\"label\":\""+"Fisico"+"\"," +
                "\"data\":["+concat5+"]}";
        String teste2 = "{\"label\":\""+"Online"+"\"," +
                "\"data\":["+concat6+"]}";

        String jsonString = "{\"data\":["+teste+", "+ teste2 + "] }";
        return jsonString;
    }
}
