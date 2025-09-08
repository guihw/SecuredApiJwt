package com.guilh.SecuredApiJWT.Controller;

import com.guilh.SecuredApiJWT.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if(auth.isAuthenticated()){
            return jwtService.generateToken(username);
        }
        throw new RuntimeException("Invalid login");
    }

   @GetMapping("/hello")
   public String hello(){
        return "Hello World!";
   }
}
