import java.rmi.*;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Client{

public static void main(String args[]){
try{
System.out.print("Enter the location (in words): ");
Scanner scanner = new Scanner(System.in);
String userLocation = scanner.nextLine();
Weather stub=(Weather)Naming.lookup("rmi://localhost/fetchWeather");
String jsonString = stub.fetchWeather(userLocation);
//System.out.println(jsonString);


JSONObject jsonObject = new JSONObject(jsonString);
JSONObject cityObject = jsonObject.getJSONObject("city");
String cityName = cityObject.getString("name");
String countryName = cityObject.getString("country");

JSONArray listArray = jsonObject.getJSONArray("list");
JSONObject tempObject = listArray.getJSONObject(0).getJSONObject("temp");

String dayTemp = tempObject.getString("day");
String minTemp = tempObject.getString("min");
String maxTemp = tempObject.getString("max");
String nightTemp = tempObject.getString("night");
String eveTemp = tempObject.getString("eve");
String mornTemp = tempObject.getString("morn");

String pressure = listArray.getJSONObject(0).getString("pressure");
String humidity = listArray.getJSONObject(0).getString("humidity");

JSONArray weatherArray = listArray.getJSONObject(0).getJSONArray("weather");
String description = weatherArray.getJSONObject(0).getString("description");


System.out.println("Location: " + cityName + ", " + countryName);

System.out.println("Weather description: " + description);

System.out.println("Temperature:");
System.out.println("\tDay: " + dayTemp + "\u00b0 C");
System.out.println("\tMin: " + minTemp + "\u00b0 C");
System.out.println("\tMax: " + maxTemp + "\u00b0 C");
System.out.println("\tNight: " + nightTemp + "\u00b0 C");
System.out.println("\tEvening: " + eveTemp + "\u00b0 C");
System.out.println("\tMorning: " + mornTemp + "\u00b0 C");

System.out.println("Pressure: " + pressure + " hPa");
System.out.println("Humidity: " + humidity + " %");



}catch(Exception e){System.out.println(e);}
}

}
