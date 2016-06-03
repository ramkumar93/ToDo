$(document).ready(function(){
	
	$('#create').click(function(){
		var name = $("#name").val();
		var email = $("#email").val();
		var password = $("#password").val();
		var repassword = $("#repwd").val();
		var emailFormat = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		if(!isNaN(name))
			{
			$("#name").css("border-color","red");
			$("#name").val("");
			$("#name").attr("placeholder", "Enter Valid Name").placeholder();
			$("#name").focus();
			return false;
			}
		if(emailFormat.test(email) == false)
			{
			$("#email").css("border-color","red");
			$("#email").val("");
			$("#email").attr("placeholder", "Enter Valid Email Id").placeholder();
			$("#email").focus();
			return false;
			}
		if(password == '')
			{
			$("#password").css("border-color","red");
			$("#password").val("");
			$("#password").attr("placeholder", "Password must contains some characters").placeholder();
			$("#password").focus();
			return false;
			}
		if(password != repassword )
			{
			$("#password").css("border-color","red");
			$("#repwd").css("border-color","red");
			$("#password").val("");
			$("#repwd").val("");
			$("#password").attr("placeholder", "Password does not match").placeholder();
			$("#repwd").attr("placeholder", "Password does not match").placeholder();
			$("#password").focus();
			return false;
			}
	});
	
	$('#signinval').click(function(){
		
		var signinemail = $("#signinemail").val();
		var signinpassword = $("#signinpwd").val();
		
		var emailFormat = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		
		if(emailFormat.test(signinemail) == false)
			{
			$("#signinemail").css("border-color","red");
			$("#signinemail").val("");
			$("#signinemail").attr("placeholder", "Enter Valid Email Id").placeholder();
			$("#signinemail").focus();
			return false;
			}
		if(signinpassword == "" )
			{
			$("#signinpwd").css("border-color","red");
			$("#signinpwd").val("");
			$("#signinpwd").attr("placeholder", "Enter Vaild Password").placeholder();
			$("#signinpwd").focus();
			return false;
			}
	});
	
$('#listcreate').click(function(){
		
	var listformat = /^[0-9]|[a-z]|[A-Z]$/;
		var name = $("#listname").val();
		//alert(name);
		var namere = name.replace(/\s+/g,'');
		//alert(namere);
		$("#listname").val(namere);
		var listname = $("#listname").val();
		//alert(listname);
		if(listname == '')
		{
		$("#listname").css("border-color","red");
		$("#listname").val("");
		$("#listname").attr("placeholder", "Enter Vaild List Name").placeholder();
		$("#listname").focus();
		return false;
		}
		if(!isNaN(listname))
			{
			$("#listname").css("border-color","red");
			$("#listname").val("");
			$("#listname").attr("placeholder", "Enter Vaild List Name").placeholder();
			$("#listname").focus();
			return false;
			}
		
		/*if(/\S/.test(listname))
		{
		$("#listname").css("border-color","red");
		$("#listname").val("");
		$("#listname").attr("placeholder", "Please use Underscore(_) instead of Space").placeholder();
		$("#listname").focus();
		return false;
		}*/
			
	});

$('#submitcontent').click(function(){
	
	var name = $("#name").val();
	var email = $("#email").val();
	var date = $("#date").val();
	var time = $("#time").val();
	var details = $("#details").val();
	
	if(time == '')
	{
	$("#time").css("border-color","red");
	$("#time").val("");
	$("#time").attr("placeholder", "Enter Time").placeholder();
	$("#time").focus();
	return false;
	}
	else if(details == '')
{
	$("#details").css("border-color","red");
	$("#details").val("");
	$("#details").attr("placeholder", "Enter Task details").placeholder();
	$("#details").focus();
	return false;
}
else{
		$("#myModal1").modal("hide");
		
	}
});
	
	//alert($('#Main_content').attr('id'));
	/*$("#nf").click(function(){               //Making new list
		var name = prompt("Enter List Name");
		if(name != "")
			{
		$.ajax({
		    url: '/list',
		    data: {
		        postVariableName: name
		    },
		    type: 'POST'
		});​ 
		
			}else{
				alert("Enter Valid Name");
				continue;
			}*/
		
		
		/*var name = document.getElementById("listName").value;
		//var name = prompt("Enter List Name ");
		alert("Value :"+name)
		var footerId = name+"_footer";
		var mainContent = name+"_content";
		//window.alert(footerId);
		//$("ol").append("<li>adwd</li>");
		$('#e').append($('<div id="container1"><div class="header" align="center">'+name+'</div><div id="scroll"><div class="'+mainContent+'"><div class="inside_container"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt">Spell and Grammar correction in all content</div></div></div></div><div class="footer" align="center"><img src="foot.png" id="'+name+'" class="'+footerId+'"/></div></div></div>'));*/ 
		
	/*	});
	
$("#addContainer2").click(function(){
		
		//$("ol").append("<li>adwd</li>");
		$('#Main_content').append($('<div class="inside_container"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt">Send home page and product page mockup PSDs to suson</div></div>')); 
		
	});*/

/*$("img").click(function(){*/
	
	
	 //alret($("Main_content").attr('class'));
	$(".footer").click(function(){	
		var bgdate = $(".bga").text();
		//inserting details
		//var details= prompt("Enter the Details");
		/*('#listcontent').text("");*/
		var name = $(this).children("img").attr("id");
	//	alert(name);
		$("#name").val(name);
		$("#date").val($(".bga").text());
		var cls = $(this).children("img").attr("class");
		//alert(cls);
		var con = "."+name+"_Content";
		//alert(con);
		$("#form").on("keypress", function(e){
			/*event.stopPropagation();*/
			if(e.which == 13) {
			      e.preventDefault();
			      return false;
			}
		});
		$("#submitcontent").on("keypress click", function(e){
			/*event.stopPropagation();*/
			if(e.which == 13) {
			      e.preventDefault();
			      return false;
			}
			
			var email = $("#email").val();
			var date = $("#date").val();
			var time = $("#time").val();
			var details = $('#details').val();
			//alert(details);
			//alert(listcontent);
			if(date == '')
			{
			$("#date").css("border-color","red");
			$("#date").val("");
			$("#date").attr("placeholder", "Choose date").placeholder();
			$("#date").focus();
			return false;
			}
			else if(time == '')
			{
			$("#time").css("border-color","red");
			$("#time").val("");
			$("#time").attr("placeholder", "Choose Time").placeholder();
			$("#time").focus();
			return false;
			}
			else if(details == '')
		{
			$("#details").css("border-color","red");
			$("#details").val("");
			$("#details").attr("placeholder", "Enter Task details").placeholder();
			$("#details").focus();
			return false;
		}
		else{
			$.ajax({
				//alert("after ajax");
				type : "POST",
				url : "schedule",
				data : { listName : name, Email : email, Date : date, Time : time, Details : details },
				
				success : function(result){
			//		alert(result);
					$.each(result, function(index, data) {    
					       var name = "."+data.listName+"_Content";
					       
					       var id = String(data.id.id);
					       var content = data.details;
					      // alert(content);
					       if(date == bgdate){
					    	   $(name).append($('<div class="inside_container" id="'+id+'" data-toggle="modal" data-target="#update" data-placement="bottom" title="Click to Edit"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt">'+content+'</div></div>'));
					       }
					      });
				setTimeout(function(){$('#submitcontent').unbind('click');}, 10);
				/*$("#myModal").hide();*/
				$('#name').val('');
				$("#date").val('');
				$("#date").css('border-color','');
				$("#time").val('');
				$("#time").css('border-color','');
				$("#details").val('');
				$("#details").css('border-color','');
				$("#myModal1").modal("hide");
				}
			}); 
		}
		});	
		
	});
	
	
	
	$(".btn-danger").click(function(){
		$(".btn-success").off("click");
		$(".btn-warning").off("click");
	});
/*});*/

	
	/*$('body').on('click','.Main_footer', function(){
	//var x = $("#main").createElement("$scroll");
		//$("ol").append("<li>adwd</li>");
		$('.Main_content').append($('<div class="inside_container"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt">Send home page and product page mockup PSDs to suson</div></div>')); 
		
	});*/
	
	
	
	/*	$("#addContainer3").click(function(){
		
		//$("ol").append("<li>adwd</li>");
		$('#scroll').append($('<div class="inside_container"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt">Send home page and product page mockup PSDs to suson</div></div>')); 
		
	});*/
	
	
	
$("#signupval").click(function(){	
	

		$("#signupform").on("keypress", function(e){
			/*event.stopPropagation();*/
			if(e.which == 13) {
			      e.preventDefault();
			      return false;
			}
		});
		$("#create").on("click keypress", function(e){
			/*event.stopPropagation();*/
			if(e.which == 13) {
			      e.preventDefault();
			      return false;
			}
			var name = $('#name').val();
			var email = $('#email').val();
			var pwd = $('#password').val();
			var repwd = $('#repwd').val();
			//alert(listcontent);
			$.ajax({
				//alert("after ajax");
				type : "post",
				url : "signupvalidation",
				data : { name : name, email : email, pwd : pwd, repwd : repwd },
				/*data : {email : email},*/
				/*dataType: "json",*/
				/*console.log("in ajax");*/
				success : function(result){
					/*$("#myModal").modal.({backdrop:true});*/
					//alert(result)
					console.log("in result");
					var re = result+" Hai";
					var ss = "sucess";
					if(result == ss){
						/*window.location.assign("/signup");*/
						document.getElementById("signupform").submit();
					}
					else{
						$("#email").css("border-color","red");
						$("#email").val("");
						$("#password").val("");
						$("#repwd").val("");
						$("#email").focus();
						$("#email").attr("placeholder", "Email Id already exists").placeholder();
						
						
						return false;
					}
					
				
				
				/*setTimeout(function(){$('#submitcontent').unbind('click');}, 10);*/
				}
				
			});  
		});	
		
	});
	

$("#signinval").click(function(){	
	

	$("#signinform").on("keypress", function(e){
		/*event.stopPropagation();*/
		if(e.which == 13) {
		      e.preventDefault();
		      return false;
		}
	});
	
		
		var email = $('#signinemail').val();
		var pwd = $('#signinpwd').val();
		//alert(email);
		//alert(pwd);
		//alert(listcontent);
		$.ajax({
			//alert("after ajax");
			type : "post",
			url : "loginvalidation",
			data : { email : email, pwd : pwd },
			/*data : {email : email},*/
			/*dataType: "json",*/
			/*console.log("in ajax");*/
			success : function(result){
				/*$("#myModal").modal.({backdrop:true});*/
				//alert(result)
				console.log("in result");
				//alert(result);
				var ss = "sucess";
				if(result == ss){
					/*window.location.assign("/signup");*/
					document.getElementById("signinform").submit();
				}
				else{
					$("#signinemail").css("border-color","red");
					$("#signinemail").val("");
					$("#signinpwd").val("");
					$("#signinpwd").val("");
					$("#signinemail").focus();
					$("#signinemail").attr("placeholder", "Not a valid Email/Password").placeholder();
					
					
					return false;
				}
				
			
			
			/*setTimeout(function(){$('#submitcontent').unbind('click');}, 10);*/
			}
			
		});  
	
	
});



	
$(".calendar").on("click",function(){
	var $this = $(this).children('p');
	/*$this.closest(function(){*/
	if($this.hasClass('bga') == false){
		$("p").removeClass('bga');
        $($this).addClass('bga');
	}
	var value = $($this).text();
	
	//alert(value);
	$.ajax({
		type : "post",
		url : "dateview",
		data : { Value : value },
		success : function(result){
			//alert(result);
			if(result == ""){
				$(".inside_container").remove();
			}else{
			$.each(result, function(index, data) {    
			       var name = "."+data.listName+"_Content";
			    // alert(name);
			      $(".inside_container").remove();
			      
			});
			}
			$.each(result, function(index, data) {    
			       var name = "."+data.listName+"_Content";
			       var id = String(data.id.id);
			       //alert(id);
			      // alert(name);
			       //alert(employee.comtent);
			       $(name).append($('<div class="inside_container" id="'+id+'" data-toggle="modal" data-target="#update" data-placement="bottom" title="Click to Edit"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt">'+data.details+'</div></div>'));

			});
		/*setTimeout(function(){$('#submitcontent').unbind('click');}, 10);*/
			
		}
		
	}); 
	/*else{
		$('p').removeClass('bga');
		$($this).addClass('bga')
	}*/
    /*});*/
});

	$("input").blur(function(){
		$("input").css("border-color","none");
	});
	
	
$("li").hover(function(){
	
	$(this).find('ul>li').stop().fadeToggle(100);
});
	
/*$(function() {
	  $( "#date" ).datepicker();
	  $(this).change(function(){
  $( "#date" ).datepicker("option", "dateFormat", "D M d yy");
	});
});*/


$("#e").on("click", ".inside_container", function(){
	
	var id = $(this).attr('id');
	var bgdate = $(".bga").text();
	//alert(date);
	//alert(id);
	$.ajax({
		type : "post",
		url : "update",
		data : { Id : id },
		success : function(result){
			$.each(result, function(index, data) {    
			     $("#updatename").val(data.listName);
			     $("#updateemail").val(data.email);
			     $("#updatedate").val(data.date);
			     $("#updatetime").val(data.time);
			     $("#updatedetails").val(data.details);
			});
		}
	});
	
	$("#updatecontent").on("keypress click", function(e){
		/*event.stopPropagation();*/
		if(e.which == 13) {
		      e.preventDefault();
		      return false;
		}
		
		var name =  $("#updatename").val();
		var email = $("#updateemail").val();
		var date = $("#updatedate").val();
		var time = $("#updatetime").val();
		var details = $('#updatedetails').val();
		var con = "."+name+"_Content";
//		alert(details);
		//alert(listcontent);
		if(date == '')
		{
		$("#updatedate").css("border-color","red");
		$("#updatedate").val("");
		$("#updatedate").attr("placeholder", "Choose date").placeholder();
		$("#updatedate").focus();
		return false;
		}
		else if(time == '')
		{
		$("#updatetime").css("border-color","red");
		$("#updatetime").val("");
		$("#updatetime").attr("placeholder", "Choose Time").placeholder();
		$("#updatetime").focus();
		return false;
		}
		else if(details == '')
	{
		$("#updatedetails").css("border-color","red");
		$("#updatedetails").val("");
		$("#updatedetails").attr("placeholder", "Enter Task details").placeholder();
		$("#updatedetails").focus();
		return false;
	}
	else{
		$.ajax({
			//alert("after ajax");
			type : "POST",
			url : "saveupdate",
			data : { Id : id, listName : name, Email : email, Date : date, Time : time, Details : details, bgdate : bgdate },
			
			success : function(result){
				if(result == ""){
					$(".inside_container").remove();
				}else{
				$.each(result, function(index, data) {    
				       var name = "."+data.listName+"_Content";
				    // alert(name);
				      $(".inside_container").remove();
				      
				});
				}
				$.each(result, function(index, data) {    
				       var name = "."+data.listName+"_Content";
				       var id = String(data.id.id);
				       var content = data.details;
				       var retdate = data.date;
				       
			$(name).append($('<div class="inside_container" id="'+id+'" data-toggle="modal" data-target="#update" data-placement="bottom" title="Click to Edit"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt">'+content+'</div></div>'));
				    	   
				    	   });
			//alert(result);
			/*$(con).replaceWith($('<div class="inside_container" id="'+id+'" data-toggle="modal" data-target="#update" data-placement="bottom" title="Click to Edit"><div id="checkbox"><input type="checkbox" value="" name=""/></div><div id="tt">'+result+'</div></div>'));*/
			//$('#'+id).children('#tt').text(result);
			setTimeout(function(){$('#updatecontent').unbind('click');}, 10);
			//setTimeout(function(){$('#delete').unbind('click');}, 10);
			$('#updatename').val('');
			$("#updatedate").val('');
			$("#updatedate").css('border-color','');
			$("#updatetime").val('');
			$("#updatetime").css('border-color','');
			$("#updatedetails").val('');
			$("#updatedetails").css('border-color','');
			$(".btn-warning").off("click");
			$("#update").modal("hide");
			}
		}); 
	}
	});
	
	$("#delete").on("keypress click", function(e){
		event.stopPropagation();
		if(e.which == 13) {
		      e.preventDefault();
		      return false;
		}
	//alert(id);
		$.ajax({
			type : "post",
			url : "delete",
			data : { Id : id },
			success : function(result){
			//	alert(id);
				if(result == "deleted"){
					$("#"+id).remove();
					
				}
				//setTimeout(function(){$('#updatecontent').unbind('click');}, 10);
				setTimeout(function(){$('#delete').unbind('click');}, 10);
				$(".btn-success").off("click");

				$("#update").modal("hide");
			}
		});
		
	});
});

$(".btn").click(function(){
	setTimeout(function(){$('#submitcontent').unbind('click');}, 10);
});





$('.clockpicker').clockpicker();


	  $( ".date" ).datepicker({
		  format: 'D M dd yyyy'
	  });

$('[data-toggle="tooltip"]').tooltip();  

});