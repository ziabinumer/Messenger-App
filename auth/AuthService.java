package auth;

import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private List<UserCredentials> users;

    public AuthService() {
        users = new ArrayList<>();
    }

    // register
    public boolean register(String username, String password) {
        if (getUser(username) != null) return false; // username already present
        users.add(new UserCredentials(username, password));
        return true;
    }

    // log in
    public boolean login(String username, String password) {
        UserCredentials user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }

    private UserCredentials getUser(String username) {
        for (UserCredentials u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }
}
