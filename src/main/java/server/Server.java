package server;

import common.customer.Customer;
import common.customer.CustomerList;
import common.employee.Employee;
import common.employee.EmployeeList;
import common.employee.Investigator;
import compensation.Claim;
import compensation.ClaimList;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Server extends UnicastRemoteObject implements ServerImpl {

    private static final int PORT_NUMBER = 40022;
    private static final int CLAIM_LIST_PORT_NUMBER = 20622;
    private static final int CUSTOMER_LIST_PORT_NUMBER = 30645;
    private static final int EMPLOYEE_LIST_PORT_NUMBER = 20645;

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");

            Registry registry = LocateRegistry.createRegistry(PORT_NUMBER);
            registry.bind("SERVER", new Server());
            System.out.println("Server is running!");
            claimListImpl = (ClaimList) LocateRegistry.getRegistry("localhost", CLAIM_LIST_PORT_NUMBER)
                    .lookup("CLAIM_LIST");
            System.out.println("ClaimList is bound!");
            customerListImpl = (CustomerList) LocateRegistry.getRegistry("localhost", CUSTOMER_LIST_PORT_NUMBER)
                    .lookup("CUSTOMER_LIST");
            System.out.println("CustomerList is bound!");
            employeeListImpl = (EmployeeList) LocateRegistry.getRegistry("localhost", EMPLOYEE_LIST_PORT_NUMBER)
                    .lookup("EMPLOYEE_LIST");
            System.out.println("EmployeeList is bound!");
        } catch (RemoteException | AlreadyBoundException | NotBoundException e) {
            e.printStackTrace();
            System.out.println("Bind Failed!");
        }
    }

    private static ClaimList claimListImpl;
    private static CustomerList customerListImpl;
    private static EmployeeList employeeListImpl;

    public Server() throws RemoteException {
        super();
    }

    @Override
    public Customer getCustomer(String customerId) throws RemoteException {
        return customerListImpl.retrieve(customerId);
    }

    @Override
    public Employee getEmployee(String employeeId) throws RemoteException {
        return employeeListImpl.retrieve(employeeId);
    }

    @Override
    public List<Claim> getClaims() throws RemoteException {
        return claimListImpl.retrieveAll();
    }

    @Override
    public boolean updateClaim(Claim claim) throws RemoteException {
        return claimListImpl.update(claim);
    }

    @Override
    public boolean createClaim(Claim claim) throws RemoteException {
        List<Employee> employeeList = employeeListImpl.retrieveAll();
        List<Employee> investigators = new ArrayList<>();
        for (Employee employee : employeeList)
            if (employee.getDepartment().equals("investigator"))
                investigators.add(employee);
        claim.setEmployeeId(investigators.get(new Random().nextInt(investigators.size()))
                .getEmployeeId());
        return claimListImpl.add(claim);
    }
}