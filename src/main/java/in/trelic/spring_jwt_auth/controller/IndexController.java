package in.trelic.spring_jwt_auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public ResponseEntity<Object> test(HttpServletRequest servletRequest) {
        return new ResponseEntity<>(servletRequest.getAttribute("_csrf"), HttpStatus.OK);
    }
}
