package in.sp.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController
{
	@GetMapping("/")
	public String openIndexPage()
	{
		return "index";
	}
	
	@GetMapping("/adminProfile")
	public String openAdminProfilePage()
	{
		return "admin-profile";
	}
}