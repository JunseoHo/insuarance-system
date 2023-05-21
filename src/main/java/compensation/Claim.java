package compensation;

import java.io.Serial;
import java.io.Serializable;

public class Claim implements Serializable {

    // ids
    private int claimId;
    private int customerId;
    private int employeeId;
    // info
    private String date;
    private String type;
    private String description;
    private String location;
    // review
    private String report;
    private int compensation;
    private int reviewer;
    private boolean isPaid;
    private String review = "Reporting";

    public Claim(String[] values) {
//        this.claimId = Integer.parseInt(values[0]);
        this.customerId = Integer.parseInt(values[1]);
//        this.employeeId = Integer.parseInt(values[2]);
        this.date = values[3];
        this.type = values[4];
        this.description = values[5];
        this.location = values[6];
        this.report = values[7];
        this.compensation = Integer.parseInt(values[8]);
//        this.reviewer = Integer.parseInt(values[9]);
//        this.isPaid = values[10].equals("P");
        this.review = values[11];
        this.reviewer = 1;
    }

    public Claim(int claimId, int customerId, int employeeId, String date, String type, String description,
        String location, String report, int compensation, int reviewer, boolean isPaid,
        String review) {
        this.claimId = claimId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.date = date;
        this.type = type;
        this.description = description;
        this.location = location;
        this.report = report;
        this.compensation = compensation;
        this.reviewer = reviewer;
        this.isPaid = isPaid;
        this.review = review;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public int getCompensation() {
        return compensation;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
    }

    public int getReviewer() {
        return reviewer;
    }

    public void setReviewer(int reviewer) {
        this.reviewer = reviewer;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return claimId + " " + customerId + " " + employeeId + " " + date + " "
                + type + " " + description + " " + location + " "
                + report + " " + compensation + " " + reviewer + " "
                + isPaid + " " + review;
    }

}