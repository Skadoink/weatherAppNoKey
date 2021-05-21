package weather.app;

import java.net.URLEncoder;

import kong.unirest.HttpResponse; //for handling a response
import kong.unirest.JsonNode; //for assigning a JSON-type to it
import kong.unirest.Unirest; //for making a GET request

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class App {
        public static void main(String[] args) throws Exception{
        HttpResponse<JsonNode> response = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
            .header("content-type", "application/x-www-form-urlencoded")
            .header("accept-encoding", "application/gzip")
            .header("x-rapidapi-key", "67f8920a8cmshf3a99822b9b0f7dp1f4031jsnea9966c26c65")
            .header("x-rapidapi-host", "google-translate1.p.rapidapi.com")
            .body("q=Hello%2C%20world!&target=fr&source=en")
            .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));
        System.out.println("Translation: " + response.getBody());
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);
        system


        // //Prettifying
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // JsonParser jp = new JsonParser();
        // JsonElement je = jp.parse(response.getBody().toString());
        // String prettyJsonString = gson.toJson(je);
        // System.out.println(prettyJsonString);
        
        // Gson gson = new Gson();
        // JsonElement jsonElement = gson.toJsonTree(response);
        // JsonArray jsonArray = jsonElement.getAsJsonObject().get("data").getAsJsonArray();
        // System.out.println(gson.toJson(jsonArray));


    }
}
