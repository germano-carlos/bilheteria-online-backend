package BL;

import DAO.CinemaDAO;
import Entities.Cinema;
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
}
