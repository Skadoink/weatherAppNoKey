package weather.app;


import kong.unirest.HttpResponse; //for handling a response
import kong.unirest.JsonNode; //for assigning a JSON-type to it
import kong.unirest.Unirest; //for making a GET request

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class testApp {
    public static void main(String[] args) throws Exception
    {
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

    JsonElement je = JsonParser.parseString(response.getBody().toString());
    JsonObject jo = je.getAsJsonObject();
    JsonArray translations = jo.getAsJsonObject("data").getAsJsonArray("translations");

    System.out.println(translations.toString());

    for (JsonElement jsonElement : translations) {
            String text = jsonElement.getAsJsonObject().get("translatedText").getAsString();
            System.out.println(text);
        }
    }
}
