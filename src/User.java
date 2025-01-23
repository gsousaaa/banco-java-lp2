
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String userId;
    private String nome;
    private String cpf;
    private String senha;
    private List<Account> userAccounts;

    public User(String nome, String cpf, String senha) {
        this.userId = UUID.randomUUID().toString();
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.userAccounts = new ArrayList<>();
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getPassword() {
        return this.senha;
    }

      public String getUserId() {
        return this.userId;
    }

    public List<Account> getUserAccounts() {
        return this.userAccounts;
    }

    public String toCSV() {
        return String.join(",", this.nome, cpf, senha, this.userId);
    }
}
