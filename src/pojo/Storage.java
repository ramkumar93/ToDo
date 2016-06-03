package pojo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
//import javax.servlet.http.HttpSession;


import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;






import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import pojo.PMF;
import pojo.ReminderMail;
import pojo.Listname;
import pojo.Schedule;


public class Storage {
	PersistenceManager p = null;
	//HttpSession session = req.getSession();
	public void Signup(Users user)
	{
		p = PMF.get().getPersistenceManager();
		p.makePersistent(user);
		System.out.println("Storesd Sucessfully");
	}
	
	@SuppressWarnings("unchecked")
	public List<Listname> Listretrive(String email1)
	{
		System.out.println("in listretrive method");
		p = PMF.get().getPersistenceManager();
		Query q = p.newQuery(Listname.class);
		q.setFilter("email == '"+email1+"'");
		q.setOrdering("name asc");
		System.out.println("1");
		List<Listname> results = (List<Listname>) q.execute();
		System.out.println("2");
	return results;
	}
	
	public List<Schedule> scheduleretrive(String email)
	{
		System.out.println("in schedule retrive method");
		Date hiredate = new Date();
		 String date = hiredate.toString().substring(0, 10) + hiredate.toString().substring(23,28);
		 p = PMF.get().getPersistenceManager();
		Query q1 = p.newQuery(Schedule.class);
		q1.setFilter("email == '"+email+"' && date == '"+date+"'");
		q1.setOrdering("listName asc");
		
		List<Schedule> schedule = (List<Schedule>) q1.execute();
	return schedule;
	}
	
	
	
	public String ListServlet(String listName, String email, String name)
	{
		

		 Date hiredate = new Date();
		 String date = hiredate.toString().substring(0, 10) + hiredate.toString().substring(23,28);
		String gmtdate = hiredate.toGMTString();
		
		p = PMF.get().getPersistenceManager();
		Query q = p.newQuery(Listname.class);
		q.setFilter("email == '"+email+"' && name == '"+listName+"'");
		//q.setOrdering("name asc");
		List<Listname> data = (List<Listname>) q.execute();
		if(data.isEmpty())
		{
		Listname lst = new Listname();
		lst.setName(listName);
		lst.setEmail(email);
		p.makePersistent(lst);
		
		
		ReminderMail mail = new ReminderMail();
		mail.list(name, email, listName, date);
		
		
		}
		else{
			System.out.println(listName+" already exist");
		}
		/*Query q = p.newQuery(Listname.class);
		
		List<Listname> results = (List<Listname>) q.execute();*/
			return "redirect:listnameretrive";
		

	}
	
	
	public String saveSchedule(String listName, String email, String date, String time, String details, String name)
	{
System.out.println("in schedule servlet");
		
		String status = "failure";
		Date hiredate = new Date();
		 //String date = hiredate.toString().substring(0, 10) + hiredate.toString().substring(23,28);
		String gmtdate = hiredate.toGMTString();
		//HttpSession session = req.getSession();
		
		//PersistenceManager p = PMF.get().getPersistenceManager();
		//Key key = stringToKey(req.getParameter("Key"));
		
		
		Schedule schedule = new Schedule();
		
		schedule.setName(name);
		schedule.setListName(listName);
		schedule.setEmail(email);
		schedule.setDate(date);
		schedule.setDateGMT(gmtdate);
		schedule.setDetails(details);
		schedule.setTime(time);
		
		p = PMF.get().getPersistenceManager();
		
		try
		{
			p.makePersistent(schedule);
			status = "success";
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			p.close();
		}
		
		
		ReminderMail mail = new ReminderMail();
		mail.content(name, email, listName, details, date, time);
		//PersistenceManager pm = PMF.get().getPersistenceManager();
			//	System.out.println(datas);
		 //// String datas = saveedDataRetrive(details, email);
	
		//return datas;
		return gmtdate;
	}
	
	public String savedDataRetrive(String gmtdate, String details, String email) {
		int num = 0;
		List<Schedule> sch = null;
		p = PMF.get().getPersistenceManager();
		while(num<3){
		Query q = p.newQuery(Schedule.class);
		
		q.setFilter("dateGMT == '"+gmtdate+"' && details == '"+details+"' && email == '"+email+"'");
		//q.setFilter("details == '"+details+"' && email == '"+email+"'");	
		//	q.setFilter("email == '"+uname+"' && password == '"+passwd+"'");
		
		System.out.println("Query= "+q);
		
		sch = (List<Schedule>) q.execute();
		//int size = sch.size();
		System.out.println("size="+sch.size() );
		num++;
		System.out.println(num);
		}
		
		String data = null;
		ObjectMapper n=new ObjectMapper();
		try {
			data = n.writeValueAsString(sch);
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}finally{
			p.close();
		}
		return data;
	}
	
	public String dateView(String email, String date)
	{
		p = PMF.get().getPersistenceManager();
		Query q1 = p.newQuery(Schedule.class);
		q1.setFilter("email == '"+email+"' &&date == '"+date+"'");
		q1.setOrdering("listName asc");
		//System.out.println(session.getAttribute("emailid"));
		List<Schedule> schedule = (List<Schedule>) q1.execute();
		/*String json = new Gson().toJson(contents);*/
		ObjectMapper n=new ObjectMapper();
		String json = null;
		try {
			json = n.writeValueAsString(schedule);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			p.close();
		}
		System.out.println(json);
		  

		
		return json;
	}
	public String updateServlet(String Id) {
		
		p = PMF.get().getPersistenceManager();
		Date hiredate = new Date();
		 //String date = hiredate.toString().substring(0, 10) + hiredate.toString().substring(23,28);
		String gmtdate = hiredate.toGMTString();
		Query q1 = p.newQuery(Schedule.class);
		q1.setFilter("ID == "+Id);
		List<Schedule> schedule = (List<Schedule>) q1.execute();
		ObjectMapper n=new ObjectMapper();
		String jsondata = null;
		try {
			jsondata = n.writeValueAsString(schedule);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			p.close();
		}
		//System.out.println(jsondata);
		return jsondata;
	}
	
	public String saveUpdate(String Id, String listName, String email, String date, String time, String details, String bgdate)
	{
		
		p = PMF.get().getPersistenceManager();
		Date hiredate = new Date();
		 //String date = hiredate.toString().substring(0, 10) + hiredate.toString().substring(23,28);
		String gmtdate = hiredate.toGMTString();
		Query q1 = p.newQuery(Schedule.class);
		q1.setFilter("ID == "+Id);
			//	q.setFilter("email == '"+uname+"' && password == '"+passwd+"'");

		List<Schedule> schedule = (List<Schedule>) q1.execute();
		
		 for (Schedule sc : schedule) {
			 sc.setListName(listName);
			 sc.setEmail(email);
			 sc.setDate(date);
			 sc.setDateGMT(gmtdate);
			 sc.setDetails(details);
			 sc.setTime(time);
			 
			 p.makePersistent(sc);
		 }
		 
		// String bgdate = req.getParameter("bgdate");
		 Query q = p.newQuery(Schedule.class);
			q.setFilter("email == '"+email+"' &&date == '"+bgdate+"'");
			q.setOrdering("listName asc");
			//System.out.println(session.getAttribute("emailid"));
			List<Schedule> sched = (List<Schedule>) q.execute();
			/*String json = new Gson().toJson(contents);*/
			ObjectMapper n=new ObjectMapper();
			String json = null;
			try {
				json = n.writeValueAsString(sched);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				p.close();
			}
			//System.out.println(json);
			 
		return json;
	}
	
	public String delete(String id)
	{
		long Id = Long.parseLong(id);
		p = PMF.get().getPersistenceManager();
		System.out.println(Id);
		Schedule s = p.getObjectById(Schedule.class, Id);
		try{
			System.out.println("inside try");
			p.deletePersistent(s);
			
			
		}catch(Exception e){
			
		}finally{
			p.close();
		}
		return "deleted";
		
	}
	
	public void cron(String TodayDate) {
		
		p = PMF.get().getPersistenceManager();
		try{
		Query q = p.newQuery(Schedule.class);
		q.setFilter("date == '"+TodayDate+"'");
		q.setOrdering("name asc");
		System.out.println("before Liresult list");
		List<Schedule> results = (List<Schedule>) q.execute();
		
		if (!results.isEmpty()) {
		    for (Schedule ln : results) {
		    String name = ln.getName();
		    String email = ln.getEmail();
		    String date = ln.getDate();
		    String details = ln.getDetails();
		    String time = ln.getTime();
		    	
		    ReminderMail mail = new ReminderMail();
		    mail.cron(name, email, date, details, time);

		}
		
	}else{
		System.out.println("no jobs are there");
	}
		}catch(Exception e){
			
		}finally{
			p.close();
		}
	}
	
	public String enqueue() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		TimeZone timeZone=TimeZone.getTimeZone("IST");
		sdf.setTimeZone(timeZone);
		String t = "";
		Date date1 = null;
		
			date1 = new Date();
		
		String formattedDate=sdf.format(date1);
		int hour = Integer.parseInt(formattedDate.substring(0, 2));
		int minute = Integer.parseInt(formattedDate.substring(3, 5))+05;
		//int minute = 10;
		if( minute<10 || minute>=60 ){
		switch(minute)
		{
		case 60:
			hour +=1;
			minute = 0;
			break;
		case 61:
			hour +=1;
			minute = 1;
			break;
		case 62:
			hour +=1;
			minute = 2;
			break;
		case 63:
			hour +=1;
			minute = 3;
			break;
		case 64:
			hour +=1;
			minute = 4;
			break;
		/*case 65:
			minute = 5;
			break;*/
		}
		if(minute<10)
		{
			String m = "0"+String.valueOf(minute);
			System.out.println(m);
			t = String.valueOf(hour)+":"+String.valueOf(m);
			System.out.println("XYZ ==============>"+t);
		}
		}
		else{
		t = String.valueOf(hour)+":"+String.valueOf(minute);
		System.out.println("XYZ ==============>"+t);
		
		}
		
		/*Date d = new Date();
		int h = Integer.parseInt(d.toString().substring(11, 13));
		int min = (Integer.parseInt(d.toString().substring(14, 16)))+5;
		System.out.println("Time"+h+":"+min);
		log.info("Current time is = "+h+":"+min);
		String time = String.valueOf(h)+":"+String.valueOf(min);
		System.out.println(" old time :"+time);*/
        // Add the task to the default queue.
        
		System.out.println(t);
		return t;
	}
	
	public void worker(String ComparingTime) {
		try{
		Date hiredate = new Date();
		String TodayDate = hiredate.toString().substring(0, 10) + hiredate.toString().substring(23,28);
		//String gmtdate = hiredate.toGMTString();
		//String comparingTime = req.getParameter("ComparingTime");
		p = PMF.get().getPersistenceManager();
		Query q = p.newQuery(Schedule.class);
		q.setFilter("time == '"+ComparingTime+"' && date == '"+TodayDate+"'");
		q.setOrdering("name asc");
		System.out.println("before Liresult list");
		List<Schedule> results = (List<Schedule>) q.execute();
		
		if (!results.isEmpty()) {
		    for (Schedule ln : results) {
		    String name = ln.getName();
		    String email = ln.getEmail();
		    String date = ln.getDate();
		    String details = ln.getDetails();
		    String time = ln.getTime();
		    
		    ReminderMail mail = new ReminderMail();
		    mail.queue(name, email, date, details, time);

		}
		
	}else{
		System.out.println("no jobs are there");
	}
	
	}catch(Exception e){
		
	}finally{
		p.close();
	}
	}
}
