package compensation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClaimList extends Remote {

    public boolean add(Claim claim) throws RemoteException;

    public boolean remove(String claimId) throws RemoteException;

    public Claim retrieve(String claimId) throws RemoteException;

    public List<Claim> retrieveAll() throws RemoteException;

    public boolean update(Claim claim) throws RemoteException;

}