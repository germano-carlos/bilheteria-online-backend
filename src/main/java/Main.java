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

/*
        try {
            String url = "https://opensky-network.org/api/states/all?lamin=45.8389&lomin=5.9962&lamax=47.8229&lomax=10.5226";

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output += line;
            }

            conn.disconnect();

            Gson gson = new Gson();
            Dados dados = gson.fromJson(new String(output.getBytes()), Dados.class);

            System.out.println("TIME: " + dados.getTime());
            System.out.println("STATES: " + Arrays.toString(dados.getStates()[0]));

        } catch (IOException ex) {
            Logger.getLogger(APIRest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public class Dados {

        private String time;
        private String[][] states;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String[][] getStates() {
            return states;
        }

        public void setStates(String[][] states) {
            this.states = states;
        }*/

    }
}
