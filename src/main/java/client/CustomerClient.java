package client;

import common.customer.Customer;
import compensation.Claim;
import server.ServerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class CustomerClient {

    // server connection
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT_NUMBER = 40022;
    private static final String SERVER_NAME = "SERVER";
    private static ServerImpl serverImpl = null;
    // client object
    private static Customer customer = null;
    // I/O device
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        if (!connect()) return;
        if (!login()) return;
        while (true) {
            printMenu();
            switch (getStandardInput("Select number", reader)) {
                case "1" -> createClaim();
                case "x" -> {
                    System.out.println("System exit....");
                    return;
                }
                default -> System.out.println("Invalid input!");
            }
        }
    }

    private static boolean connect() {
        try {
            serverImpl = (ServerImpl) LocateRegistry.getRegistry(SERVER_HOST, SERVER_PORT_NUMBER)
                    .lookup(SERVER_NAME);
            return true;
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Server connection error");
            return false;
        }
    }

    private static boolean login() throws IOException {
        while (customer == null) {
            String inputId = getStandardInput("Input your Customer ID (Typing 'x' to exit)", reader);
            if (inputId.equals("x")) return false;
            if ((customer = serverImpl.getCustomer(Integer.parseInt(inputId))) == null) System.out.println("Login failed");
        }
        return true;
    }

    private static void printMenu() {
        System.out.println("***** Customer Menu *****");
        System.out.println("1. Create claim");
        System.out.println("x. Exit");
    }

    private static void createClaim() throws IOException {
        String[] values = new String[12];
        values[0] = UUID.randomUUID().toString();
        values[1] = String.valueOf(customer.getCustomerId());
        // employee_id
        values[2] = "1";
        while (values[3] == null) {
            if (!checkDateFormat(values[3] = getStandardInput("Input date (ex : 2023/05/10)", reader))) {
                values[3] = null;
                System.out.println("Date format is invalid!");
            }
        }
        values[4] = getStandardInput("Input type", reader);
        values[5] = getStandardInput("Input description", reader).replace(" ", "_");
        values[6] = getStandardInput("Input location", reader).replace(" ", "_");
        values[7] = "none";
        values[8] = "0";
        values[9] = "none";
        values[10] = "none";
        values[11] = "reporting";
        if (serverImpl.createClaim(new Claim(values)))
            System.out.println("You have been created new claim successfully!");
        else
            System.out.println("You have been failed to create new claim!");
    }

    private static boolean checkDateFormat(String date) {
        try {
            SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy/MM/dd");
            dateFormatParser.setLenient(false);
            dateFormatParser.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static String getStandardInput(String message, BufferedReader reader) throws IOException {
        System.out.print(message + "\n" + ">> ");
        return reader.readLine().trim();
    }

}
