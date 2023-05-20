package compensation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClaimListImpl extends UnicastRemoteObject implements ClaimList {

    private static final int PORT_NUMBER = 20622;

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");
            Registry registry = LocateRegistry.createRegistry(PORT_NUMBER);
            registry.bind("CLAIM_LIST", new ClaimListImpl());
            System.out.println("ClaimList is running!");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
            System.out.println("Bind Failed!");
        }
    }

    private static final String dbPath = "src/main/java/compensation/claims";
    private List<Claim> claimList;

    public ClaimListImpl() throws RemoteException {
        super();
        this.claimList = new ArrayList<>();
        load();
    }

    private void load() {
        try {
            Scanner scanner = new Scanner(new File(dbPath));
            String[] values;
            while (scanner.hasNextLine())
                claimList.add(new Claim(scanner.nextLine().split(" ")));
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean add(Claim claim) {
        for (Claim element : claimList) {
            if (element.getClaimId().equals(claim.getClaimId())) return false;
        }
        this.claimList.add(claim);
        return insert(claim.toString());
    }


    public boolean remove(String claimId) {
        for (int idx = 0; idx < claimList.size(); idx++) {
            if (claimList.get(idx).getClaimId().equals(claimId)) {
                this.claimList.remove(idx);
                return true;
            }
        }
        return false;
    }


    public Claim retrieve(String claimId) {
        for (Claim element : claimList) {
            if (element.getClaimId().equals(claimId)) return element;
        }
        return null;
    }

    @Override
    public List<Claim> retrieveAll() throws RemoteException {
        return claimList;
    }

    public boolean update(Claim claim) {
        for (int idx = 0; idx < claimList.size(); idx++) {
            if (claimList.get(idx).getClaimId().equals(claim.getClaimId())) {
                this.claimList.set(idx, claim);
                return update(claim.toString());
            }
        }
        return false;
    }

    private boolean insert(String record) {
        try {
            FileWriter writer = new FileWriter(dbPath, true);
            writer.write(record + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean update(String record) {
        try {
            Scanner scanner = new Scanner(new File(dbPath));
            String dummy = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.split(" ")[0].equals(record.split(" ")[0])) {
                    dummy += record + "\n";
                } else dummy += line + "\n";
            }
            FileWriter writer = new FileWriter(dbPath, false);
            writer.write(dummy);
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}