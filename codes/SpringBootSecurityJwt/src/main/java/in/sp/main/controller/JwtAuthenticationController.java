package in.sp.main.controller;

import in.sp.main.model.JwtRequest;
import in.sp.main.security.JwtTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        authenticateTheUser(jwtRequest.getUsername(), jwtRequest.getPassword());

        UserDetails userDetails=userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        return jwtTokenUtil.generateToken(userDetails);
    }

    public void authenticateTheUser(String username, String password) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
