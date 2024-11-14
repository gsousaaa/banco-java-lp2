public class Main {
    public static void main(String[] args) {
        AuthUser authService = new AuthUser();
        Bank bank = new Bank(authService);

        // Criando e registrando usuários
        User user1 = new User("João", "123456789", "senha123");
        User user2 = new User("Maria", "987654321", "senha321");
        authService.registerUser(user1);
        authService.registerUser(user2);


        // autenticando user1 e criando sua conta
        authService.authUser("123456789", "senha123");
        Account accountJoao = bank.createAccount("Corrente", 1000.0);
        System.out.println("Saldo inicial da Conta Corrente de João: " + accountJoao.getBalance());

        //autenticando user2 e criando sua conta
        authService.authUser("987654321", "senha321");
        Account accountMaria = bank.createAccount("Corrente", 2000.0);
        System.out.println("Saldo inicial da Conta Corrente de Maria: " + accountMaria.getBalance());

        // entrando na conta de joao para transferir
        authService.authUser("123456789", "senha123");
        boolean transferencia = bank.transfer(accountJoao.getAccountNumber(), accountMaria.getAccountNumber(), 100.0);

        System.out.println("Transferência de João para Maria " + (transferencia ? "realizada com sucesso!" : "falhou."));
        System.out.println("Saldo final da Conta Corrente de João: " + accountJoao.getBalance());
        System.out.println("Saldo final da Conta Corrente de Maria: " + accountMaria.getBalance());
    }
}
