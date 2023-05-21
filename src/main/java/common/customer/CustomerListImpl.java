package common.customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CustomerListImpl extends UnicastRemoteObject implements CustomerList {

    private static final int PORT_NUMBER = 30645;

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");

            Registry registry = LocateRegistry.createRegistry(PORT_NUMBER);
            registry.bind("CUSTOMER_LIST", new CustomerListImpl());
            System.out.println("CustomerList is running!");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
            System.out.println("Bind Failed!");
        }
    }

    private static final String dbPath = "src/main/java/common/customer/customers";
    private List<Customer> customerList;
    private CustomerDAO customerDAO;

    public CustomerListImpl() throws RemoteException {
        super();
        this.customerList = new ArrayList<>();
        this.customerDAO = new CustomerDAO();
//        load();
    }

    private void load() {
        try {
            Scanner scanner = new Scanner(new File(dbPath));
            while (scanner.hasNextLine())
                this.customerList.add(new Customer(scanner.nextLine().split(" ")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Customer customer) {
//        for (Customer element : customerList) {
//            if (element.getCustomerId() == (customer.getCustomerId()))
//                return false;
//        }
        customerList.add(customer);
        return true;
    }

    @Override
    public boolean remove(int customerId) throws RemoteException {
        for (int idx = 0; idx < customerList.size(); idx++) {
            if (customerList.get(idx).getCustomerId() == (customerId)) {
                customerList.remove(idx);
                return true;
            }
        }
        return false;
    }


    public Customer retrieve(int customerId) {
        return this.customerDAO.findByCustomerId(customerId);
    }

    @Override
    public boolean update(Customer customer) throws RemoteException {
        for (int idx = 0; idx < customerList.size(); idx++) {
            if (customerList.get(idx).getCustomerId() == (customer.getCustomerId())) {
                customerList.set(idx, customer);
                return true;
            }
        }
        return false;
    }

}