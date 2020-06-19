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
        post("/addUsuario", UsuarioBL::add);
        post("/addFilme", FilmeBL::add);
        post("/login", UsuarioBL::loginBL);
        put("/updateFilme", FilmeBL::update);
        post("/addSessao", SessaoBL::add);
        post("/transacao", TransacaoBL::add);
        post("/addManualTransaction", TransacaoBL::addTransacaoManual);
        get("/countTransacoes/:method/:filter", TransacaoBL::getCountTransacoes);
        get("/totalBySession", TransacaoBL::getTotalBySession);
        delete("/deleteFilme", FilmeBL::delete);
        get("/getAllMovies", FilmeBL::getAllMoviesEnabledBL);
        get("/filmebyId/:id", FilmeBL::getMovieById);
        post("/getDataRelatorios", (request, response) -> { return TransacaoBL.getData(request,response); });
    }
}
