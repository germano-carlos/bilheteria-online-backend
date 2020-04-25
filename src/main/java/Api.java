import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

import org.json.simple.JSONObject;

public class Api {

    public static String[] __call(String method, String title, String year)
    {
        String[] pairs;
        try {
            if(year != null)
                year = "&y="+year;

            String url = "http://www.omdbapi.com/?apikey=88151055&t="+title+""+year;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod(method);
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            }

            String JsonString = convertStreamToString(conn.getInputStream());
            JsonString = JsonString.substring(1, JsonString.length()-1);
            JsonString = JsonString.replace("{","");
            JsonString = JsonString.replace("}","");
            JsonString = JsonString.replace(":",",");

            pairs = JsonString.split(",");

            //BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            conn.disconnect();
            return pairs;

        } catch (IOException ex) {
            Logger.getLogger(Api.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
