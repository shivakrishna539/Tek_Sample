function myFunction(){
    document.getElementById('appid').style.display = 'none';


    if (document.getElementById('prmnt').checked) {
        document.getElementById('appid').style.display = 'block';
        document.getElementById('cntsalary').style.display = 'none';
		
    }
    else 
    	if(document.getElementById('cntrct').checked) {
        document.getElementById('appid').style.display = 'none';
    }
 }
function myFunction1(){
	document.getElementById('cntsalary').style.display = 'none';
if(document.getElementById('cntrct').checked) {
	
	document.getElementById('cntsalary').style.display ='block';
	document.getElementById('appid').style.display = 'none';
}
else 
	if(document.getElementById('prmnt').checked) {
    document.getElementById('cntsalary').style.display = 'none';
}
}

function removeform() {
	
	var formid = "form2";
	var divid = "a";
	document.getElementById(formid).submit();
	
	document.getElementById(divid).style.display ="none";
}
