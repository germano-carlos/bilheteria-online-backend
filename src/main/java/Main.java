import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.init;

public class Main {
    public static void main(String[] args) {
        init();
        Rotas routes = new Rotas();
    }
}
