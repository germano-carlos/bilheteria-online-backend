import static spark.Spark.get;

public class Rotas {

    public Rotas()
    {
        get("/", (req, res) -> "Hello World");
        get("/1", (req, res) -> "Hello2 World");
        get("/2", (req, res) -> "Hello3 World");
        get("/3", (req, res) -> "Hello4 World");
        get("/4", (req, res) -> "Hello5 World");
        get("/5", (req, res) -> "Hello6 World");
    }
}
