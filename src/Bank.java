import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Agency> agencies;
    private AuthUser authService;

    public Bank(AuthUser authService) {
        this.authService = authService;
        this.agencies = new ArrayList<>();
    }

    // Adiciona uma nova agência ao banco
    public void addAgency(Agency agency) {
        agencies.add(agency);
        CSVWriter.appendToCSV("agencies.csv", agency.toCSV());
    }

    // Busca uma agência pelo número
    public Agency findAgencyByNumber(double agencyNumber) {
        for (Agency agency : agencies) {
            if (agency.getAgencyNumber() == agencyNumber) {
                return agency;
            }
        }
        return null;
    }
    
    // Busca uma conta pelo número em todas as agências
    public Account findAccountInAllAgencies(String accountNumber) {
        for (Agency agency : agencies) {
            for (Account account : agency.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }

}
