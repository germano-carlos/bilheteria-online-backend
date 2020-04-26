import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Api {

    public static JsonObject __call(String method, String title, String year)
    {
        try {
            if(year != null)
                year = "&y="+year;
            else
                year = "";

            String url = "http://www.omdbapi.com/?apikey=88151055&t="+title+""+year;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod(method);
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            }

            String JsonString = convertStreamToString(conn.getInputStream());

            JsonParser parser = new JsonParser();
            JsonObject obj = (JsonObject) parser.parse(JsonString);

            conn.disconnect();
            return obj;

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
