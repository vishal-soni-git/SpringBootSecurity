package in.sp.main.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController 
{
	@GetMapping("/")
	public String openIndexPage()
	{
		return "index";
	}
	
	@GetMapping("/login")
	public String openLoginPage()
	{
		return "login";
	}
	
	@GetMapping("/profile")
	public String openProfilePage(@AuthenticationPrincipal OidcUser principal, Model model)
	{
		if(principal != null)
		{
			String fullname = principal.getFullName();
			String email = principal.getEmail();
			
			model.addAttribute("modelName", fullname);
			model.addAttribute("modelEmail", email);
		}
		return "profile";
	}
}
