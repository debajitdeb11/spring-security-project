package in.trelic.spring_jwt_auth.config;

import in.trelic.spring_jwt_auth.model.User;
import in.trelic.spring_jwt_auth.model.UserPrincipal;
import in.trelic.spring_jwt_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exist in the Database");
        }

        return new UserPrincipal(user);
    }
}
