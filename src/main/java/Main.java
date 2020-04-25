import Enums.Categoria;

import java.util.Arrays;

import static spark.Spark.init;

public class Main {
    public static void main(String[] args) {

        init();
        Rotas routes = new Rotas();

        Categoria[] teste = Categoria.values();

        Arrays.asList( teste ).forEach(blabla -> {
            System.out.print( blabla.idCategoria);
            System.out.print( " = " );
                }
        );

    }
}