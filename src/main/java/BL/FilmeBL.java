package BL;

import DAO.FilmeDAO;
import Entities.Cinema;
import Entities.Filme;
import Enums.Categoria;
import Enums.Permissao;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    public static JsonObject add(Request req, Response res) throws Exception {
        //Recupera a permissao do usuário da sessão
        if(req.session().attribute("userPermission") == Permissao.FULLACESS.toString())
        {
            JsonParser jsonParser = new JsonParser();
            JsonObject params = (JsonObject) jsonParser.parse(req.body());

            JsonObject dados = FilmeDAO.getFilmeByParams(params.get("title").toString(),params.get("year").toString());

            if(dados.has("Error") || dados == null)
            {
                throw new Exception("Movie not found, please try again");
            }

            String name = dados.get("Title").toString().replace("\"","");
            String synopsis = dados.get("Plot").toString().replace("\"","");
            String releaseData = params.get("releaseData").toString().replace("\"","");
            String finalDate = params.get("finalDate").toString().replace("\"","");
            List<Cinema> cineList = new ArrayList<Cinema>();
            cineList.add(CinemaBL.getById(params.get("cinemaId").toString().replace("\"","")));
            List<Categoria> categorylist = compareClass(dados.get("Genre").toString());

            Filme movie = new Filme(name,synopsis,releaseData,finalDate,cineList,categorylist);
            FilmeDAO.add(movie);

            res.status(201);
            return movie.to_Object(movie);
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

    public static JsonObject update(Request req, Response res) throws Exception {

        JsonParser jsonParser = new JsonParser();
        JsonObject params = (JsonObject) jsonParser.parse(req.body());

        Filme newFilme = FilmeDAO.update(params);

        return newFilme.to_Object(newFilme);
    }
}
