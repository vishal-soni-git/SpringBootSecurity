package in.sp.main.helperclass;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomStdDetails extends User
{
	private String name;
	
	public CustomStdDetails(String name, String email, String password, Collection<? extends GrantedAuthority> authorities)
	{
		super(email, password, authorities);
		this.name=name;
	}

	public String getName() {
		return name;
	}
}