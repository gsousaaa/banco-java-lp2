import java.util.List;
import java.util.ArrayList;

public class AuthUser {
    private List<User> users = new ArrayList<>();
    private User loggedUser;

    public void registerUser(User user) {
        this.users.add(user);
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
}
