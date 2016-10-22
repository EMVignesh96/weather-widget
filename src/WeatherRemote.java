
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherRemote extends UnicastRemoteObject implements Weather {

    WeatherRemote() throws RemoteException {
        super();
    }

    @Override
    public String fetchWeather(String location) {
        String jsonString;
        URL url = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + location + "&mode=json&units=metric&cnt=1&appid=7bbdba5bd1ae82ab8a74b6238227e7b8");
        } catch (MalformedURLException ex) {
            Logger.getLogger(WeatherRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(WeatherRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream inputStream = null;
        try {
            inputStream = urlConnection.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(WeatherRemote.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder buffer = new StringBuilder();

        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(WeatherRemote.class.getName()).log(Level.SEVERE, null, ex);
        }

        jsonString = buffer.toString();
        return jsonString;
    }

}
