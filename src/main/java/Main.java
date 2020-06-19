import Utils.Rotas;

import static spark.Spark.get;
import static spark.Spark.port;

public class Main {

    public static void main(String[] args) throws Exception {
        port(getHerokuAssignedPort());
        get("/hello", (req, res) -> "Hello Heroku World");
        Cors cors = new Cors();
        Rotas routes = new Rotas();
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}

