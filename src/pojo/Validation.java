package pojo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import pojo.Users;

public class Validation {
	PersistenceManager p = PMF.get().getPersistenceManager();
	public String SignupValidation(String name, String email, String password)
	{
		
System.out.println("in validation class");
		/*Users u = new Users();
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		System.out.println("before persistance");
		try{
			p.makePersistent(u);
		}finally
		{
			p.close();
		}
		
		System.out.println("Sucessfully Saved");*/

System.out.println("in signup validation method");
Query q = p.newQuery(Users.class);
q.setFilter("email == '"+email+"'");
	//	q.setFilter("email == '"+uname+"' && password == '"+passwd+"'");

List<Users> exists = (List<Users>) q.execute();
  if (exists.isEmpty()) {
	  try{
		  System.out.println("inside try");
		  
	  //req.getRequestDispatcher("/signup").forward(req, resp);
	  return("sucess");
	  }catch(Exception e){
		  System.out.println(e);
	  }
  }else{
	  System.out.println("inside else");
	  System.out.println(email);
	  try {
		return(email);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
return email;

	}
	
	
	
	public String SigninValidation(String email, String password)
	{
		
		System.out.println("in validation class");
		
		Query q = p.newQuery(Users.class);
		q.setFilter("email == '"+email+"' && password =='"+password+"'");
		try{
				//	q.setFilter("email == '"+uname+"' && password == '"+passwd+"'");
		
		List<Users> results = (List<Users>) q.execute();
		  if (!results.isEmpty()) {
		    for (Users user : results) {
		      // Process result user    	
		return "sucess";	 	    }
		  } else {
		    // Handle "no results" case
		return "no";
		}
		
		 
			 
		  
		}catch(Exception e)
		{
		
		}return email;

	}
	
	public String Login(String email, String password)
	{
		Query q = p.newQuery(Users.class);
        q.setFilter("email == '"+email+"' && password =='"+password+"'");
		try{
				//	q.setFilter("email == '"+uname+"' && password == '"+passwd+"'");
		
		List<Users> results = (List<Users>) q.execute();
		  if (!results.isEmpty()) {
		    for (Users user : results) {
		      // Process result user
		    	String emailid = user.getEmail();
		    	System.out.println("Retrived : "+email);
		    	//System.out.println("Uesr Entry : "+uname);
		    	String name = user.getName();
		    	String passwd = user.getPassword();
		    	System.out.println("user entry : "+password);
		    	System.out.println("Retrived: "+passwd);
			    	if(emailid.equals(email) && passwd.equals(password))
			    	{
			    	//session.setAttribute("username", name);
			    	//session.setAttribute("emailid", email);
			    	return name;
			    	//resp.sendRedirect("/listnameretrive");
			    	//req.getRequestDispatcher("/listnameretrive").forward(req, resp);
			    	//System.out.println("below redirect");
			    	}
			    	/*else
			    	{
			    		req.setAttribute("error", "Not a Valid User.");
			    		req.getRequestDispatcher("login.jsp").forward(req, resp);
			    	}*/
		    }
		  } else {
		    // Handle "no results" case
			//  req.setAttribute("error", "Not a Valid User.");
	    		//req.getRequestDispatcher("/loginfirst").include(req, resp);
			  System.out.println("Please Enter Valid Username and Password..");

			  return "notlogin";
			  		  }
	}catch(Exception e)
	{
		System.out.println(e);
	}finally {
		q.closeAll();
		p.close();	 
		}
		return "";
	}
	
	
}
