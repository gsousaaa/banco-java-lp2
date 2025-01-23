
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializa os serviços principais
        AuthUser authService = new AuthUser();
        Bank bank = new Bank(authService);

        // Criação de uma agência inicial
        Agency agency1 = new Agency(101);
        bank.addAgency(agency1);

        System.out.println("Bem-vindo ao sistema bancário!");
        boolean exit = false;

        while (!exit) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Registrar usuário");
            System.out.println("2. Fazer login");
            System.out.println("3. Criar conta bancária");
            System.out.println("4. Depositar dinheiro");
            System.out.println("5. Sacar dinheiro");
            System.out.println("6. Ver saldo da conta");
            System.out.println("7. Transferir dinheiro");
            System.out.println("8. Logout");
            System.out.println("9. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (choice) {
                case 1:
                    System.out.println("Digite seu nome:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite seu CPF:");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite sua senha:");
                    String senha = scanner.nextLine();

                    User newUser = new User(nome, cpf, senha);
                    authService.registerUser(newUser);
                    System.out.println("Usuário registrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Digite seu CPF:");
                    String loginCpf = scanner.nextLine();
                    System.out.println("Digite sua senha:");
                    String loginSenha = scanner.nextLine();

                    if (authService.authUser(loginCpf, loginSenha)) {
                        System.out.println("Login realizado com sucesso!");
                    } else {
                        System.out.println("CPF ou senha incorretos!");
                    }
                    break;

                case 3:
                    if (authService.getLoggedUser() == null) {
                        System.out.println("Faça login primeiro!");
                        break;
                    }

                    Agency selectedAgency = null;
                    while (selectedAgency == null) {
                        System.out.println("Digite o número da agência:");
                        int agencyNumber = scanner.nextInt();
                        scanner.nextLine();

                        selectedAgency = bank.findAgencyByNumber(agencyNumber);
                        if (selectedAgency == null) {
                            System.out.println("Agência não encontrada! Por favor, tente novamente.");
                        }
                    }

                    System.out.println("Escolha o tipo de conta:");
                    System.out.println("1. Conta Corrente");
                    System.out.println("2. Conta Poupança");
                    System.out.println("3. Conta Salário");
                    int accountType = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o saldo inicial:");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine();

                    Account newAccount;
                    switch (accountType) {
                        case 1:
                            scanner.nextLine();
                            newAccount = new ContaCorrente(initialBalance, authService.getLoggedUser(), 10, agency1.getAgencyNumber());
                            break;

                        case 2:
                            newAccount = new ContaPoupanca(initialBalance, authService.getLoggedUser(), agency1.getAgencyNumber());
                            ((ContaPoupanca) newAccount).applyIncome();
                            break;

                        case 3:
                            System.out.println("Digite seu cargo:");
                            System.out.println("1. Empregado");
                            System.out.println("2. Empregador");
                            double role = scanner.nextInt();
                            scanner.nextLine();
                            String roleString = (role == 1) ? "Empregado" : "Empregador";
                            newAccount = new ContaSalario(initialBalance, authService.getLoggedUser(), roleString, 3, agency1.getAgencyNumber());
                            break;

                        default:
                            System.out.println("Tipo de conta inválido!");
                            continue;
                    }

                    agency1.getAccounts().add(newAccount);
                    authService.getLoggedUser().getUserAccounts().add(newAccount);
                    System.out.println("Conta criada com sucesso! Número da conta: " + newAccount.getAccountNumber());
                    break;

                case 4:
                    if (authService.getLoggedUser() == null) {
                        System.out.println("Faça login primeiro!");
                        break;
                    }

                    System.out.println("Digite o número da conta:");
                    String accountNumber = scanner.nextLine();
                    Account account = bank.findAccountInAllAgencies(accountNumber);

                    if (account != null) {
                        System.out.println("Digite o valor para depósito:");
                        double depositValue = scanner.nextDouble();
                        scanner.nextLine();
                        account.depositMoney(depositValue);
                        System.out.println("Depósito realizado com sucesso! Saldo atual: R$" + account.getBalance());
                    } else {
                        System.out.println("Conta não encontrada!");
                    }

                    break;

                case 5:
                    if (authService.getLoggedUser() == null) {
                        System.out.println("Faça login primeiro!");
                        break;
                    }

                    System.out.println("Digite o número da conta:");
                    String withdrawalAccountNumber = scanner.nextLine();
                    Account withdrawalAccount = bank.findAccountInAllAgencies(withdrawalAccountNumber);

                    if (withdrawalAccount != null) {
                        System.out.println("Digite o valor para saque:");
                        double withdrawalValue = scanner.nextDouble();
                        scanner.nextLine();

                        if (withdrawalAccount.withdrawMoney(withdrawalValue)) {
                            System.out.println("Saque realizado com sucesso! Saldo atual: R$" + withdrawalAccount.getBalance());
                        } else {
                            System.out.println("Saldo insuficiente!");
                        }
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    break;

                case 6:
                    if (authService.getLoggedUser() == null) {
                        System.out.println("Faça login primeiro!");
                        break;
                    }

                    System.out.println("Digite o número da conta:");
                    String findBalanceAccountNumber = scanner.nextLine();
                    Account findBalanceAccount = bank.findAccountInAllAgencies(findBalanceAccountNumber);

                    if (findBalanceAccount == null) {
                        System.out.println("Conta não encontrada!");
                        break;
                    }

                    System.out.println("Seu saldo é de R$ " + findBalanceAccount.getBalance());

                    break;

                case 7:
                    if (authService.getLoggedUser() == null) {
                        System.out.println("Faça login primeiro!");
                        break;
                    }

                    System.out.println("Digite o número da conta de origem:");
                    String sourceAccountNumber = scanner.nextLine();
                    Account sourceAccount = bank.findAccountInAllAgencies(sourceAccountNumber);

                    System.out.println("Digite o número da conta de destino:");
                    String targetAccountNumber = scanner.nextLine();
                    Account targetAccount = bank.findAccountInAllAgencies(targetAccountNumber);

                    if (sourceAccount != null && targetAccount != null) {
                        System.out.println("Digite o valor para transferência:");
                        double transferValue = scanner.nextDouble();
                        scanner.nextLine();

                        if (sourceAccount instanceof ContaSalario) {
                            if (((ContaSalario) sourceAccount).transferBalance(transferValue, ((ContaSalario) targetAccount))) {
                                System.out.println("Transferência realizada com sucesso!");
                                break;
                            } else {
                                System.out.println("Não foi possível transferir o salário!");
                                break;
                            }
                        }

                        if (sourceAccount.transferMoney(targetAccount, transferValue)) {
                            System.out.println("Transferência realizada com sucesso!");
                        } else {
                            System.out.println("Saldo insuficiente para a transferência!");
                        }
                    } else {
                        System.out.println("Uma ou ambas as contas não foram encontradas!");
                    }
                    break;

                case 8:
                    authService.logout();
                    System.out.println("Logout realizado com sucesso!");
                    break;

                case 9:
                    exit = true;
                    System.out.println("Saindo do sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
