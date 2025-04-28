package user;

import java.util.HashMap;
import java.util.Map;

public final class UserManager {  // Making class final
    private static final Map<String, User> users = new HashMap<>();  // Making collection final
    
    private UserManager() {
        // Private constructor to prevent instantiation
    }
    
    public static void registerUser(final String username, final String password) {
        users.put(username, new User(username, password));
    }
    
    public static User loginUser(final String username, final String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }
}