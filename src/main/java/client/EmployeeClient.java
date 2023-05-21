package client;

import common.customer.Customer;
import common.employee.Employee;
import common.employee.Investigator;
import common.employee.Supporter;
import compensation.Claim;
import server.ServerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.UUID;

public class EmployeeClient {

    private static final int SERVER_PORT_NUMBER = 40022;
    private static Employee employee = null;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ServerImpl serverImpl;

    public static void main(String[] args) throws IOException {
        if (!connect()) return;
        while (employee == null) {
            employee = serverImpl.getEmployee(Integer.parseInt(getStandardInput("Input your Employee ID", reader)));
            if (employee == null)
                System.out.println("Login failed");
        }
        while (true) {
            printMenu();
            switch (getStandardInput("Select number", reader)) {
                case "1" -> {
                    if (employee instanceof Investigator)
                        uploadReport(reader);
                    else
                        System.out.println("Permission denied!");
                }
                case "2" -> {
                    if (employee instanceof Supporter)
                        reviewClaim(reader);
                    else
                        System.out.println("Permission denied!");
                }
                default -> System.out.println("Invalid input!");
            }
        }
    }

    private static boolean connect() {
        try {
            serverImpl = (ServerImpl) LocateRegistry.getRegistry("localhost", SERVER_PORT_NUMBER)
                    .lookup("SERVER");
            return true;
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Server connection error");
            return false;
        }
    }

    private static String getStandardInput(String message, BufferedReader reader) throws IOException {
        System.out.print(message + "\n" + ">> ");
        return reader.readLine().trim();
    }

    private static void printMenu() {
        System.out.println("***** Employee Menu *****");
        System.out.println("1. Upload report");
        System.out.println("2. Review claim");
    }

    private static void uploadReport(BufferedReader reader) throws IOException {
        List<Claim> claims = serverImpl.getClaims();
        System.out.println("< Claim List > ");
        for (Claim claim : claims) {
            if (claim.getEmployeeId() == (employee.getEmployeeId()))
                System.out.println(claim);
        }
        Claim claim = null;
        while (claim == null) {
            int claimId = Integer.parseInt(getStandardInput("Input claim ID", reader));
            for (Claim element : claims) {
                if (element.getClaimId() == (claimId))
                    claim = element;
            }
            if (claim == null)
                System.out.println("Not found!");
        }
        String report = getStandardInput("Input report", reader);
        claim.setReport(report);
        if (serverImpl.updateClaim(claim)) {
            System.out.println("You have been uploaded report successfully!");
            decideCompensation(reader, claim);
        } else
            System.out.println("You have been failed to upload report!");
    }

    private static void decideCompensation(BufferedReader reader, Claim claim) throws IOException {
        int compensation = Integer.parseInt(getStandardInput("Input compensation", reader));
        claim.setCompensation(compensation);
        claim.setReview("Reviewing");
        if (serverImpl.updateClaim(claim)) {
            System.out.println("You have been decided compensation successfully!");
        } else
            System.out.println("You have been failed to decide compensation!");
    }

    private static void reviewClaim(BufferedReader reader) throws IOException {
        List<Claim> claims = serverImpl.getClaims();
        System.out.println("< Claim List > ");
        for (Claim claim : claims) {
            if (claim.getReview().equals("Reviewing"))
                System.out.println(claim);
        }
        Claim claim = null;
        while (claim == null) {
            int claimId = Integer.parseInt(getStandardInput("Input claim ID", reader));
            for (Claim element : claims) {
                if (element.getClaimId() == (claimId))
                    claim = element;
            }
            if (claim == null)
                System.out.println("Not found!");
        }
        String review = getStandardInput("Input review result", reader);
        claim.setReview(review);
        if (serverImpl.updateClaim(claim)) {
            System.out.println("You have been reviewed claim successfully!");
        } else
            System.out.println("You have been failed to review claim!");
    }

}
