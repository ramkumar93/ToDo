<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="../style.css">
<link rel="stylesheet" type="text/css" href="../loginstyle.css">

<!-- <link rel="stylesheet" type="text/css" href="bootstrap.min.css"> -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../my_script.js"></script>
<title>Login</title>


 <script src="https://apis.google.com/js/client.js?onload=checkAuth"></script>
   <script type="text/javascript" src="../script1.js"></script>
    <script type="text/javascript">
    function oAuth(){
    	//alert("Function is called");
    	var config = {
    		/* 'client_id' : '568349204709-ou61bsqaqget4b12t61umbb826egqg3d', */
    		'client_id' : '752419755146-c4bcbfprf1v9hihldds4oq7uvfc4pl33',
    		'scope' : 'https://www.googleapis.com/auth/userinfo.email'
    	};
    	gapi.auth.authorize(config, function(resp){
    		
    		/* console.log('login complete');
    		console.log(resp.name);
    		console.log(gapi.auth.getToken()); */
    		gapi.client.load('oauth2', 'v2', function()
        			  {
        			    gapi.client.oauth2.userinfo.get()
        			      .execute(function(resp)
        			      {
        			       /*  // Shows user email
        			       document.getElementById("demo").innerHTML = "Email : "+resp.email+ "Name : "+resp.name; */
        			      var emailid = resp.email;
        			      var username = resp.name;
        			      $.ajax({
       						type : "post",
      						url : "oauthlogin",
      						data : { email : emailid, name : username },
      						success : function(result){
      							var ss = "sucess";
      							if(result != ss){
      								console.log(ss+ "is working");
      								window.location.assign("/listnameretrive");
      							}
      						}
      					}); 
        			      });
        			  });
    		
    	});
    }
    </script>


</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();">
<%if(session.getAttribute("username") != null)
	  {
	System.out.println("in loginpage inside jsp");
	 response.sendRedirect("/listnameretrive"); 
	 //request.getRequestDispatcher("/listnameretrive").forward(request, response);
	  }
else{
	//response.sendRedirect("/logout"); 
}
	  %>
<div id="content">
    <div class="oauth">
    <p class="log">Log in quick with your social login</p>
    	<!--  <form action="/design" method="post">
    	
    	<input type="submit" value="Signin with Google">
    	</form>  -->
	    <!--  <img class="img" src="google.png" onclick="oAuth()"> -->
	    <button type="button" class="google" onclick="oAuth()">Sign in with Google</button> 
	   <!--  <p id="demo">Click Google to Sign In</p> -->
	    <br>
	    <!-- <img class="img" src="facebook.png" onclick="login()"> -->
	    
	    <button type="button" class="facebook" onclick="login()">Sign in with Facebook</button>
	    
	    <!-- <div id="status"></div> -->
	 </div>
    <div class="oauth">
    
    <form id="signinform" action="/login" method="post" autocomplete="off">
	    <input type="text" class="form-control" name="email" id="signinemail" placeholder="Enter Email Id" required><br>
	    <input type="password" class="form-control" name="password" id="signinpwd" placeholder="Enter Password" required><br>
	    <button type="button" class="button" id="signinval"><strong>Signin</strong></button> 
	 </form>
	   
	    <button class="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" id="signupval"><strong>Signup</strong></button>
    <p id="error"></p>
    </div>
   </div> 
    
    <div class='container'>
    <div class="modal" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h4 class="modal-title">Enter Details</h4>
        </div>
        <form id="signupform" action="/signup" method="post" autocomplete="off">     <!--  action="/signup" method="post" -->
        <div class="modal-body">
		          <div class="form-group">
					  <label for="usr">Name:</label>
					  
					  <input type="text" class="form-control" name="name" id="name" required>
			          	
					</div>
					<div class="form-group">
					  <label for="email">Email Id.:</label>
					  
					  <input type="email" class="form-control" name="email" id="email" required>
					
					</div>
					<div class="form-group">
					  <label for="pwd">Password:</label>
					  
					  <input type="password" class="form-control" name="password" id="password" required>
					
					</div>
					<div class="form-group">
					  <label for="repwd">Retype-Password:</label>
					  
					  <input type="password" class="form-control" name="repwd" id="repwd" required>
					
					</div>
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-success" id="create" onclick="/signup">Create Account</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        </form> 
      </div>
      
    </div>
    
    
</div>


</div>




<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1699889590250601',
      xfbml      : true,
      version    : 'v2.5'
    });
    
     FB.getLoginStatus(function(response){
    	
    	if(response.status === "connected")
    	  {
    		FB.api('/me','GET',{fields: 'first_name,last_name,name,id,email'},function(response){
    			document.getElementById("status").innerHTML = "Signin with "+ response.name +" ID :"+ response.id;
    		});
    	  
    		  
    	  }
    else if(response.status === "not_authorized"){
    	  document.getElementById("status").innerHTML = "Not Connected"
    }
    else{
    	  document.getElementById("status").innerHTML = "Not Connected to facebook Click Sign In"
    		  
    }
    	
    }); 
    
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
  
  function login(){
	  FB.login(function (response){
		  if(response.status === "connected")
			  {
			  FB.api('/me',"GET",{fields: 'first_name,last_name,name,id,email'},function(response){
				 // document.getElementById("status").innerHTML = "Signin with "+ response.name +" ID :"+ response.id;
				  var emailid = response.email;
				  var username = response.name;
				 // alert(emailid);
				 // alert(username);
				  $.ajax({
						//alert("after ajax");
						type : "post",
						url : "oauthlogin",
						data : { email : emailid, name : username },
						/*data : {email : email},*/
						/*dataType: "json",*/
						/*console.log("in ajax");*/
						success : function(result){
							/*$("#myModal").modal.({backdrop:true});*/
							/* console.log("in result");
							var re = result+" Hai"; */
							var ss = "sucess";
							if(result != ss){
								window.location.assign("/listnameretrive");
							}
						}
					}); 
	    		});
				  
			  }
		  else if(response.status === "not_authorized"){
			  document.getElementById("status").innerHTML = "Not Connected"
		  }
		  else{
			  document.getElementById("status").innerHTML = "Not Connected to facebook Click Sign In"
			  
		  }
		 
	  },{scope: 'email'});
	  
  
  }
  
</script>

</body>
</html>