package Utils;

import BL.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.Filter;
import spark.Spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static spark.Spark.*;

public class Rotas {

    public Rotas()
    {
        get("/", (req, res) -> "Hello World");
        post("/addUsuario",  (request, response) -> { return UsuarioBL.add(request,response); });
        post("/addFilme", (request, response) -> { return FilmeBL.add(request,response); });
        post("/login", (request, response) -> { return UsuarioBL.loginBL(request,response); });
        put("/updateFilme", (request, response) -> { return FilmeBL.update(request,response); });
        post("/addSessao", (request, response) -> { return SessaoBL.add(request,response); });
    }
}
