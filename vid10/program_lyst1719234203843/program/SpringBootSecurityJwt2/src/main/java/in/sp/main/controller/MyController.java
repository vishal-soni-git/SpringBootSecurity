package in.sp.main.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController
{
	@PostMapping("/profile")
	public String openProfilePage()
	{
		return "Hello Deepak....you are logged in..!!";
	}
}