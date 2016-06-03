<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="pojo.Schedule"%>
<%@page import="pojo.Content"%>
<%@page import="java.util.*"%>
<%@page import="pojo.Listname;" %>
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>

  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>ToDo</title>
    
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> --> 
  
      	  <link rel="stylesheet" type="type/css" href="/css/bootstrap-clockpicker.min.css">
		  <link rel="stylesheet" type="type/css" href="/css/bootstrap-datepicker.min.css"> 
		  <link rel="stylesheet" type="type/css" href="/css/jquery-clockpicker.min.css">
		  <link rel="stylesheet" type="type/css" href="/css/standalone.css"> 
				    
		  <link rel="stylesheet" type="text/css" href="/style.css"/>
		<!--   <link rel="stylesheet" type="type/css" href="/css/style1.css"> -->
		  <link rel="stylesheet" type="text/css" href="../bootstrap.min.css">
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    
     <!-- DatePcker -->
    
 
<!-- 	<link rel="stylesheet" href="/resources/demos/style.css"> -->

    
    
    
    
    
    <script type="text/javascript" src="../script.js"></script>
    <script type="text/javascript" src="../my_script.js"></script>
    <script type="text/javascript" src="../script1.js"></script>
   
  
   
   <script type="text/javascript" src="bs/bootstrap-clockpicker.min.js"></script>
	<script type="text/javascript" src="bs/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="bs/jquery-clockpicker.min.js"></script> 
   <!--  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
   
     
  </head>

  <body onload="noBack();" onpageshow="if (event.persisted) noBack();">
  <%if(session.getAttribute("username") == null || session.getAttribute("username") == "")
	  {
	 // request.getRequestDispatcher("/loginfirst").forward(request, response);
	  response.sendRedirect("/loginfirst");
	  }%>
      <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
   <!--    Modal content -->
      <div class="modal-content">
        
        <form action="/list" method="post" autocomplete="off">
        <div class="modal-body">
		          <div class="form-group">
					  <label for="listName">Enter List Name:</label>
					  <input type="text" class="form-control" name="listName" id="listname" required>
					</div>
        </div>
        <div class="modal-footer">
        <button type="submit" class="btn btn-success" id="listcreate">Create List</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        </form>
      </div>
      
    </div></div>
   
  
  <div class="modal fade" id="myModal1" role="dialog">
    <div class="modal-dialog">
    
   <!--    Modal content -->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h4 class="modal-title">Schedule a Task</h4>
        </div>
        <form method="post" autocomplete="off">
        <div class="modal-body">
        
					<div class="form-group">
					  <label for="usr">List Name:</label>
					  
					  <input type="text" class="form-control" name="name" id="name" readonly="readonly" required>
			          	
					</div>
					<div class="form-group">
					  <label for="email">Email Id.:</label>
					  
					  <input type="text" class="form-control" name="email" id="email" value="<%=session.getAttribute("emailid") %>" readonly="readonly" required>
					
					</div>
					<div class="form-group input-group ">
					  <label for="date">Choose Date:</label>
					  <div class="input-group date">
					  <input type="text" class="form-control" name="date" id="date" readonly="readonly" required>
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</div>	
							</div>
					</div>
					
					 <div class="form-group input-group clockpicker" data-placement="bottom" data-align="top" data-autoclose="true">
    										<label for="time">Choose Time</label>
    										<div class="input-group time">
    										<input type="text" class="form-control" name='time' id="time" readonly="readonly" required>
    										<span class="input-group-addon">
       											 <span class="glyphicon glyphicon-time"></span>
   											 </span>
   											 </div>
									</div>	 
									
									
									
					
					<div class="form-group">
					  <label for="details">Task Details:</label>
					  
					  <textarea rows="3" class="form-control" name="details" id="details" required></textarea>
					
					</div>
					</div>
        <div class="modal-footer">
        <button type="button" class="btn btn-success" id="submitcontent" >Submit Content</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        </form>
      
      
    </div></div></div>
  
  
  
  <div class="modal fade" id="update" role="dialog">
    <div class="modal-dialog">
    
   <!--    Modal content -->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h4 class="modal-title">Update Task</h4>
        </div>
        <form method="post" autocomplete="off">
        <div class="modal-body">
        
					<div class="form-group">
					  <label for="updateusr">List Name:</label>
					  
					  <input type="text" class="form-control" name="updatename" id="updatename" readonly="readonly" required>
			          	
					</div>
					<div class="form-group">
					  <label for="updateemail">Email Id.:</label>
					  
					  <input type="text" class="form-control" name="updateemail" id="updateemail" value="<%=session.getAttribute("emailid") %>" readonly="readonly" required>
					
					</div>
					<div class="form-group input-group ">
					  <label for="updatedate">Choose Date:</label>
					  <div class="input-group date">
					  <input type="text" class="form-control" name="updatedate" id="updatedate" readonly="readonly" required>
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</div>	
							</div>
					</div>
					
					<div class="form-group input-group clockpicker" data-placement="bottom" data-align="top" data-autoclose="true">
    										<label for="updatetime">Choose Time</label>
    										<div class="input-group time">
    										<input type="text" class="form-control" name='updatetime' id="updatetime" readonly="readonly" required>
    										<span class="input-group-addon">
       											 <span class="glyphicon glyphicon-time"></span>
   											 </span>
   											 </div>
									</div>	
					
					<div class="form-group">
					  <label for="updatedetails">Task Details:</label>
					  
					  <textarea rows="3" class="form-control" name="updatedetails" id="updatedetails" required></textarea>
					
					</div>
					</div>
        <div class="modal-footer">
        <button type="button" class="btn btn-success" id="updatecontent" >Update</button>
           <button type="button" class="btn btn-warning" id="delete" data-dismiss="modal">Delete</button> 
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        </form>
      
      
    </div></div></div>
  
  
  
  
  	<div id="header" align="right">
  		
  		<div id="usepto"></div>
  		
  		 <div class="usrname">
	  		 <ul>
	  		 	
	  		 	<li>
	  		 	<a href="#"><%=session.getAttribute("username") %></a>
	  		 	
	  		 		<ul>
	  		 			<li><a href="#">Profile</a></li></br>
	  		 			<li><a href="/logout">Signout</a></li>
	  		 		</ul>
	  		 	
	  		 	</li>
	  		 </ul>
  		 </div>
  		 
  	</div>
 			<div class="input-group text" id="text">
 			
  		 		<input type="text" value="" name="text" placeholder="Add to do..">
  		 		<div class="input-group-addon" id="">
								<span class="glyphicon glyphicon-star"></span>
						</div>	 
  		 	</div>	 
  	    	
  	    <div class="clder">	
  	    	<div id="image"><div id="click">
    		<div id="leftarrow">
    	
    		<p id="left"> < </p>
    		<!-- <p id="calendar-date"></p> -->
    		<!-- <p id="calendar-month-year"></p> -->
    		
    	</div>
  		<div class="calendar">
    	
    		<p id="calendar-day-2" class="bg"></p>
    		<!-- <p id="calendar-date"></p> -->
    		<!-- <p id="calendar-month-year"></p> -->
    		
    	</div>
    	<div class="calendar">
    	
    		<p id="calendar-day-1" class="bg"></p>
    		<!-- <p id="calendar-date"></p> -->
    		<!-- <p id="calendar-month-year"></p> -->
    		
    	</div>
    	<div class="calendar">
    	
    		<p id="calendar-day" class="bga"></p>
    		<!-- <p id="calendar-date"></p> -->
    		<!-- <p id="calendar-month-year"></p> -->
    		
    	</div>
    	
    	<div class="calendar">
    	
    		<p id="calendar-day_1" class="bg"></p>
    		<!-- <p id="calendar-date"></p> -->
    		<!-- <p id="calendar-month-year"></p> -->
    		
    	</div>
    	
    	<div class="calendar">
    	
    		<p id="calendar-day_2" class="bg"></p>
    		<!-- <p id="calendar-date"></p> -->
    		<!-- <p id="calendar-month-year"></p> -->
    		
    	</div>
    	<div id="rightarrow">
    	
    		<p id="right"> > </p>
    		<!-- <p id="calendar-date"></p> -->
    		<!-- <p id="calendar-month-year"></p> -->
    		
    	</div>
    </div></div>
    </div>
    
    
  	     <!-- <div id="nf">	<div id="newfile" class="addlist"><div class="plus">+</div></div><div id="fl" class="addlist" align="center">New<br> List</div></div>  -->
  	    
  	     <div id="nf">	<div id="newfile" data-toggle="modal" data-target="#myModal"><div class="plus">+</div></div><div id="fl" data-toggle="modal" data-target="#myModal" align="center">New<br> List</div></div> 
  	    
  	    <div id="e">
  	    
  	    <%
  	    try{
  	    	
  	    List<Listname> data = (List)request.getAttribute("result_in_list");
  	  List<Schedule> schedule = (List)request.getAttribute("contents_in_list");
  	  if (!data.isEmpty()) {
		    for (Listname ln : data) {
		    String name = ln.getName();
		    String footerId = name+"_footer";
		    String mainContent = name+"_Content";
		    	
	     %>
	     <div id="container1"><div class="header" align="center"><%=name%></div><div id="scroll"><div class="<%=mainContent%>">
	     
	     <% if (!schedule.isEmpty()) {
			    for (Schedule cont : schedule) {
			    	String id = String.valueOf(cont.getID().getId());
				    String listname = cont.getListName();
				    String listcontent = cont.getDetails(); 
				    if(listname.equals(name))
				    {					
				    %>
	     <div class="inside_container" id="<%=id %>" data-toggle="modal" data-target="#update" data-placement="bottom" title="Click to Edit"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt"><%=listcontent %></div></div>
	           <%}else{
	           
	           }
	           }
	           }%>
	     </div></div><div class="footer" align="center"><img src="foot.png" data-toggle="modal" data-target="#myModal1" id="<%=name%>" class="<%=footerId%>"/></div></div>
	     <%
	     }
		  } else {
		    // Handle "no results" case
		  }
  	    }catch(Exception e){
  	    	System.out.println(e);
  	    }
  	    %>
  	    
  	    </div>
  	    
  	    
  	    
  	    
  	    
  	    
  	    
  	    
  	    
   	    <!-- <div id="main"> 
  	    	<div id="container1">
  			<div class="header" align="center">Main</div>
  			<div id="scroll">
  				<div id="Main_conten" class="main">
  					<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Send home page and product page mockup PSDs to suson
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					RE posting of countries template
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
						Design "About" section of benefits-added bullets with stock icon
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Research advertising options
    					</div>
    				</div>
    				</div>
    			</div>	
    				<div class="footer" align="center"><img src="foot.png" id="addContainer2" class="no" width=100%; "></div> 
    				<div class="footer" align="center"><button id="addcon1" widht="200px">Add</button></div>
    		</div>
    	</div> -->
    		
    	<!--	<div id="container1">
    		<div class="header" align="center">Starred</div>
    		<div id="scroll">
    		<div class="inside_container">
    		<div>
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					What are the possible value for backup status?
    					</div>
    				</div>
    		</div>

					<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Quote foe E-mail champaign+ Postcard design   
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Design 404 Error Page
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Check if Smashing conf still having ticket advertising option
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					http://www.blog.weare-2190.com/post/ 3829828282329/a-readable-wikipedia/
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Enter hours for last week in Toggi
    					</div>
    				</div>
    			</div>
    				<div class="footer" align="center"><img src="foot.png" id="addContainer2" width=100%; onmouseover="this.src('footover.png');" onmouseout="this.src('foot.png');"></div>    			
    		</div>
    		
    		
    		<div id="container1">
    		<div class="header" align="center">If I Have Time</div>
    		<div id="scroll">
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Spell and Grammar correction in all content
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Why are not my extensions accessible in Photoshop CC 2014
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Throw stuff on server / backup
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Make spreadsheet of harecoded content of translations
    					</div>
    				</div>
    				
    				<div class="inside_container">
  						<div id="checkbox">
    						<input type="checkbox" value="" name=""/>
    					</div>
    					<div id="tt">
    					Thank-you card to send to clients after a project is completed 
    					</div>
    				</div>
    			</div>
    				<div class="footer" align="center"><img src="foot.png" id="addContainer3" width=100%; onmouseover="this.src('footover.png');" onmouseout="this.src('foot.png');"></div>
    		</div> 
    		
  	    
  	    
  	    </div> -->
  	    
  	
     
    
		
    
 
  	    
  	    
  </body>
</html>
