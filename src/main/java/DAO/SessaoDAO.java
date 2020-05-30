package DAO;

import Entities.Cinema;
import Entities.Sessao;
import Utils.DB;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessaoDAO {

    public Sessao add()
    {
        Sessao s = new Sessao();

        return s;
    }

    public static String[] getArmChairOccuped(int sessionId) throws SQLException, ClassNotFoundException {


        int contador = 0;
        try{
            DB connection = new DB();

            //Buscando o id do Filme Inserido
            String sql = "SELECT count(armchair_id) count FROM session_user_armchair WHERE session_id='"+sessionId+"'";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);

            if(rs != null && rs.next())
            {
                contador = rs.getInt("count");
            }

            sql = "SELECT armchair_id FROM session_user_armchair WHERE session_id='"+sessionId+"'";
            stmt = connection.getConnection().prepareStatement(sql);
            rs=stmt.executeQuery(sql);

            String[] armChair = new String[contador];
            int i =0;
            while ( rs.next() ) {
                armChair[i] = rs.getString("armchair_id");
                i++;
            }
            return armChair;
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
