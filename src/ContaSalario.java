
public class ContaSalario extends Account {
    private String role;
    private double withdrawalsNumber;

    public ContaSalario(double initialBalance, User accountOwner, String role, double withdrawalsNumber, double agencyNumber) {
        super("ContaSalario", initialBalance, accountOwner, agencyNumber);

        this.role = role;
        this.withdrawalsNumber = withdrawalsNumber;
    }

    public boolean  transferBalance(double  value, ContaSalario employeeAccount) {
         if (role != "Empregador") {
            System.err.println("Você não possui permissão para depositar!");

            return false;
        }

        if(employeeAccount.role != "Empregado") {
             System.err.println("O usuário não tem permissão para receber o salário!");

            return false;
        }

        return transferMoney(employeeAccount, value);
    }

    public boolean  witdrawlBalance(double value) {
        if (role != "Empregado") {
            System.err.println("Você não possui permissão para sacar!");

            return false;
        }

        if (withdrawalsNumber <= 0) {
            System.err.println("Você não possui mais saques disponíveis!");

            return false;
        }

        boolean success = withdrawMoney(value);

        if(!success) {
            return success;
        }

        withdrawalsNumber -= 1;
        System.err.println("Saque feito! Você possui " + withdrawalsNumber + "disponiveis!");
        return success;
    }
}
