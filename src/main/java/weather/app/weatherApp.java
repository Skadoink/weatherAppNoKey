package weather.app;

import java.util.*;

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

    public String[] getWeather(String lat, String lon){

        HttpResponse<JsonNode> response = Unirest.get("https://api.openweathermap.org/data/2.5/onecall?lat=-41.2866&lon=174.7756&exclude=minutely,hourly,daily,alerts&units=metric&appid=54779f31bf92dc32a033fceb6ac2b82d")
        .asJson();
        System.out.println("response.getBody: " + response.getBody());

        JSONObject bodyJsonObject = new JSONObject(response.getBody().getObject());
        JSONArray current = bodyJsonObject.getJSONArray("current");
        for(Object jsonObject : current){
            JSONObject jo = (JSONObject)jsonObject;
            String temp = jo.getString("temp");
            System.out.println("temp: " + temp);
        }

        // String temp = current.getString("temp");
        // System.out.println("temp: " + temp);
        
        // for(Object jsonObject : jsonData){ //object so works in for loop
        //     JSONObject jo = (JSONObject)jsonObject; //convert back
        //     JSONObject current = jo.getJSONObject("current"); //get array inside current
        //     String temp = current.getString("temp");
        //     System.out.println("temp: " + temp);
        // }

        Dictionary<String, String> dict = new Hashtable<String, String>();
        for(int i=0; i<4; i++){
            //maybe use a json array for key values? or dictionary?
            //dict.put("temp", temp);
        }


        //get desired info
        //icon for weather, icon for day or night, temp, condition.
        String[] placeholder = {"Test", "test"};
        return placeholder;
    }
}
