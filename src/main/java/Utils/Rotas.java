package Utils;

import BL.*;

import static spark.Spark.*;

public class Rotas {

    public Rotas()
    {
        get("/", (req, res) -> "Hello World");
        post("/addUsuario", UsuarioBL::add);
        post("/addFilme", FilmeBL::add);
    }
}
