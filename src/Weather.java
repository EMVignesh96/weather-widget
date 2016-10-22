

import java.rmi.*;
public interface Weather extends Remote{

public String fetchWeather(String location)throws RemoteException;
}
