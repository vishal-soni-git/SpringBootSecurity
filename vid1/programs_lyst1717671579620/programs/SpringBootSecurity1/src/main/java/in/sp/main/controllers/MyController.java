package in.sp.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MyController 
{
	@GetMapping("/home")
	public String openHomePage()
	{
		return "this is HOME page";
	}
}