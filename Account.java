import java.util.UUID;

public class Account {
    private String accountNumber;
    private double balance;
    private String accountType;
    private User accountOwner;

    public Account(String accountType, double initialBalance, User accountOwner) {
        this.accountOwner = accountOwner;
        this.accountNumber = UUID.randomUUID().toString();
        this.accountType = accountType;
        this.balance = initialBalance;
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
    }

    public boolean withdrawMoney(double value) {
        if (value > this.balance) {
            return false;
        }
        this.balance -= value;
        return true;
    }

    public boolean transferMoney(Account recipientAccount, double value) {
        if (value > this.balance) {
            return false;
        }
        this.balance -= value;
        recipientAccount.depositMoney(value);
        return true;
    }
}
