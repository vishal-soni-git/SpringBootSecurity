package in.sp.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{
	@Autowired
    private JwtRequestFilter jwtRequestFilter;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
    	httpSecurity
    		.csrf().disable()
    		.authorizeRequests()
    			.requestMatchers("/login").permitAll()
    			.anyRequest().authenticated()
    			.and()
    		.sessionManagement()
    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    	// Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        return httpSecurity.build();
    }
}