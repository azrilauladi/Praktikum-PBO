package main;

import gui.LoginFrame; // Ensure that the LoginFrame class exists in the gui package
import user.UserManager;

// Class utama untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        UserManager.registerUser("user", "123");
        new LoginFrame().setVisible(true); // Ensure that the LoginFrame class is correctly defined and imported
    }
}