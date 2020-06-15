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

    public static Sessao add(String movieId , String cineId, String date)
    {
        Sessao s = new Sessao();

        try{
            DB connection = new DB();
            int id = 0;
            String sql = "INSERT INTO  session (cine_id, movie_id, session_situation ,date) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, cineId);
            stmt.setString(2, movieId);
            stmt.setString(3, "1");
            stmt.setString(4, date);
            stmt.execute();

             sql = "SELECT * FROM session ORDER BY id desc LIMIT 1";
             stmt = connection.getConnection().prepareStatement(sql);
             ResultSet rs=stmt.executeQuery(sql);

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

            String sql = "SELECT * FROM chairs WHERE id not in (" +
                    "SELECT armchair_id from session_user_armchair where session_id='"+sessionId+"')";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while ( rs.next() ) {
                armchairs.add(new Armchair(rs.getString("id"), rs.getString("position")));
            }

            return armchairs;
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static boolean checkSession(String movieId, String cineId,String date)
    {
        boolean create = false;
        try{
            DB connection = new DB();

            String sql = "SELECT COUNT(*) as count FROM session WHERE movie_id = '"+movieId+"' and cine_id = '"+cineId+"' and date ='"+date+"'";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);

            if ( rs.next()) {
                String count = rs.getString("count");
                int countInt = Integer.parseInt(count);
                if (!count.isEmpty() && countInt == 0)
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

    public static Sessao getByParams(String movieId, String cineId, String date) throws SQLException, ClassNotFoundException {
        Sessao s = new Sessao();

        DB connection = new DB();

        String sql = "SELECT * FROM session WHERE movie_id = '"+movieId+"' and cine_id = '"+cineId+"' and date ='"+date+"'  LIMIT 1";
        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
        ResultSet rs=stmt.executeQuery(sql);

        if(rs.next())
        {
            s.setCineId(rs.getString("cine_id"));
            s.setId(rs.getInt("id"));
            s.setMovieId(rs.getString("movie_id").toString());
            s.setSituacaoSessao(SituacaoSessao.ATIVO);
            s.setUserArmChair(getArmChairFree(Integer.toString(s.getId())));
        }

        return s;
    }

    public static void addUserInSession(String compradorId, String[] chairs, int sessaoId) throws SQLException, ClassNotFoundException {
        DB connection = new DB();
        for(int i=0; i< chairs.length; i++)
        {
            String sql = "INSERT INTO session_user_armchair (session_id, armchair_id, user_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, Integer.toString(sessaoId));
            stmt.setString(2, chairs[i]);
            stmt.setString(3, compradorId);
            stmt.execute();
        }
        connection.closeConnection();
    }
}
