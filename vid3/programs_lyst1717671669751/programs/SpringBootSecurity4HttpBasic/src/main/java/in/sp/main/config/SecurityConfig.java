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
									.build();
		
		return new InMemoryUserDetailsManager(adminDetails);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
			.authorizeHttpRequests()
				.requestMatchers("/guest").permitAll()
				.requestMatchers("/admin").authenticated()
				.and()
			.httpBasic();
		
		return http.build();
	}
}