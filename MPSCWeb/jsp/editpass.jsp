<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>


<html:html>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Meter Payment Service Center</title>
<link href="http://fonts.googleapis.com/css?family=Abel"
	rel="stylesheet" type="text/css" />
<link href="/MPSCWeb/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="/MPSCWeb/css/login.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="/MPSCWeb/css/animate-custom.css" rel="stylesheet"
	type="text/css" media="screen" />

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="jquery.slidertron-1.0.js"></script>
</head>
<body>
	
		<div id="header-wrapper">
			<div id="header">
				<div id="logo">
					<h2>
						<a>MPSC</a>
					</h2>
					<p>Meter Payment Service Center</p>
				</div>
			</div>
		</div>
		<!-- end #header -->
		<div id="menu">
			<ul>
			<logic:notEmpty name="ologin">
				<li><a href="/MPSCWeb/jsp/officehome.jsp">Home</a></li>
				<li><a href="/MPSCWeb/jsp/sendmonthlybill.jsp">Send Monthly	Bill</a></li>
				<li><a href="/MPSCWeb/jsp/billpayment.jsp">View Bill Payment</a></li>
				<li class="current_page_item"><a href="/MPSCWeb/ChangePassword.do">Edit Password</a></li>
				<li><a href="/MPSCWeb/Contact.do">Contact</a></li>
				<li><a href="/MPSCWeb/Logout.do">Log Out</a></li>
			</logic:notEmpty>
			<logic:notEmpty name="ulogin">	
				<li><a href="/MPSCWeb/jsp/userhome.jsp">Home</a></li>
				<li class="current_page_item"><a href="/MPSCWeb/ChangePassword.do">Edit Password</a></li>
				<li><a href="/MPSCWeb/jsp/viewbalance.jsp">View Balance</a></li>
				<li><a href="/MPSCWeb/Contact.do">Contact</a></li>
				<li><a href="/MPSCWeb/Logout.do">Log Out</a></li>
			</logic:notEmpty>
			<logic:notEmpty name="blogin">	
			 	<li><a href="/MPSCWeb/jsp/bankhome.jsp">Home</a></li>
			 	<li><a href="/MPSCWeb/BankBalance.do">View Balance</a></li>
				<li class="current_page_item"><a href="/MPSCWeb/ChangePassword.do">Edit Password</a></li>
				<li><a href="/MPSCWeb/Contact.do">Contact</a></li>
				<li><a href="/MPSCWeb/Logout.do">Log Out</a></li>
				</logic:notEmpty>
			</ul>
		</div>
		<!-- end #menu -->
		<div id="page">
			<div id="content">

				<div class="post">
					<h2 class="title">
						<a href="#">Welcome 
										<bean:write name="userLoginForm" property="username"
											scope="session" />
										!</a>
					</h2>

						<h4><i>Edit Password</i></h4>
					<div class="entry">
						<html:form action="/ChangePassword" method="POST">
							<table height="300" width="400">
								<tr>
									<td width="200">Enter Current Password:</td>
									<td><html:password property="cpass"></html:password>
									</td>
								</tr>
								<tr>
									<td>Enter New Password:</td>
									<td><html:password property="npass"></html:password>
									</td>
								</tr>
								<tr>
									<td>Retype New Password:</td>
									<td><html:password property="rpass"></html:password>
									</td>
								</tr>
					
					<tr><td colspan="2"><html:errors property="wrongpass" />
					<html:errors property="mismatch" /></td></tr>
					<tr><td></td>
					<td>
								<p class="login button">
									<input type="submit" value="Change Password" />
								</p>							
					</td>	  
							</table>
					<logic:notEmpty name="afterchange">
					<h4>Your password has been changed!</h4>
					</logic:notEmpty>		
						
</html:form>
						
					</div>
				</div>
				<div style="clear: both;">&nbsp;</div>

			</div>
			<!-- end #content -->
			<div id="sidebar-bg">
				<div id="sidebar">
					<ul>
						<li>
							<h2>About MPSC</h2>
							<p>MPSC is meter payment service center to communicate meter
								offices, end-users and their respective banks in order to easily
								make meter bill payment process.</p>
						</li>
						
					</ul>

				</div>
			</div>
			<!-- end #sidebar -->
			<div style="clear: both;">&nbsp;</div>
		</div>
		<!-- end #page -->
	
	<div id="footer">
		<p>2015. MPSC Website. All rights reserved.</p>
	</div>
	<!-- end #footer -->
</body>
</html:html>
