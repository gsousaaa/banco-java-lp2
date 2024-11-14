import java.util.List;
import java.util.ArrayList;

public class User {
    private String nome;
    private String cpf;
    private String senha;
    private List<Account> userAccounts;

    public User(String nome, String cpf, String senha) {
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

    public List<Account> getUserAccounts() {
        return this.userAccounts;
    }
}
