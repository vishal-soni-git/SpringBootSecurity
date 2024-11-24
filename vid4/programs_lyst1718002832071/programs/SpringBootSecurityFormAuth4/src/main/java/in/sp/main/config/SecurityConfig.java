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
			.csrf().disable()
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
				.logoutSuccessUrl("/adminLogin")
				.permitAll();
				
		return http.build();
	}
}