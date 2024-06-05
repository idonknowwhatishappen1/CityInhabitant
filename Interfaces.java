import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interfaces extends Remote{
    void addInhabitant(String name,String dateOfBirth,String maritalStatus) throws RemoteException;
    String allBirth() throws RemoteException;
    String marialStatus(String name) throws RemoteException;

}