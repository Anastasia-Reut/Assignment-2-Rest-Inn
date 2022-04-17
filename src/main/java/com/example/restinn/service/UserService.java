package com.example.restinn.service;

import com.example.restinn.model.User;
import com.example.restinn.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.
    private static int workload = 12;

    public void createUser(User user) {
        validateUser(user);
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashPassword(hashedPassword));
        userRepository.save(user);
    }

    public void verifyUser(String email, String password) throws FailedLoginException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new PropertyNotFoundException("User with email" + email + " not found");
        }
        validatePassword(password, optionalUser.get().getPassword());
    }

    public User getUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new PropertyNotFoundException("User not found");
        }
        return optionalUser.get();
    }

    private void validateUser(User user) {
        if (user.getFirstName() == null
                || user.getLastName() == null
                || user.getEmail() == null
                || user.getPassword() == null) {
            throw new DataIntegrityViolationException("User data is invalid");
        }
        if (!isEmailValid(user.getEmail())) {
            throw new DataIntegrityViolationException("Email format is invalid");
        }
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password, salt);
    }

    private boolean isEmailValid(String email) {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void validatePassword(String password_plaintext, String stored_hash) throws FailedLoginException {
        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided");
        if (!BCrypt.checkpw(password_plaintext, stored_hash)) {
            throw new FailedLoginException("Email and password pair not exists in the database");
        }
    }
}
