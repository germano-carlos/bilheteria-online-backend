import Utils.Rotas;

import static spark.Spark.init;

public class Main {

    public static void main(String[] args) throws Exception {
        init();
        Cors cors = new Cors();
        Rotas routes = new Rotas();
    }
}

