package BL;

import DAO.CinemaDAO;
import Entities.Cinema;
import Entities.Usuario;
import Enums.Permissao;
import Enums.SituacaoCinema;
import Utils.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CinemaBL {

    public List<Cinema> getCineByMoviesBL()
    {
        return CinemaDAO.getCineByMoviesDAO();
    }
    public List<Cinema> getAllCineEnabledBL()
    {
        return CinemaDAO.getAllCineEnabledDAO();
    }

    public static Cinema getById(String cineId) throws SQLException, ClassNotFoundException {
        return CinemaDAO.getById(cineId);
    }

    public static String getByUserId(String cpf) throws SQLException, ClassNotFoundException {
        return CinemaDAO.getByUserId(cpf);
    }

}
