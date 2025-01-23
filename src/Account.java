import java.io.*;
import java.util.*;

public class Account {
    private String accountNumber;
    private double balance;
    private String accountType;
    private User accountOwner;
    private double agencyNumber;

    public Account(String accountType, double initialBalance, User accountOwner, double agencyNumber) {
        this.accountOwner = accountOwner;
        this.accountNumber = UUID.randomUUID().toString();
        this.accountType = accountType;
        this.balance = initialBalance;
        this.agencyNumber = agencyNumber;

        CSVWriter.appendToCSV("accounts.csv", this.toCSV());
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void depositMoney(double value) {
        this.balance += value;
        updateCSV();
    }

    public boolean withdrawMoney(double value) {
        if (value > this.balance) {
            return false;
        }
        this.balance -= value;
        updateCSV();
        return true;
    }

    public boolean transferMoney(Account recipientAccount, double value) {
        if (value > this.balance) {
            return false;
        }
        this.balance -= value;
        recipientAccount.depositMoney(value);
        updateCSV();
        return true;
    }

    public String toCSV() {
        return String.join(",", this.accountNumber, this.accountType, String.valueOf(this.balance), String.valueOf(this.agencyNumber), this.accountOwner.getUserId());
    }


    private void updateCSV() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(this.accountNumber)) {
                    line = String.join(",", this.accountNumber,  this.accountType, String.valueOf(this.balance), String.valueOf(this.agencyNumber), this.accountOwner.getUserId());
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Atualizar o CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.csv"))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
