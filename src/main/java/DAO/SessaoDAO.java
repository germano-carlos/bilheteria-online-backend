package DAO;

import Entities.Armchair;
import Entities.Cinema;
import Entities.Sessao;
import Enums.SituacaoSessao;
import Utils.DB;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {

    public static Sessao add(String cineId, String movieId)
    {
        Sessao s = new Sessao();

        try{
            DB connection = new DB();
            int id = 0;
            String sql = "INSERT INTO session (cine_id, movie_id) VALUES ('"+cineId+"','"+movieId+"')";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);

             sql = "SELECT * FROM session ORDER BY id desc LIMIT 1";
             stmt = connection.getConnection().prepareStatement(sql);
             rs=stmt.executeQuery(sql);

            if ( rs.next() && rs != null) {
                id = rs.getInt("id");
            }

            s.setCineId(rs.getString("cine_id"));
            s.setId(id);
            s.setMovieId(rs.getString("movie_id").toString());
            s.setSituacaoSessao(SituacaoSessao.ATIVO);
            s.setUserArmChair(getArmChairFree(Integer.toString(s.getId())));

            return s;
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }

        return s;
    }

    public static List<Armchair> getArmChairFree(String sessionId) throws SQLException, ClassNotFoundException {
        List<Armchair> armchairs = new ArrayList<Armchair>();
        try{
            DB connection = new DB();

            String sql = "SELECT * FROM chairs WHERE chairs_id not in (" +
                    "SELECT armchair_id from session_user_armchair where session_id='"+sessionId+"')";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while ( rs.next() ) {
                armchairs.add(new Armchair( rs.getString("armchair_id"),
                                            rs.getString("userId"),
                                            rs.getString("cineId")));
            }

            return armchairs;
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static boolean checkSession(String movieId, String cineId)
    {
        boolean create = false;
        try{
            DB connection = new DB();

            String sql = "SELECT COUNT(*) as count FROM session WHERE movieId = '"+movieId+"' and cineId = '"+cineId+"'";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);

            if ( rs.next() && rs != null) {
                if (rs.getString("count") == "0")
                {
                    create = true;
                }
            }

            return create;
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }
        return  create;
    }

    public static Sessao getByParams(String movieId, String cineId)
    {
        Sessao s = new Sessao();
        return s;
    }
}
