package DAO;

import Entities.Filme;
import Utils.Api;
import Utils.DB;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    public static List<Filme> getMoviesByNameDAO()
    {
        List<Filme> moviesList = new ArrayList<Filme>();
        //Criar chamada GET

        return moviesList;
    }

    public static List<Filme> getAllMoviesEnabledDAO()
    {
        List<Filme> moviesList = new ArrayList<Filme>();
        //Criar chamada GET

        return moviesList;
    }

    public static JsonObject getFilmeByParams(String title, String year)
    {
        title = title.replace("\"","").replace(" ","+");
        year = year.replace("\"","");

        JsonObject json = Api.__call("GET",title, year);

        return json;
    }

    public static void add(Filme movie, Request request)
    {
        try{
            DB connection = new DB();
            String sql = "INSERT INTO movie (name, synopsis) VALUES (?, ?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, movie.getName());
            stmt.setString(2, movie.getSynopsis());
            stmt.execute();

            //Descobrir como buscar o Id após a inserção
            String inteiro = "1";
            sql = "INSERT INTO cine_movie (cine_id, movie_id, release_data, final_date) VALUES (?, ?, ?, ?)";
            stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, "1");//request.attribute("cine_id"));
            stmt.setString(2, inteiro);
            stmt.setString(3, movie.getReleaseData());
            stmt.setString(4, movie.getFinalDate());
            stmt.execute();

            String inteiroM = "1";
            //Inserir as Categorias deste Filme
            for (int i = 0; i < movie.getCategoryList().size(); i++)
            {
                sql = "INSERT INTO movie_category (movie_id, category_id) VALUES (? , ?)";
                stmt = connection.getConnection().prepareStatement(sql);
                stmt.setString(1, inteiroM);
                stmt.setString(2, movie.getCategoryList().get(i).getId());
                stmt.execute();
            }

            stmt.close();
            connection.closeConnection();

        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }
    }
}
