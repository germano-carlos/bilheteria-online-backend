import spark.route.Routes;

import static spark.Spark.get;
import static spark.Spark.init;

public class Rotas {

    public Rotas()
    {
        get("/", (req, res) -> "Hello World");
        get("/teste", (req, res) -> "Hello2 World");
        get("/lala", (req, res) -> "Hello3 World");
        get("/vrau", (req, res) -> "Hello4 World");
        get("/katiau", (req, res) -> "Hello5 World");
        get("/limbo", (req, res) -> "Hello6 World");

    }
}
