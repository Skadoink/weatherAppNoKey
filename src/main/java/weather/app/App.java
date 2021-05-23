package weather.app;

import kong.unirest.HttpResponse; //for handling a response
import kong.unirest.JsonNode; //for assigning a JSON-type to it
import kong.unirest.json.*;
import kong.unirest.Unirest; //for making a GET request


public class App {
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

    JSONObject bodyJsonObject = response.getBody().getObject(); //gets JsonObject from response body

    JSONArray translations = bodyJsonObject.getJSONObject("data").getJSONArray("translations"); //make the translations iterable so several translations can be printed. 

    System.out.println(translations.toString());

    for (Object jsonObject : translations) //Only Object compatible with for loop
        {
            JSONObject jo = (JSONObject)jsonObject; //Convert into JsonObject so following processes can work
            String text = jo.getString("translatedText"); //get the translated text
            System.out.println(text);
        }
    }
}