package pojo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Content {

	@Persistent
	String email;
	
	@Persistent
	String listname;
	
	@Persistent
	String content;
	
	@Persistent
	String date;
	
	@Persistent
	String gmtdate;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGmtdate() {
		return gmtdate;
	}

	public void setGmtdate(String gmtdate) {
		this.gmtdate = gmtdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getListname() {
		return listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Content [email=" + email + ", listname=" + listname
				+ ", content=" + content + ", date=" + date + ", gmtdate="
				+ gmtdate + "]";
	}


	
	
}
