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
			<li><a href="/MPSCWeb/jsp/officehome.jsp">Home</a></li>
			<li  class="current_page_item"><a href="/MPSCWeb/jsp/sendmonthlybill.jsp">Send Monthly Bill</a></li>
			<li><a href="/MPSCWeb/jsp/billpayment.jsp">View Bill Payment</a></li>
			<li><a href="/MPSCWeb/ChangePassword.do">Edit Account</a></li>
			<li><a href="/MPSCWeb/Contact.do">Contact</a></li>
			<li><a href="/MPSCWeb/Logout.do">Log Out</a></li>
			</ul>
		</div>
		<!-- end #menu -->
		<div id="page">
			<div id="content">
				
				<html:form action="/sendMonthlyBill" method="POST"
					enctype="multipart/form-data">
					<div class="post">
						<h2 class="title">
							<a href="#">Welcome <bean:write name="userLoginForm" property="username" scope="session" />!</a>
						</h2>
						<h4><i>Send Monthly Bill</i></h4>
					


						<div class="entry">
						<table height="300"><tr><td width="100">
						Enter Year:</td><td><div class="select_wrapper">
						<html:select property="year">
						<html:option value="2015">2015</html:option>
						<html:option value="2016">2016</html:option>
						<html:option value="2017">2017</html:option>
						</html:select></div></td></tr>
						<tr><td>
						Enter Month:</td><td><div class="select_wrapper">
						<html:select property="month">
						<html:option value="JANAUARY">JANAUARY</html:option>
						<html:option value="FEBRUARY">FEBRUARY</html:option>
						<html:option value="MARCH">MARCH</html:option>
						<html:option value="APRIL">APRIL</html:option>
						<html:option value="MAY">MAY</html:option>
						<html:option value="JUNE">JUNE</html:option>
						<html:option value="JULY">JULY</html:option>
						<html:option value="AUGUST">AUGUST</html:option>
						<html:option value="SEPTEMBER">SEPTEMBER</html:option>
						<html:option value="OCTOBER">OCTOBER</html:option>
						<html:option value="NOVEMBER">NOVEMBER</html:option>
						<html:option value="DECEMBER">DECEMBER</html:option>
						</html:select></div>
						</td></tr>
						<tr><td>
						Select File:</td><td>
						<input type="file" name="upfile" size="50" value="">
						</td></tr>
						
						<tr><td></td>
						<td>
						<p class="login button">
						<html:submit value="Send Bill File" />
						</p>
						</td>
						</table>
						</div>
					</div>
					<div style="clear: both;">&nbsp;</div>
			</div>
			</html:form>
			
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
