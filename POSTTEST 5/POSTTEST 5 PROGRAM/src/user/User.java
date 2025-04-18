package user;

public class User {
    private final String username;  // Making fields final
    private final String password;
    
    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean checkPassword(final String password) {
        return this.password.equals(password);
    }
}