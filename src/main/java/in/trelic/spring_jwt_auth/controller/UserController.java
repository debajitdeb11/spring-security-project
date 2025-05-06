package in.trelic.spring_jwt_auth.controller;

import in.trelic.spring_jwt_auth.common.exception.UserRegistrationException;
import in.trelic.spring_jwt_auth.model.User;
import in.trelic.spring_jwt_auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> test(HttpServletRequest servletRequest) {
        return new ResponseEntity<>(servletRequest.getAttribute("_csrf"), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        try {
            User createdUser = userService.registerUser(user);

            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UserRegistrationException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        String result = userService.login(user);

        if (result == null) {
            return ResponseEntity.status(401).body("Invalid Username or password");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
