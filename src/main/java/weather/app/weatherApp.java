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

    public Dictionary<String, String> getWeather(String locationString){

        //get lon and lat from combobox string
        List<String> boxTextList = new ArrayList<String>(Arrays.asList(locationString.split(",")));
        String lon = "";
        String lat = "";
        int counter = 0; //used to figure out if we're up to the lat or the lon in the loop
        for(String eachString : boxTextList){
            try{
                counter += 1;
                if (counter == 1){ //works, but bad efficiency
                    lon = eachString;
                }
                else{
                    lat = eachString;
                }
            }
            catch (NumberFormatException exc){
            }
        }
        lon = lon.substring(1);
        lat = lat.substring(1);
        System.out.println("lon: " + lon + ", lat: " + lat); //checking

        HttpResponse<JsonNode> response = Unirest.get("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=minutely,hourly,daily,alerts&units=metric&appid=APIKEYHERE")
        .asJson();
        System.out.println("response.getBody: " + response.getBody()); //checking
        Dictionary<String, String> dict = new Hashtable<String, String>(); //made dictionary for return values
        JSONObject bodyJsonObject = new JSONObject(response.getBody().getObject());
        System.out.println("bodyJSONObject is: " + bodyJsonObject); //checking

        String temp = bodyJsonObject.getJSONObject("element").getJSONObject("current").getString("temp"); 
        System.out.println("temp: " + temp); //checking
        dict.put("temp", temp);

        JSONArray weather = bodyJsonObject.getJSONObject("element").getJSONObject("current").getJSONArray("weather");
        System.out.println("weather: " + weather);
        for(Object jsonObject : weather){
            System.out.println("jsonObject: " + jsonObject); //checking
            JSONObject jo = (JSONObject)jsonObject;
            String description = jo.getString("description");
            dict.put("description", description);
            String icon = jo.getString("icon");
            dict.put("icon", icon);
        }

        //convert time 
        int dt = bodyJsonObject.getJSONObject("element").getJSONObject("current").getInt("dt");
        int sunrise = bodyJsonObject.getJSONObject("element").getJSONObject("current").getInt("sunrise");
        int sunset = bodyJsonObject.getJSONObject("element").getJSONObject("current").getInt("sunset");
        System.out.println("sunset: " + sunset); //checking
        if(dt >= sunrise && dt <= sunset){ //if daytime
            dict.put("dayOrNight", "day");
        }
        else{
            dict.put("dayOrNight", "night");
        }

        System.out.println("dict" + dict); //checking

        //String[] placeholder = {"Test", "test"};
        return dict;
    }
}
