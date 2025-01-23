public class ContaCorrente extends Account {
    private  double maintenanceValue; 

    public ContaCorrente(double initialBalance, User accountOwner, double maintenanceValue, double agencyNumber) {
        super("ContaCorrente", initialBalance, accountOwner, agencyNumber);

        this.maintenanceValue = maintenanceValue;
    }

    public void chargeMaintenanceValue() {
        if(getBalance() >= this.maintenanceValue) {
            withdrawMoney(maintenanceValue);
        } else {
            System.out.println("Saldo insuficiente para pagar a manutenção");
        }
    }
}