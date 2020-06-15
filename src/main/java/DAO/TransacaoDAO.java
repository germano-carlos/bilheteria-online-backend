package DAO;

import Entities.Transacao;
import Utils.DB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransacaoDAO {
    public static void add(Transacao transacao)    {
        try{
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
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }
    }

    public static void trasacoesByUser(int userId)    {
        try{
            DB connection = new DB();
            String sql = "selext * from transition where ";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.closeConnection();
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }
    }
}
