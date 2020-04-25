import static spark.Spark.init;

public class Main {
    public static void main(String[] args) {
        init();
        Rotas routes = new Rotas();

        String[] teste = Api.__call("GET","sonic","2020");
        int i = 0;
    }
}
