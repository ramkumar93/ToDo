function calendar(){	
	/*var day = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
	var month = ['January','Febuary','March','April','May','June','July','August','September','October','November','December'];
	var d = new Date();
	setText('calendar-day', day[d.getDay()]);
	//setText('calendar-date', d.getDay());
	var yes = d.getDate()-1;
	setText('calendar-month-year', month[d.getMonth()-1]+' '+yes);*/
	getYesterdaysDate();
};
function getYesterdaysDate() {
	var day = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
	var month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
    var date = new Date();
    date.setDate(date.getDate());
    var date_1 = new Date();
    console.log(date_1.getFullYear());
    date_1.setDate(date.getDate()-1);
    var date_2 = new Date(); 
    date_2.setDate(date.getDate()-2);
    var date1_1 = new Date();
    date1_1.setDate(date.getDate()+1);
    var date1_2 = new Date(); 
    date1_2.setDate(date.getDate()+2);
    document.getElementById("calendar-day-2").innerHTML = '<span style="font-size:17px;">' + date_2.toDateString()+'</span>';
    document.getElementById("calendar-day-1").innerHTML = '<span style="font-size:17px;">' + date_1.toDateString()+'</span>';
    document.getElementById("calendar-day").innerHTML = '<span style="font-size:17px;">' + date.toDateString()+'</span>';
    document.getElementById("calendar-day_2").innerHTML = '<span style="font-size:17px;">' + date1_2.toDateString()+'</span>';
    document.getElementById("calendar-day_1").innerHTML = '<span style="font-size:17px;">' + date1_1.toDateString()+'</span>';
}

/*function setText(id, val){

	if(val < 10)
		{
		val = '0'+val;
		}
	document.getElementById(id).innerHTML = val;
	
};*/

window.onload = calendar;

