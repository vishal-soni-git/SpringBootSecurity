package in.sp.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails adminDetails = User.builder()
									.username("admin")
									.password("{noop}admin123")
									.roles("ADMIN")
									.build();
		
		UserDetails userDetails = User.builder()
									.username("deepak")
									.password("{noop}deepak123")
									.roles("USER")
									.build();
		
		return new InMemoryUserDetailsManager(adminDetails, userDetails);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
			.authorizeHttpRequests()
				.requestMatchers("/guest").permitAll()
				.requestMatchers("/user").hasRole("USER")
				.requestMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.httpBasic();
		
		return http.build();
	}
}