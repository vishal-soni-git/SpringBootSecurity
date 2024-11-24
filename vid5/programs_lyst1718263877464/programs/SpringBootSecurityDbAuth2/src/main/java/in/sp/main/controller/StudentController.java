package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sp.main.entity.Student;
import in.sp.main.repository.StudentRepository;

@Controller
public class StudentController
{
	@Autowired
	private StudentRepository stdRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String openIndexPage()
	{
		return "index";
	}
	
	@GetMapping("/register")
	public String openRegisterPage()
	{
		return "register";
	}
	
	@PostMapping("/regForm")
	public String submitRegForm(@ModelAttribute Student std, Model model)
	{
		try
		{
			std.setPassword(passwordEncoder.encode(std.getPassword()));
			
			stdRepository.save(std);
			
			//System.out.println("success");
			model.addAttribute("successMsg", "Student registered successfully");
		}
		catch(Exception e)
		{
			//System.out.println("fail");
			model.addAttribute("failMsg", "Stduent not registered due to some error");
			
			e.printStackTrace();
		}
		
		return "register";
	}
	
	@GetMapping("/profile")
	public String openProfilePage()
	{
		return "profile";
	}
}