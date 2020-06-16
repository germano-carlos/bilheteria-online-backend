package DAO;

import Entities.Transacao;
import Utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TransacaoDAO {

    public static void add(Transacao transacao) {
        try {
            DB connection = new DB();
            String sql = "INSERT INTO transaction (user_id, sessao_id, qt_ingresso, valor_ingressos, valor_total, operadora_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, transacao.getCompradorId());
            stmt.setString(2, Integer.toString(transacao.getSessaoId()));
            stmt.setString(3, Integer.toString(transacao.getQtIngressos()));
            stmt.setString(4, Double.toString(transacao.getValorIngresso()));
            stmt.setString(5, Double.toString(transacao.getValorTotal()));
            stmt.setString(6, Integer.toString(transacao.getOperadoraId()));
            stmt.setString(7, "1");
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

    public static String countTransacoes(String method) {
        try {
            DB connection = new DB();

            String querySuffix = "";

            if (method.equals("site")) {
                querySuffix = "WHERE user_id != 9999";
            }
            else {
                querySuffix = "WHERE user_id = 9999";
            }

            String query = "SELECT COUNT(*) as total FROM transaction " + querySuffix;
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
}
