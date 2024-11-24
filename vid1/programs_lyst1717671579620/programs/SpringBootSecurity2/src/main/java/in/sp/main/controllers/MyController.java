package in.sp.main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController 
{
	@GetMapping("/student")
	public String getUserData()
	{
		String jsonStr="[\r\n"
							+ "	{\r\n"
							+ "		\"name\" : \"deepak\",\r\n"
							+ "		\"rollno\" : 101,\r\n"
							+ "		\"marks\" : 93.5\r\n"
							+ "	},\r\n"
							+ "	{\r\n"
							+ "		\"name\" : \"amit\",\r\n"
							+ "		\"rollno\" : 102,\r\n"
							+ "		\"marks\" : 96.8\r\n"
							+ "	}\r\n"
							+ "]";
		
		return jsonStr;
	}
}