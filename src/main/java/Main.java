import Utils.Rotas;

import static spark.Spark.init;

public class Main {

    public static void main(String[] args) throws Exception {
        init();
        Cors cors = new Cors();
        Rotas routes = new Rotas();
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {


    }

}

