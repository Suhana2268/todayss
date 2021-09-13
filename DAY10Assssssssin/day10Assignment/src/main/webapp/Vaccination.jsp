<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="survey.css" rel="stylesheet"></link>
<title>Insert title here</title>

<script>

function validate(){  
	var name = document.getElementById("name").value;
	if(name.length < 6) {
		document.getElementById("result").innerHTML = "<font color=red>Enter valid name</font>";
		
	}
	else{
		document.getElementById("result").innerHTML="<font color=green>good!</font>";
		}

	}
	function validate1() {
	var age=document.getElementById("ag").value;
	if(age <= 18 || age >=60) {
		document.getElementById("agr").innerHTML="<font color=red>Enter valid age </font>"; 
		}
	else{
		document.getElementById("agr").innerHTML="<font color=green>valid age</font>";
	}
	}

function getCoviShield() {
	
	var v = document.getElementById("covi").value;
	console.log(v+"iiii");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "GlobalServlet?ac="+v, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			var v3 = xhr.responseText;
			document.getElementById("vaccineresult").innerHTML = v3;
		}
	}
}
function getCovaxin() {
	
	var v = document.getElementById("cova").value;
	console.log(v+"iiii");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "GlobalServlet?ac="+v, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			var v3 = xhr.responseText;
			document.getElementById("vaccineresult").innerHTML = v3;
		}
	}
}
function getHttpRequest() {
	if (window.ActiveXObject) {
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP")
	} else {
		httpRequest = new XMLHttpRequest();
	}

	console.log(httpRequest);
}

function printData() {
	console.log("helloooo");
	var name = document.getElementById("name").value;
	var gender = document.getElementById("gen").value;
	var age = document.getElementById("ag").value;
	var v3 = "";
	var dose = "first";
	
	opt = document.getElementById('covax').value;
	opt1 = document.getElementById('covi').value;
	if(document.getElementById('covax').checked == true) {
		getHttpRequest();
		httpRequest.onreadystatechange=handleResponse
		httpRequest.open("GET", "GlobalServlet?ac="+opt, true);
		httpRequest.send(null);
	}
	
	if(document.getElementById('covi').checked==true) {

		getHttpRequest();
		httpRequest.onreadystatechange=handleResponse
		httpRequest.open("GET", "GlobalServlet?ac="+opt1, true);
		httpRequest.send(null);
	}
	
	return false;
	
} 


function handleResponse() {
	var name = document.getElementById("name").value;
	var gender = document.getElementById("gen").value;
	var age = document.getElementById("ag").value;
	var dose = "first"
	if(httpRequest.readyState == 4) {
		if(httpRequest.status ==200) {
			var response=httpRequest.responseText;
		
			const myJSON = JSON.stringify(response);
			
			var jsonObj = eval('('+myJSON+')')
		
			var emx = eval('('+jsonObj+')')
			if(document.getElementById('covax').checked == true) {
				var op = document.getElementById('covax').value;
				var sec = emx.secondDose;
				var effects = emx.sideEffects
				var prec = emx.precautions
			}
			if(document.getElementById('covi').checked==true){
				var op = document.getElementById('covi').value;
				 sec=emx.secondDose
			     effects=emx.sideEffects
				 prec=emx.precautions
			}
			
			var table = document.getElementById('tab');
			
			var row = `<tr><td>${name}</td>
							<td>${gender}</td>
							<td>${age}</td>
							<td>${dose}</td>
							<td>${op}</td>
							<td>${sec}</td>
							<td>${effects}</td>
							<td>${prec}</td>
							</tr>`;
			table.innerHTML += row;
		}
	}
	return false;
}

</script>
</head>
<body>
<div>
	<form name = "myform" onsubmit="return validate()">
		<div> 
			<h3>Covid Vaccination Preference Survey</h3>
		</div>
		<div>
			<table>
				<tr>
					<td class = "itr">Name</td>
					<td><input type="text" class = "inp" name="name" required="" id = "name" onkeyup="validate()"><div id="result"></td>
				</tr>
				<tr>
					<td class = "itr">Gender</td>
					<td><input type="text" class = "inp" name="gender"  required="" id = "gen"></td>
				</tr>
				<tr>
					<td class = "itr">Age</td>
					<td><input type="number" name="age" class = "inp" required="" id = "ag" onkeyup="validate1()">
					<div id="agr"></div></td>
				</tr>
				<tr>
					<td class = "itr">Dose</td>
					<td><select id = "dose" name = "dose" class = "inp"><option>first</option><option>Second</option></select>
				<tr>
					<td class = "itr">Preference</td>
					<td><input type="radio" class = "inp" name="cov" required="" id = "covax" value = "Covaxin">Covaxin<br><input type="radio" name="cov" id = "covi" value = "Covishield">Cocisheild</td>

				</tr>
			</table>
			<div>
			<input type="submit" class = "btn" onclick="return printData();"/>
			<div id = "result"></div>
		</div>
	</form>
	</div>
	<div class = "val" style = "padding: 30px;">

	<input type = "submit" name = "covi" id = "covi" value = "Covishield" onclick = "getCoviShield()" class = "c1"><div id = "vaccineresult"></div>
<input type ="submit" name = "covax" id = "cova" value = "Covaxin" onclick = "getCovaxin()" class = "c1"><div id = "vaccineresult"></div>
	<div>
	<table border = "2px" id = "tab" class = "tab1" align = "center">
		<tr>
			<th>Name</th>
			<th>Gender</th>
			<th>Age</th>
			<th>Dose</th>
			<th>Preference</th>
			<th>Second Dose</th>
			<th>Side Effects</th>
			<th>Precautions</th>
		</tr>
	</table>
	</div>


</body>
</html>