package DAO;

import Entities.Cinema;
import Enums.SituacaoCinema;
import Utils.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CinemaDAO {
    public static List<Cinema> getCineByMoviesDAO()
    {
        List<Cinema> cineList = new ArrayList<Cinema>();
        //Criar chamada GET

        return cineList;
    }

    public static List<Cinema> getAllCineEnabledDAO()
    {
        List<Cinema> cineList = new ArrayList<Cinema>();
        //Criar chamada GET

        return cineList;
    }

    public static Cinema getById(String cineId) throws SQLException, ClassNotFoundException {

        Cinema cine = new Cinema();
        //Inicializo Conexão
        DB Connection = new DB();
        //Realiza consulta
        Statement stmt = Connection.getConnection().createStatement();
        String sql = "select * from cine where id='"+cineId+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs != null && rs.next()) {
            cine.setActive(SituacaoCinema.ATIVO);
            cine.setAdress(rs.getString("adress"));
            cine.setCnpj(rs.getString("cnpj"));
            cine.setInitials(rs.getString("initials"));
            cine.setName(rs.getString("name"));
            cine.setId(rs.getInt("id"));
        }
        //Finaliza a conexão
        Connection.closeConnection();

        return cine;
    }
}
