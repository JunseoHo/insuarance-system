package common.customer;


import java.io.Serializable;

public class Customer implements Serializable {

    // ids
    private int customerId;
    // personal info
    private String name;
    private int age;
    private boolean gender;
    private String birth;
    private String job;
    // insurance info
    private String bankAccount;
    private String familyHistory;
    private String healthExaminationRecord;

    public Customer(String values[]) {
        setCustomerId(values[0]);
        setName(values[1]);
        setAge(Integer.parseInt(values[2]));
        setGender(values[3].equals("M"));
        setBirth(values[4]);
        setJob(values[5]);
        setBankAccount(values[6]);
        setFamilyHistory(values[7]);
        setHealthExaminationRecord(values[8]);
    }

    public Customer(int customerId, String name, boolean gender, String birth,
        String job,
        String bankAccount, String familyHistory, String healthExaminationRecord) {
        this.customerId = customerId;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.job = job;
        this.bankAccount = bankAccount;
        this.familyHistory = familyHistory;
        this.healthExaminationRecord = healthExaminationRecord;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getHealthExaminationRecord() {
        return healthExaminationRecord;
    }

    public void setHealthExaminationRecord(String healthExaminationRecord) {
        this.healthExaminationRecord = healthExaminationRecord;
    }

    public boolean createClaim() {
        return false;
    }

    public boolean payment() {
        return false;
    }

    public boolean receiveAnAccident() {
        return false;
    }

    public boolean rewarded() {
        return false;
    }

}