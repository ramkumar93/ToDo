$(document).ready(function(){
	var i=1;var j=1;
	
	$('body').on("click","#rightarrow", function(){               //fromt navigation
	j=1;
	
		
		document.getElementById("calendar-day-2").innerHTML = '<span style="font-size:17px;">' +retrievedate(-2+i)+'</span>';
	    document.getElementById("calendar-day-1").innerHTML = '<span style="font-size:17px;">' +retrievedate(-1+i)+'</span>';
	    document.getElementById("calendar-day").innerHTML = '<span style="font-size:17px;">' + retrievedate(0+i)+'</span>';
	    document.getElementById("calendar-day_1").innerHTML = '<span style="font-size:17px;">' +retrievedate(1+i)+'</span>';
	    document.getElementById("calendar-day_2").innerHTML = '<span style="font-size:17px;">' +retrievedate(2+i)+'</span>';
		i = i+1;
	});

	
	$('body').on("click","#leftarrow", function(){               //back navigation
			i = 1;
			
	document.getElementById("calendar-day-2").innerHTML = '<span style="font-size:17px;">' +retrievedate(-2-j)+'</span>';
    document.getElementById("calendar-day-1").innerHTML = '<span style="font-size:17px;">' +retrievedate(-1-j)+'</span>';
    document.getElementById("calendar-day").innerHTML = '<span style="font-size:17px;">' + retrievedate(0-j)+'</span>';
    document.getElementById("calendar-day_1").innerHTML = '<span style="font-size:17px;">' +retrievedate(1-j)+'</span>';
    document.getElementById("calendar-day_2").innerHTML = '<span style="font-size:17px;">' +retrievedate(2-j)+'</span>';
	j = j+1;
	});
	
	
});

function retrievedate(a)
{
var date = new Date();

date.setDate(date.getDate() + a);
return date.toDateString();
}



window.history.forward();
function noBack() { window.history.forward(); }