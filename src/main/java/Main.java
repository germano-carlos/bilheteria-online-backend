import BL.CinemaBL;
import Entities.Cinema;
import Utils.Rotas;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static spark.Spark.init;

public class Main {

    public static void main(String[] args) throws Exception {
        init();
        Cors cors = new Cors();
        Rotas routes = new Rotas();
    }
}

