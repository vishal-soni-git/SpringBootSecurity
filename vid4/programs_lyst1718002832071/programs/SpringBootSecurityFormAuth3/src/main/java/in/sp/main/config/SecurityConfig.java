package in.sp.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
				.defaultSuccessUrl("/adminProfile")
				.permitAll();
				
		return http.build();
	}
}