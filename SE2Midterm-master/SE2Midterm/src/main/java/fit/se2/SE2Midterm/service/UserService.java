package fit.se2.SE2Midterm.service;

import fit.se2.SE2Midterm.model.User;
import fit.se2.SE2Midterm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticate a user with username and password
     * @param username Username to check
     * @param password Password to verify
     * @return User object if authentication successful, null otherwise
     */
    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    /**
     * Check if a username already exists in the database
     * @param username Username to check
     * @return true if username exists, false otherwise
     */
    public boolean isUsernameExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    /**
     * Register a new user
     * @param user User to register
     * @param confirmPassword Password confirmation to verify
     * @return RegistrationResult with success status and error message if applicable
     */
    public RegistrationResult registerUser(User user, String confirmPassword) {
        // Check if username already exists
        if (isUsernameExists(user.getUsername())) {
            return new RegistrationResult(false, "Username already exists");
        }

        // Check if passwords match
        if (!user.getPassword().equals(confirmPassword)) {
            return new RegistrationResult(false, "Passwords do not match");
        }

        // Validate phone number (basic validation)
        if (user.getPhoneNumber() != null && !user.getPhoneNumber().matches("\\d{10,15}")) {
            return new RegistrationResult(false, "Invalid phone number format");
        }

        userRepository.save(user);
        return new RegistrationResult(true, null);
    }

    // Inner class to return registration results
    public static class RegistrationResult {
        private boolean success;
        private String errorMessage;

        public RegistrationResult(boolean success, String errorMessage) {
            this.success = success;
            this.errorMessage = errorMessage;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}