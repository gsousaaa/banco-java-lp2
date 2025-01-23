
import java.util.ArrayList;
import java.util.List;

public class Agency {
    private double agencyNumber;
    private List<Account> accounts;

    public Agency(double agencyNumber) {
        this.agencyNumber = agencyNumber;
        this.accounts = new ArrayList<>();
    }

    public double getAgencyNumber() {
        return agencyNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account createAccount(String accountType, double initialBalance, User loggedUser) {
        Account newAccount = new Account(accountType, initialBalance, loggedUser, agencyNumber);
        accounts.add(newAccount);
        return newAccount;
    }

    public String toCSV() {
        return String.join(",", String.valueOf(this.agencyNumber));
    }

}
