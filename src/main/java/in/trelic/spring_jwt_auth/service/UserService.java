package in.trelic.spring_jwt_auth.service;

import in.trelic.spring_jwt_auth.common.exception.UserRegistrationException;
import in.trelic.spring_jwt_auth.model.User;
import in.trelic.spring_jwt_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
    }

    public User registerUser(User user) throws UserRegistrationException {
        // validation
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
            throw new UserRegistrationException("Unable to create user");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
