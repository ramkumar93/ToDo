package pojo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
//import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class Users {

	
	@Persistent
	private String email;
	
	@Persistent
	private String name;
	
	@Persistent
	private String password;

	//@Persistent
	//private String repwd;
	
	/*public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
