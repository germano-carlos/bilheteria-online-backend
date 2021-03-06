package DAO;

import Entities.Cinema;
import Entities.Filme;
import Enums.Categoria;
import Utils.Api;
import Utils.DB;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FilmeDAO {
    public static List<Filme> getMoviesByNameDAO()
    {
        List<Filme> moviesList = new ArrayList<Filme>();
        //Criar chamada GET

        return moviesList;
    }

    public static List<Filme> getAllMoviesEnabledDAO() throws SQLException, ClassNotFoundException {
        List<Filme> moviesList = new ArrayList<Filme>();

        //Inicializo Conexão
        DB Connection = new DB();
        //Realiza consulta
        Statement stmt = Connection.getConnection().createStatement();
        String sql = "SELECT * FROM movie m " +
                " join cine_movie cm on cm.movie_id = m.id " +
                " where now() between release_data and final_date ";

        ResultSet rs=stmt.executeQuery(sql);
        while(rs != null && rs.next()) {
            moviesList.add(new Filme(rs.getString("name"),
                                     rs.getString("synopsis"),
                                     rs.getString("release_data"),
                                     rs.getString("final_date"),
                                    null,
                                 null,
                                     rs.getString("poster"),
                                     rs.getInt("id"),
                                     rs.getString("carousel")));
        }


        //Adiciona a categoria a cada um dos filmes da lista
        for(int i =0; i< moviesList.size(); i++)
        {
            List<Categoria> categoryList = new ArrayList<Categoria>();
            int id = moviesList.get(i).getId();
            Statement stmt2 = Connection.getConnection().createStatement();
            String sql2 = "SELECT distinct * FROM category c " +
                            "join movie_category mc on mc.category_id = c.id " +
                            "where movie_id ='" + moviesList.get(i).getId() + "'";
            ResultSet rs2=stmt.executeQuery(sql2);

            while(rs2.next())
            {
                categoryList.add(compareClassBD(rs2.getString("category_id")));
            }

            moviesList.get(i).setCategoryList(categoryList);
        }

        Connection.closeConnection();
        return moviesList;
    }

    public static JsonObject getFilmeByParams(String title, String year)
    {
        title = title.replace("\"","").replace(" ","+");
        year = year.replace("\"","");

        JsonObject json = Api.__call("GET",title, year);

        return json;
    }

    public static void add(Filme movie)
    {
        try{
            DB connection = new DB();
            //Inserindo um filme na tabela movie
            String sql = "INSERT INTO movie (name, synopsis, poster, runtime) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, movie.getName());
            stmt.setString(2, movie.getSynopsis());
            stmt.setString(3, movie.getPoster());
            stmt.setString(4, movie.getRuntime());
            stmt.execute();

            //Buscando o id do Filme Inserido
            sql = "SELECT MAX(id) as id FROM movie";
            String movieId = " ";
            ResultSet rs=stmt.executeQuery(sql);
            if(rs != null && rs.next()) {
                movieId = rs.getString("id");
            }

            Cinema cine = movie.getCineList().get(0);
            String cineId = Integer.toString(cine.getId());

            sql = "INSERT INTO cine_movie (cine_id, movie_id, release_data, final_date) VALUES (?, ?, ?, ?)";
            stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, cineId);//request.attribute("cine_id"));
            stmt.setString(2, movieId);
            stmt.setString(3, movie.getReleaseData());
            stmt.setString(4, movie.getFinalDate());
            stmt.execute();

            String inteiroM = "1";
            //Inserir as Categorias deste Filme
            for (int i = 0; i < movie.getCategoryList().size(); i++)
            {
                sql = "INSERT INTO movie_category (movie_id, category_id) VALUES (? , ?)";
                stmt = connection.getConnection().prepareStatement(sql);
                stmt.setString(1, movieId);
                stmt.setString(2, movie.getCategoryList().get(i).getId());
                stmt.execute();
            }

            stmt.close();
            connection.closeConnection();

        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }
    }

    public static Filme update(JsonObject params) throws Exception {
        DB connection = new DB();

        String updateReleaseDate = " ";
        String updateFinalDate = " ";
        String paramsReleaseDate = params.get("releaseData").toString().replace("\"","");
        String paramsfinalDate = params.get("finalDate").toString().replace("\"","");;
        int movieId = params.get("movieId").getAsInt();
        int cineId = params.get("cineId").getAsInt();

        //Valido os parametros recebidos
        if(paramsfinalDate.isEmpty() && paramsReleaseDate.isEmpty())
            throw new Exception("Nenhum dos valores podem ser vazios !");
        if(!paramsReleaseDate.isEmpty())
            updateReleaseDate = "release_data = '"+paramsReleaseDate+"'";
        if(!paramsfinalDate.isEmpty())
            updateFinalDate = ",final_date = '"+paramsfinalDate+"'";
        if(paramsReleaseDate.isEmpty() && !paramsfinalDate.isEmpty())
            updateFinalDate = updateFinalDate.replace(",","");

        String sql = "UPDATE CINE_MOVIE "
                    + "SET " + updateReleaseDate
                             + updateFinalDate +
                     " WHERE cine_id = '"+cineId+"' " +
                     "AND movie_id = '"+movieId+"'";

        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
        stmt.execute();

        Filme filme = getByParams(movieId, cineId);

        if(!paramsfinalDate.isEmpty())
            filme.setReleaseData(paramsfinalDate);
        if(!paramsReleaseDate.isEmpty())
            filme.setFinalDate(paramsReleaseDate);

        return filme;
    }
    public static Filme getByParams(int movieId, int cineId) throws SQLException, ClassNotFoundException {
        Filme movie = new Filme();

        //Inicializo Conexão
        DB Connection = new DB();
        //Realiza consulta
        Statement stmt = Connection.getConnection().createStatement();
        String sql = "SELECT * FROM movie m " +
                    " join cine_movie cm on cm.movie_id = m.id " +
                    " where cine_id ="+cineId+" and movie_id ="+movieId+"";

        ResultSet rs=stmt.executeQuery(sql);
        if(rs != null && rs.next()) {
            //Instancia atributos do Usuário que está logando
            movie.setSynopsis(rs.getString("synopsis"));
            movie.setFinalDate(rs.getString("final_date"));
            movie.setReleaseData(rs.getString("release_data"));
            movie.setName(rs.getString("name"));
            movie.setId(rs.getInt("id"));
            movie.setPoster("poster");
        }

        return  movie;
    }

    public static Filme getById(int movieId) throws SQLException, ClassNotFoundException {
        Filme movie = new Filme();

        //Inicializo Conexão
        DB Connection = new DB();
        //Realiza consulta
        Statement stmt = Connection.getConnection().createStatement();
        String sql = "SELECT * FROM movie where id = " + movieId;

        ResultSet rs=stmt.executeQuery(sql);
        if(rs != null && rs.next()) {
            movie.setName(rs.getString("name"));
            movie.setPoster(rs.getString("poster"));
            movie.setId(movieId);
            movie.setSynopsis(rs.getString("synopsis"));
            movie.setRuntime(rs.getString("runtime"));
        }

        List<Categoria> categoryList = new ArrayList<Categoria>();
        int id = movieId;
        Statement stmt2 = Connection.getConnection().createStatement();
        String sql2 = "SELECT distinct * FROM category c " +
                "join movie_category mc on mc.category_id = c.id " +
                "where movie_id ='" + movieId + "'";
        ResultSet rs2=stmt.executeQuery(sql2);

        while(rs2.next())
        {
            categoryList.add(compareClassBD(rs2.getString("category_id")));
        }

        movie.setCategoryList(categoryList);



        return  movie;
    }

    public static void delete(int movieId) throws SQLException, ClassNotFoundException {
        Filme movie = new Filme();

        //Inicializo Conexão
        DB Connection = new DB();
        //Realiza consulta
        String sql = "delete FROM movie where id = " + movieId;
        PreparedStatement stmt = Connection.getConnection().prepareStatement(sql);
        stmt.execute();

        Connection.closeConnection();
    }

    public static Categoria compareClassBD(String categoryId)
    {
        if(categoryId.equals("1"))
            return Categoria.ACAO;
        if(categoryId.equals("2"))
            return Categoria.ANIMACAO;
        if(categoryId.equals("3"))
            return Categoria.AVENTURA;
        if(categoryId.equals("4"))
            return Categoria.COMEDIA;
        if(categoryId.equals("5"))
            return Categoria.DOCUMENTARIO;
        if(categoryId.equals("6"))
            return Categoria.FANTASIA;
        if(categoryId.equals("7"))
            return Categoria.FAROESTE;
        if(categoryId.equals("8"))
            return Categoria.FICCAO;
        if(categoryId.equals("9"))
            return Categoria.GUERRA;
        if(categoryId.equals("10"))
            return Categoria.MUSICAL;
        if(categoryId.equals("11"))
            return Categoria.ROMANCE;
        if(categoryId.equals("12"))
            return Categoria.SUSPENSE;

        return Categoria.TERROR;

    }
}
