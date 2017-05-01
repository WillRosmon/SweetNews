<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>SweetNews | Curated News for the World</title>
		<meta name="description" content="Demo of Material design portfolio template by TemplateFlip.com."/>
		<link href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;amp;lang=en" rel="stylesheet">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css" rel="stylesheet">
		<link href="CSS/styles.css" rel="stylesheet">
		<script type="text/javascript">
		function onbodyload()
		{
			var sessionname= document.getElementById("usersessionname").value;
			if(sessionname == null)
	        {
	            document.getElementById("homebar").style.visibility='hidden';
	            document.getElementById("aboutbar").style.visibility='hidden';
	            document.getElementById("signbar").style.visibility='hidden';
	            document.getElementById("loginbar").style.visibility='hidden';
	            document.getElementById("logoutbar").style.visibility='visible';
	        }
	        else
	        {
	        	document.getElementById("homebar").style.visibility='visible';
	        	document.getElementById("aboutbar").style.visibility='visible';
	        	document.getElementById("signbar").style.visibility='visible';
	            document.getElementById("loginbar").style.visibility='visible';
	            document.getElementById("logoutbar").style.visibility='hidden';        
	        }
			
			
		}
		
		</script>
		
		
	</head>
	
	<body id="top" onload="onbodyload();">
		<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
			<header class="mdl-layout__header mdl-layout__header--waterfall site-header">
				<div class="mdl-layout__header-row site-logo-row">
					<span class="mdl-layout__title">
						<span class="site-description" style="margin-left: 20px;">Sweet News</span>
					</span>
				</div>
				<input type="hidden" id="usersessionname" value="${sessionScope.theUser}" />
				<div class="mdl-layout__header-row site-navigation-row mdl-layout--large-screen-only">
					<nav class="mdl-navigation mdl-typography--body-1-force-preferred-font">
						<!-- <a class="mdl-navigation__link" href="index.jsp" id="homebar">Home</a>
						<a class="mdl-navigation__link" href="about.jsp" id="aboutbar">About</a>
						<a class="mdl-navigation__link" href="signup.jsp" id="signbar">Sign Up</a> -->
						<!-- <a class="mdl-navigation__link" href="index.jsp#login" id="loginbar">LogIn</a>
						<a class="mdl-navigation__link" href="index.jsp#login" id="logoutbar">LogOut</a> -->
						
						<c:choose>
							<c:when test="${sessionScope.theUser == null}">
								<a class="mdl-navigation__link" href="index.jsp">Home</a>
							</c:when>
							<c:otherwise>
								<a class="mdl-navigation__link" href="main.jsp">Home</a>
							</c:otherwise>
						</c:choose>
						<a class="mdl-navigation__link" href="about.jsp">About</a>
						<a class="mdl-navigation__link" href="signup.jsp">Sign Up</a>
						<c:choose>
							<c:when test="${theUser == null}">
								<a class="mdl-navigation__link" href="index.jsp#login">Login</a>
							</c:when>
							<c:otherwise>
								<a class="mdl-navigation__link" href="user?action=logout">Logout</a>
							</c:otherwise>
						</c:choose>
					</nav>
				</div>
			</header>
		    <div class="mdl-layout__drawer mdl-layout--small-screen-only">
		      <nav class="mdl-navigation mdl-typography--body-1-force-preferred-font">
		      	<a class="mdl-navigation__link" href="index.jsp">Home</a>
		     		<a class="mdl-navigation__link" href="about.jsp">About</a>
		     		<a class="mdl-navigation__link" href="signup.jsp">Sign Up</a>
		     		<a class="mdl-navigation__link" href="index.jsp#login">Login</a>
		      </nav>
		    </div>