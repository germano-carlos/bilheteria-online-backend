package DAO;

import Entities.Operadora;
import Entities.Usuario;
import Utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperadoraDAO {
    public static void add(Operadora operadora){
        String sql;
        try{
            DB connection = new DB();
            sql = "INSERT INTO operator (nickname, card_number, cvv, expiration_date, card_situation) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, operadora.getNickname());
            stmt.setString(2, operadora.getCardNumber());
            stmt.setString(3, operadora.getCvv());
            stmt.setString(4, operadora.getExpirationDate());
            stmt.setString(4, "Valido");
            stmt.execute();

            stmt.close();
            connection.closeConnection();
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }
    }

    public static String lastInsertId(){
        String sql = "SELECT MAX(id) as id FROM movie";
        String operadoraId = "";
        try{
            DB connection = new DB();
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);
            if(rs != null && rs.next()) {
                operadoraId = rs.getString("id");
            }
           } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return operadoraId;
    }
}
