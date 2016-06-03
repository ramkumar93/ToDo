package com.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pojo.ReminderMail;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.sun.org.apache.xpath.internal.operations.Mod;

import pojo.Schedule;
import pojo.Listname;
import pojo.PMF;
//import com.google.apphosting.utils.config.ClientDeployYamlMaker.Request;

import pojo.Users;
import pojo.Validation;
import pojo.Storage;

@Controller
public class ToDoServlet {
	/*public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}*/
	
	
	@RequestMapping("/")
	public ModelAndView index()
	{
		return new ModelAndView("login");
	}
	
	
	@RequestMapping(value="/signup", method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Signup(@ModelAttribute("users") Users a, HttpServletRequest req, HttpServletResponse resp)
	{
		System.out.println("in signup mapping servlet");
		Storage storage = new Storage();
		storage.Signup(a);
		HttpSession session = req.getSession();
		System.out.println("0");
		session.setAttribute("username", a.getName());
		session.setAttribute("emailid", a.getEmail());
		ModelAndView model = new ModelAndView("redirect:listnameretrive");
		return model;
	}
	
	

	@RequestMapping(value="/oauthlogin",method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView oAuthLogin(@RequestParam("email")String email,@RequestParam("name")String name,HttpServletRequest req, HttpServletResponse resp)
	{
		HttpSession session = req.getSession();
		session.setAttribute("username", name);
		session.setAttribute("emailid", email);
		System.out.println(email);
		System.out.println(name);
		/*try {
			resp.getWriter().write("sucess");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		ModelAndView model = new ModelAndView("redirect:listnameretrive");
		return model;
	}
	
	
	@RequestMapping(value="/login",method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Login(@RequestParam("email")String email,@RequestParam("password")String pwd,HttpServletRequest req, HttpServletResponse resp)
	{
		System.out.println("in login mapping servlet");
		Storage storage = new Storage();
		Validation v = new Validation();
		String username = v.Login(email, pwd);
		/*storage.Signup(a);*/
		HttpSession session = req.getSession();
		System.out.println("0");
		if(username != "notlogin")
		{
		session.setAttribute("username", username);
		session.setAttribute("emailid", email);
		
		System.out.println("after redirected");
		ModelAndView model = new ModelAndView("redirect:listnameretrive");
		return model;
		}
		else
		{
			ModelAndView model = new ModelAndView("login");
			return model;
		}
	}
	
	
	@RequestMapping(value="/signupvalidation", method={ RequestMethod.GET, RequestMethod.POST })
	public void Signup(@RequestParam("email")String email,@RequestParam("name")String name,@RequestParam("pwd")String pwd,@RequestParam("repwd")String repwd,HttpServletResponse resp)
	{
		/*System.out.println(email);
		System.out.println(name);
		System.out.println(pwd);*/
		Validation v = new Validation();
		String result = v.SignupValidation(name, email, pwd);
		System.out.println("result is "+result);
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	@RequestMapping(value="/loginvalidation", method={ RequestMethod.GET, RequestMethod.POST })
	public void Signin(@RequestParam("email")String email,@RequestParam("pwd")String pwd, HttpServletResponse resp)
	{
		/*System.out.println(email);
		System.out.println(name);
		System.out.println(pwd);*/
		Validation v = new Validation();
		String result = v.SigninValidation(email, pwd);
		System.out.println("result is "+result);
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	@RequestMapping(value="/list", method={ RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam("listName")String listName,HttpServletRequest req, HttpServletResponse resp)
	{
		Storage storage = new Storage();
		HttpSession session = req.getSession();
		//String listname = (String) session.getAttribute("listName");
		String emailid = (String) session.getAttribute("emailid");
		String name = (String) session.getAttribute("username");
		String path = storage.ListServlet(listName, emailid, name);
	
		return path;
	}
	
	
	@RequestMapping(value="/listnameretrive",method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(HttpServletRequest req, HttpServletResponse resp)
	{
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("emailid");
		System.out.println("in listname retrive");
		Storage storage = new Storage();
		List<Listname> results = (List<Listname>) storage.Listretrive(email);
		List<Schedule> schedule = (List<Schedule>) storage.scheduleretrive(email);
		try {
			/*req.getRequestDispatcher("/listretrive").include(req,  resp);*/
			req.setAttribute("result_in_list", results);
			req.setAttribute("contents_in_list", schedule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("after redirected");
	
		ModelAndView model = new ModelAndView("welcome");
		return model;
	}
	
	
	@RequestMapping(value="/schedule",method={ RequestMethod.GET, RequestMethod.POST })
	public void schedule(@RequestParam("listName") String listName, @RequestParam("Email") String Email, @RequestParam("Date") String Date, @RequestParam("Time") String Time, @RequestParam("Details") String Details, HttpServletRequest req, HttpServletResponse resp)
	{
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("username");
		Storage storage = new Storage();
		String gmtdate = storage.saveSchedule(listName, Email, Date, Time, Details, name);
		String data = null;
		System.out.println(gmtdate);
		 data = storage.savedDataRetrive(gmtdate,Details, Email);
		try {
			resp.setContentType("application/json");
			  resp.setCharacterEncoding("UTF-8");
			    resp.getWriter().write(data.toString());
			  
			  } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/dateview",method={ RequestMethod.GET, RequestMethod.POST })
	public void DataView(@RequestParam("Value") String date, HttpServletRequest req, HttpServletResponse resp)
	{
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("emailid");
		Storage storage = new Storage();
		String dateview = storage.dateView(email, date);
		resp.setContentType("application/json");
		  resp.setCharacterEncoding("UTF-8");
		  try {
			resp.getWriter().write(dateview.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/update", method={ RequestMethod.GET, RequestMethod.POST })
	public void Upadte(@RequestParam("Id") String Id, HttpServletRequest req, HttpServletResponse resp)
	{
		
		//HttpSession session = req.getSession();
		Storage storsge = new Storage();
		//String id = req.getParameter("Id");
		//PersistenceManager p = PMF.get().getPersistenceManager();
		
			//	q.setFilter("email == '"+uname+"' && password == '"+passwd+"'");

		String jsondata = storsge.updateServlet(Id);
		  resp.setContentType("application/json");
		  resp.setCharacterEncoding("UTF-8");
		  try {
			resp.getWriter().write(jsondata.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@RequestMapping(value="/saveupdate",method={ RequestMethod.GET, RequestMethod.POST })
	public void SaveUpdate(@RequestParam("Id") String Id, @RequestParam("listName") String listName, @RequestParam("Email") String Email, @RequestParam("Date") String Date, @RequestParam("Time") String Time, @RequestParam("Details") String Details,@RequestParam("bgdate") String bgdate, HttpServletRequest req, HttpServletResponse resp)
	{
		Storage storage = new Storage();
		
		String json = storage.saveUpdate(Id, listName, Email, Date, Time, Details, bgdate);
		resp.setContentType("application/json");
		  resp.setCharacterEncoding("UTF-8");
		  try {
			resp.getWriter().write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/delete",method={ RequestMethod.GET, RequestMethod.POST })
	public void SaveUpdate(@RequestParam("Id") String Id, HttpServletRequest req, HttpServletResponse resp)
	{
		
		Storage storage = new Storage();
		String a = storage.delete(Id);
		try {
			resp.getWriter().write("deleted");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/cron",method={ RequestMethod.GET, RequestMethod.POST })
	public void Cron(HttpServletRequest req)
	{
		Date hiredate = new Date();
		String TodayDate = hiredate.toString().substring(0, 10) + hiredate.toString().substring(23,28);
		String gmtdate = hiredate.toGMTString();
		
		Storage storage = new Storage();
		storage.cron(TodayDate);
		
		
	}
	
	
	@RequestMapping(value="/enqueue",method={ RequestMethod.GET, RequestMethod.POST })
	public void Enqueue(HttpServletRequest req)
	{
		Storage storage = new Storage();
		String t = storage.enqueue();
		
		Queue queue = QueueFactory.getDefaultQueue();
        queue.add(TaskOptions.Builder.withUrl("/workerqueue").param("ComparingTime", t));
	}
	
	
	@RequestMapping(value="/workerqueue",method={ RequestMethod.GET, RequestMethod.POST })
	public void Workerqueue(@RequestParam("ComparingTime")String ComparingTime, HttpServletRequest req)
	{
		System.out.println("In worker queue");
		Storage storage = new Storage();
		storage.worker(ComparingTime);
	}
	
	
	@RequestMapping(value="/loginfirst",method={ RequestMethod.GET, RequestMethod.POST })
	public void LoginFirst( HttpServletResponse resp)
	{
//		HttpSession session = req.getSession();
//		
//		if(session.getAttribute("username") == null)
//		  {
//		  req.getSession().invalidate();
//		 
//		  }	
//		 ModelAndView model = new ModelAndView("login");
//		return model;
		try {
			resp.sendRedirect("/logout");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    @RequestMapping(value="/logout", method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView logout(HttpServletResponse resp,HttpServletRequest req)
	{
	try {
		
		Cookie loginCookie = null;
    	Cookie[] cookies = req.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		if(cookie.getName().equals("user")){
    			loginCookie = cookie;
    			break;
    		}
    	}
    	}
    	if(loginCookie != null){
    		loginCookie.setMaxAge(0);
        	resp.addCookie(loginCookie);
    	}
		
		
		HttpSession session = req.getSession(false);
		resp.setHeader("Cache-Control","no-cache");
		resp.setHeader("Cache-Control","no-store");
		resp.setHeader("Pragma","no-cache");
		resp.setDateHeader ("Expires", 0);
		session.setAttribute("username", "");
		session.removeAttribute("username");
		session.invalidate();
		//req.getRequestDispatcher("login.jsp").forward(req, resp);
		//req.getRequestDispatcher("login.html").forward(req, resp);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		ModelAndView model = new ModelAndView("login");
		return model;
	}

	
}

