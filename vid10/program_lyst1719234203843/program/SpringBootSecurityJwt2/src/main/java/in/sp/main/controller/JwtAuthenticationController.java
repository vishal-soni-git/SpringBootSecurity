package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import in.sp.main.model.JwtRequest;
import in.sp.main.security.JwtTokenUtil;

@RestController
public class JwtAuthenticationController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception 
    {
        try
        {
        	authenticateTheUser(jwtRequest.getUsername(), jwtRequest.getPassword());

            final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(token);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username and password.");
        }
    }

    private void authenticateTheUser(String username, String password) throws Exception
    {
        try 
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } 
        catch (Exception e)
        {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}