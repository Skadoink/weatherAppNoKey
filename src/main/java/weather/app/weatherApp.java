package weather.app;

import java.util.ArrayList;
import java.util.Arrays;

import kong.unirest.HttpResponse; //for handling a response
import kong.unirest.JsonNode; //for assigning a JSON-type to it
import kong.unirest.json.*;
import kong.unirest.Unirest; //for making a GET request

public class weatherApp {
    public String[] getLocations(String input){
        HttpResponse<JsonNode> response = Unirest.get("http://api.openweathermap.org/geo/1.0/direct?q=" + input + "&limit=6&appid=54779f31bf92dc32a033fceb6ac2b82d")
        .asJson();
        System.out.println("response.getBody: " + response.getBody());
       
        //Make a JSON array out of the response body converted to string
        JSONArray jsonLocations = new JSONArray(response.getBody().toString());
        ArrayList<String> al = new ArrayList<String>(); //to be added to with locations
        String text = null;
        for(Object jsonObject : jsonLocations){
            System.out.println(jsonObject);
            JSONObject jo = (JSONObject)jsonObject;
            String name = jo.getString("name");
            String country = jo.getString("country");
            String lon = jo.getString("lon");
            String lat = jo.getString("lat");
            String state = ""; //empty unless state exists
            if(jo.has("state")){
                state = (jo.getString("state") + ", "); //only want comma if state exists
            }
            text = (name + ", " + state + country + ", " + lon + ", " + lat); 
            al.add(text); 
        }
        String[] arr = al.toArray(new String[0]); //convert arraylist to array


        return arr;

    }
}
