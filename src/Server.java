import java.rmi.*;
import java.rmi.registry.*;

public class Server{

public static void main(String args[]){
try{

Weather stub=new WeatherRemote();
Naming.rebind("rmi://localhost/fetchWeather",stub);

}catch(Exception e){System.out.println(e);}
}

}
