<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up page</title>

<link href="register.css" rel="stylesheet"></link>

<script>
	function validate(){
		var v=document.getElementById("pwd").value;
		if(v.length>=4  && v.length<6){
			//alert("week password")
			document.getElementById("result").innerHTML="<font color=red>week password</font>";
		}
		if(v.length>=6  && v.length<8){
			//alert("week password")
			document.getElementById("result").innerHTML="<font color=yellow>medium password</font>";
		}
		if(v.length>=8){
			//alert("week password")
			document.getElementById("result").innerHTML="<font color=green>strong password</font>";
		}
		console.log(v);
	}
	
	function mouseOver(obj) {
		obj.innerHTML=Date();
	}
	function mouseOut(obj) {
		obj.innerHTML = "Time";
	}
	
	function upperCase() {
		const i = document.getElementById("name");
		i.value = i.value.toUpperCase();
	}
	function focusEvent() {
		document.getElementById("name").style.background = "cyan";
	}
	function onBlurEvent() {
		document.getElementById("name").style.background = "grey";
	}
	function validateEmail() {
		//to establish Asynchronous communication from website = Ajax, xhr
		
		var v = document.getElementById("email").value;
		
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "EmailValidationServlet?v2="+v, true);
		xhr.send();
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4) { //4-response completed
				var v3 = xhr.responseText;
				document.getElementById("emailresult").innerHTML=v3;
			}
		}
	}
	
	function viewCaptcha() {
		var xhr = new XMLHttpRequest();
		xhr.open("GET","CaptchaServlet", true);
		xhr.send();
		xhr.onreadystatechange=function() {
			if(xhr.readyState==4) {
				var v3 = xhr.responseText;
				document.getElementById("capt").innerHTML = v3;
			}
		}
	}
	
	function loadCountry(){
		var xhr = new XMLHttpRequest();
		xhr.open("GET","CountryServlet",true);
		xhr.send();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				var v3=xhr.responseText;
				document.getElementById("cont").innerHTML=v3;
			}
		}
		
	}
	
	function readJson(){
		var xhr=new XMLHttpRequest();
		xhr.open("GET","COvishield.js",true);
		xhr.send();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				
				eval("var response1 = " + xhr.responseText + ";");
				
				var v3="";
				console.log("Ins");
				for ( var i = 0; i < response1.length; i++) {
					console.log("Inside");
					v3=v3+"  "+response1[i].name + "--->"+"<br>";

				}
				document.getElementById("jsonresult").innerHTML=v3;
			}
		}
	}
	
	function validateCaptcha(){
		var v1=document.getElementById("f1").value;
		var v2=document.getElementById("f2").value;
		if(v1==v2){
			document.getElementById("ss").disabled=false;
		}
		else{
			viewCaptcha();
		}
	}
</script>


</head>
<body onload="loadCountry()"  style = "background: #ADA996;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #EAEAEA, #DBDBDB, #F2F2F2, #ADA996);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #EAEAEA, #DBDBDB, #F2F2F2, #ADA996); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
">
  <center>
	
	<div onmouseover="mouseOver(this)" onmouseout = "mouseOut(this)">Heyy
	</div>
	</br><br>
	
	<form action="GlobalServlet?ac=register" method="post">
	<p id="abc" class="def"><font color="white" size="5"><b> Sign Up Page </b></font></p>
		<table border="5">
		<tr>
				<td>Employee Id</td><td><input type=number name="eid" id = "em"></td>
			</tr>
			<tr>
			<tr>
				<td>Name</td><td><input type=text name="nm" required  minlength="3" maxlength="10" id = "name" onchange="upperCase()" onfocus = "focusEvent()" onblur = "onBlurEvent()"></td>
			</tr>
			<tr>
				<td>Password</td><td><input type=password id="pwd" name="pass" required minlength="4" maxlength="15" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" onkeyup="validate()"><div id="result"></div></td>
			</tr>
			<tr>
				<td>Email</td><td><input type=email name="em" id = "email" onkeyup="validateEmail()"><div id = "emailresult"></div></td>
			</tr>
			<tr>
			<tr>
				<td>Salary</td><td><input type=number name="sal" id = "sal"></td>
			</tr>
			<tr>
			<tr>
				<td>Department</td><td><input type=text name="dep" id = "dep" onblur="viewCaptcha()"></td>
			</tr>
			<tr>
				<td>Country</td><td><div id="cont"></div></td>
			</tr>
			<tr><td>Captcha</td><td><div id="capt"></div></td></tr>
			<tr>
				<td><input type=submit class = "btn" id = "ss" value=Register disabled></td><td><input class = "rst" type=reset></td>
			</tr>
		</table>
	</form>
	<input type=button value=json onclick="readJson()">
	<div id="jsonresult"></div>
  </center>
${ message }
</body>
</html>