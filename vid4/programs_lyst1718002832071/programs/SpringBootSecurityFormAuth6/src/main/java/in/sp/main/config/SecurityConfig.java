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
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
			.authorizeHttpRequests()
				.requestMatchers("/").permitAll()
				.requestMatchers("/adminProfile").authenticated()
				.and()
			.formLogin()
				.loginPage("/adminLogin")
				.loginProcessingUrl("/loginForm")
				.defaultSuccessUrl("/adminProfile")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/adminLogin?logoutStatus=true")
				.permitAll();
				
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails userDetails = User
									.withDefaultPasswordEncoder()
									.username("admin")
									.password("admin123")
									.build();
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(userDetails);
		
		return manager;
	}
}