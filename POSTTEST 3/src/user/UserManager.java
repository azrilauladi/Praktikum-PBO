package user;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Map<String, User> users = new HashMap<>();
    
    public static void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }
    
    public static User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.validatePassword(password)) {
            return user;
        }
        return null;
    }
}
