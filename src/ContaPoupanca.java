
public class ContaPoupanca extends Account {
    // taxa rendimento
    private double incomeRate = 1;

    public ContaPoupanca(double initialBalance, User accountOwner, double agencyNumber){
        super("ContaCorrente", initialBalance, accountOwner, agencyNumber);

    }

    public void applyIncome() {
        double income = getBalance() * (this.incomeRate / 100);

        depositMoney(income);
        System.out.println("Seu rendimento mensal foi de R$" + income);
    }
}
