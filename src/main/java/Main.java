import BL.FilmeBL;
import Utils.Rotas;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import static spark.Spark.init;

public class Main {

    public static void main(String[] args) throws Exception {
        init();
        Rotas routes = new Rotas();
    }
}
