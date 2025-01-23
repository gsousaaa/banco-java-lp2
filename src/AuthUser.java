
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AuthUser {

    private List<User> users = new ArrayList<>();
    private User loggedUser;
    private static String FILE_PATH = "users.csv";

    public AuthUser() {
        loadUsersFromFile();
    }

    public void registerUser(User user) {
        this.users.add(user);
        CSVWriter.appendToCSV(FILE_PATH, user.toCSV());
       
    }

    public boolean authUser(String cpf, String password) {
        for (User user : users) {
            if (user.getCpf().equals(cpf) && user.getPassword().equals(password)) {
                loggedUser = user;
                return true;
            }
        }
        return false;
    }

    public User getLoggedUser() {
        return this.loggedUser;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void logout() {
        this.loggedUser = null;
    }

    // Carrega os usuários do arquivo CSV
    private void loadUsersFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0];
                    String cpf = data[1];
                    String password = data[2];
                    String userId = data[3];
                    users.add(new User(name, cpf, password));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários do arquivo: " + e.getMessage());
        }
    }
}
