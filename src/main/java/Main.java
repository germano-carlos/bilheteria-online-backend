import BL.UsuarioBL;

import java.sql.SQLException;
import static spark.Spark.init;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        init();
        Rotas routes = new Rotas();
    }
}
