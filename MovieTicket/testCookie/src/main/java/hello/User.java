package hello;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;


public class User{
	@Autowired
	@Id
	private int id;
	private String username;
	private String password;
	private boolean login;
//	private ROLE role;
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setLogin(boolean login) {
		this.login = login;
	}
	
	public boolean getLogin() {
		return this.login;
	}
	
//	public ROLE getRole() {
//		return this.role;
//	}
//	
//	public void setRole(ROLE role) {
//		this.role = role;
//	}
	
}
