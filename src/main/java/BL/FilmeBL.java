package BL;

import DAO.FilmeDAO;
import Entities.Cinema;
import Entities.Filme;
import Enums.Categoria;
import Enums.Permissao;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class FilmeBL {

    public List<Filme> getMoviesByNameBL()
    {
        return FilmeDAO.getMoviesByNameDAO();
    }
    public List<Filme> getAllMoviesEnabledBL(){ return FilmeDAO.getAllMoviesEnabledDAO();}

    public static Filme add(Request req, Response res) throws Exception {
        //Recupera a permissao do usuário da sessão
        if(req.session().attribute("userPermission") == Permissao.FULLACESS)
        {
            JsonObject dados = FilmeDAO.getFilmeByParams(req, res);

            if(dados.has("Error"))
            {
                throw new Exception("Movie not found, please try again");
            }

            String name = dados.get("Title").toString();
            String synopsis = dados.get("Plot").toString();
            String releaseData = req.attribute("releaseData");
            String finalDate = req.attribute("finalDate");
            List<Cinema> cineList = new ArrayList<Cinema>();
            List<Categoria> categorylist = compareClass(dados.get("Genre").toString());

            Filme movie = new Filme(name,synopsis,releaseData,finalDate,cineList,categorylist);
            FilmeDAO.add(movie, req);

            res.status(201);
            return movie;
        }

        return null;
    }

    private static List<Categoria> compareClass(String moviesCategories)
    {
        List<Categoria> categoriaList = new ArrayList<Categoria>();
        String[] categoriesSplit = moviesCategories.split(",");

        for(String categoria : categoriesSplit){
            switch (categoria.replace(" ","").replace("\"","").trim())
            {
                case "Action":
                    categoriaList.add(Categoria.ACAO);
                    break;
                case "Adventure":
                    categoriaList.add(Categoria.AVENTURA);
                    break;
                case "Comedy":
                    categoriaList.add(Categoria.COMEDIA);
                    break;
                case "Sci-Fi":
                    categoriaList.add(Categoria.FICCAO);
                    break;
                case "Drama":
                    categoriaList.add(Categoria.DRAMA);
                    break;
                case "Horror":
                    categoriaList.add(Categoria.TERROR);
                    break;
            }

        }


        return categoriaList;
    }
}
