package BL;

import DAO.FilmeDAO;
import Entities.Filme;

import java.util.List;

public class FilmeBL {
    public List<Filme> getMoviesByNameBL()
    {
        return FilmeDAO.getMoviesByNameDAO();
    }
    public List<Filme> getAllMoviesEnabledBL(){ return FilmeDAO.getAllMoviesEnabledDAO();}
}
