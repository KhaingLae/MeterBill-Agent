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
			<li><a href="/MPSCWeb/jsp/adminhome.jsp">Home</a></li>
			<li class="current_page_item"><a href="/MPSCWeb/jsp/meteroffice.jsp">Meter Office</a></li>
			<li><a href="/MPSCWeb/jsp/enduser.jsp">End User</a></li>
			<li><a href="/MPSCWeb/jsp/bank.jsp">Bank</a></li>
			<li><a href="/MPSCWeb/Logout.do">Log Out</a></li>
			</ul>
		</div>
		<!-- end #menu -->
		<div id="page">
			<div id="content">

				<div class="post">
					<h2 class="title">
							<a href="#">Welcome <bean:write name="userLoginForm" property="username" scope="session" />!</a>
						</h2>
						<h4><i>Meter Office</i></h4>
					


					<div class="entry">
						<p align="center"><b><i>Meter Bill Information for 
						<bean:write name="userLoginForm" property="txttown" scope="session" /> in
						<bean:write name="userLoginForm" property="month" scope="session" />
						<bean:write name="userLoginForm" property="year" scope="session" />
						
						</b></i></p>
						<br>
						<logic:notEmpty name="balanceresultlist">
							<center>
								<table width="300" border="0">
									<tr>
										<td><b>Home</b></td>
										<td><b>Bill</b></td>
										<td><b>Received Date</b></td>
									</tr>
									<logic:iterate id="id" name="balanceresultlist">

										<tr>
											<td><bean:write property="home" name='id' /></td>
											<td><bean:write property="bill" name='id' /></td>
											<td><bean:write property="receive" name='id' /></td>
										<tr>
									</logic:iterate>
								</table>
							</center>
						</logic:notEmpty>

						<a href="/MPSCWeb/jsp/createreport.jsp">
						<center>
						<table width="300"><tr><td>
							<p class="login button">
								<input type="submit" value="Generate PDF Report" />
							</p>
							</td></tr>
						</table>	
						</center>
						</a>
						<br><br>
						
						
						<html:form action="/sendReport" method="POST"
							enctype="multipart/form-data">
							<p><b>Choose PDF Report File to Send ! </b></p>
						<input type="file" name="upfile" size="50" value="">						
						<center>
						<table width="200"><tr><td>
							<p class="login button">
								<html:submit value="Send Report File" />
							</p>
						</html:form>
						</td></tr>
						</table>	
						</center>
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
