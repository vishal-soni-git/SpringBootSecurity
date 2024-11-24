package in.sp.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity
			.authorizeHttpRequests()
				.requestMatchers("/", "/login", "/oauth2/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.oauth2Login()
				.loginPage("/login")
				.defaultSuccessUrl("/profile")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "auth_code", "refreshToken", "Authorization")
				.permitAll();
		
		return httpSecurity.build();
	}
}
