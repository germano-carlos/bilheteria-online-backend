package Utils;

import BL.FilmeBL;
import BL.SessaoBL;
import BL.TransacaoBL;
import BL.UsuarioBL;

import static spark.Spark.*;

public class Rotas {

    public Rotas()
    {
        get("/", (req, res) -> "Hello World");
        post("/addUsuario",  (request, response) -> { return UsuarioBL.add(request,response); });
        post("/addFilme", (request, response) -> { return FilmeBL.add(request,response); });
        post("/login", (request, response) -> { return UsuarioBL.loginBL(request,response); });
        put("/updateFilme", (request, response) -> { return FilmeBL.update(request,response); });
        post("/addSessao", (request, response) -> { return SessaoBL.add(request,response); });
        post("/transacao", (request, response) -> { return TransacaoBL.add(request,response); });
        delete("/deleteFilme", (request, response) -> { return FilmeBL.delete(request,response); });
        get("/getAllMovies", (request, response) -> { return FilmeBL.getAllMoviesEnabledBL(request,response); });
    }
}
