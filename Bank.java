import java.util.List;

public class Bank {
    private AuthUser authService;

    public Bank(AuthUser authService) {
        this.authService = authService;
    }

    public Account createAccount(String accountType, double initialBalance) {
        if (authService.getLoggedUser() == null) {
            return null;
        }
        Account newAccount = new Account(accountType, initialBalance, authService.getLoggedUser());
        authService.getLoggedUser().getUserAccounts().add(newAccount);
        return newAccount;
    }

    public double checkAccountBalance(String accountNumber) {
        for (Account account : authService.getLoggedUser().getUserAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account.getBalance();
            }
        }
        return -1;
    }

    public boolean deposit(String accountNumber, double value) {
        for (Account account : authService.getLoggedUser().getUserAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.depositMoney(value);
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double value) {
        for (Account account : authService.getLoggedUser().getUserAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account.withdrawMoney(value);
            }
        }
        return false;
    }

    public boolean transfer(String sourceAccountNumber, String recipientAccountNumber, double value) {
        Account sourceAccount = null;
        Account recipientAccount = null;

        for (Account account : authService.getLoggedUser().getUserAccounts()) {
            if (account.getAccountNumber().equals(sourceAccountNumber)) {
                sourceAccount = account;
                break;
            }
        }

        for (User user : authService.getUsers()) {
            for (Account account : user.getUserAccounts()) {
                if (account.getAccountNumber().equals(recipientAccountNumber)) {
                    recipientAccount = account;
                    break;
                }
            }
        }

        if (sourceAccount == null || recipientAccount == null) {
            return false;
        }

        return sourceAccount.transferMoney(recipientAccount, value);
    }
}
