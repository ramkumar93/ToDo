package pojo;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
//import javax.jdo.annotations.Key;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Schedule {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	
	private Key ID;
	
	@Persistent
	String name;
	
	@Persistent
	String listName;
	
	@Persistent
	String email;
	
	@Persistent
	String date;
	
	@Persistent
	String time;
	
	@Persistent
	String dateGMT;
	
	@Persistent
	String details;

	

	public Key getID() {
		return ID;
	}

	public void setID(Key iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDateGMT() {
		return dateGMT;
	}

	public void setDateGMT(String dateGMT) {
		this.dateGMT = dateGMT;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	
	
}
