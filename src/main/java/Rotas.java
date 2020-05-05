import BL.*;

import static spark.Spark.*;

public class Rotas {

    public Rotas()
    {
        get("/", (req, res) -> "Hello World");
        post("/addUsuario", (req, res) -> UsuarioBL.add(req, res));
    }
}
