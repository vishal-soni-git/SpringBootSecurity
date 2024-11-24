package in.sp.main.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil
{
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //generate and return JWT token
    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("claim1", "claim one");
        claims.put("claim2", "claim two");

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 10 min
                .signWith(secretKey)
                .compact();

        logger.info("Generated Token: {}", token);
        return token;
    }

    //return username from token
    public String getUsernameFromToken(String token)
    {
        String username = getClaimFromToken(token, Claims::getSubject);
        logger.info("Extracted Username: {}", username);
        return username;
    }


    //return data from JWT token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
    {
    	final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //return all the claims/data from JWT token
    private Claims getAllClaimsFromToken(String token)
    {
        try 
        {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            
            return claims;
        } 
        catch (Exception e)
        {
            logger.error("Could not parse token: {}", token, e);
            throw e;
        }
    }

	//return expiry date of JWT token
    public Date getExpirationDateFromToken(String token)
    {
        Date expiration = getClaimFromToken(token, Claims::getExpiration);
        logger.info("Extracted Expiration: {}", expiration);
        return expiration;
    }
    
    //check whether JWT token is expired or not
    private Boolean isTokenExpired(String token)
    {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //check whether the JWT token is valid or not
    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
