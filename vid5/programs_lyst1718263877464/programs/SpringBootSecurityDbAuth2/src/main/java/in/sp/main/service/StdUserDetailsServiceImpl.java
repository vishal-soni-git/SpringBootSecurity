package in.sp.main.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.sp.main.entity.Student;
import in.sp.main.repository.StudentRepository;

@Service
public class StdUserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private StudentRepository stdRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		Optional<Student> optionalStd = stdRepository.findByEmail(email);
		if(optionalStd.isPresent())
		{
			Student std = optionalStd.get();
			
			User user = new User(std.getEmail(), std.getPassword(), Collections.emptyList());
			return user;
		}
		else
		{
			throw new UsernameNotFoundException("Student not found");
		}
	}
}