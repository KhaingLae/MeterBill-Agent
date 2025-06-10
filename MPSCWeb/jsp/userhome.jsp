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
			<li class="current_page_item"><a
				href="/MPSCWeb/jsp/userhome.jsp">Home</a></li>
			<li><a href="/MPSCWeb/ChangePassword.do">Edit Account</a></li>
			<li><a href="/MPSCWeb/jsp/viewbalance.jsp">View Balance</a></li>
			<li><a href="/MPSCWeb/Contact.do">Contact</a></li>
			<li><a href="/MPSCWeb/Logout.do">Log Out</a></li>
		</ul>
	</div>
	<!-- end #menu -->
	<div id="page">
		<div id="content">

			<div class="post">
				<h2 class="title">
					<a href="#">Welcome <bean:write name="userLoginForm"
							property="username" scope="session" />!
					</a>
				</h2>

				<div class="entry">
					<html:form action="/PayBill" method="POST">
						<logic:empty name="wait">

							<h4>
								Total units of
								<bean:write name="userLoginForm" property="month"
									scope="session" />
								<bean:write name="userLoginForm" property="year" scope="session" />
								is
								<bean:write name="userLoginForm" property="units"
									scope="session" />
								. So meter bill is as follows:
							</h4>
								From 1 to 100 units  : <b><bean:write name="userLoginForm"
									property="first" scope="session" /></b>
							<br>From 101 to 200 units  : <b><bean:write
									name="userLoginForm" property="second" scope="session" /></b>
							<br>200 units above  : <b><bean:write
									name="userLoginForm" property="third" scope="session" /></b>
							<br>Total Bill  :<b><bean:write name="userLoginForm"
									property="total" scope="session" /></b>&nbsp;ks
							<br>Percentage for Bank  :<b><bean:write
									name="userLoginForm" property="percent" scope="session" /></b>&nbsp;ks
							<br>Percentage for MPSC  :<b><bean:write
									name="userLoginForm" property="percent" scope="session" /></b>&nbsp;ks
							<br>
							<br>Total Charges  :<b><bean:write name="userLoginForm"
									property="actualbill" scope="session" /></b>&nbsp;ks
							<br>
							<br>
								The bill must be paid before expire date <b><bean:write
									name="userLoginForm" property="expire" scope="session" /></b>
							<br>
							<br>
							<logic:empty name="enough"><logic:empty name="expire">

								<p class="signin button">
									<input type="submit" value="Pay Bill" />
								</p>

							</logic:empty></logic:empty>
						</logic:empty>
						<logic:notEmpty name="enough">Your meter bill is being paid!</logic:notEmpty>
						<logic:notEmpty name="expire">Your bill payment expires the date !</logic:notEmpty>
						<logic:notEmpty name="noenough">Your balance does not enough to pay bill!</logic:notEmpty>
						<logic:notEmpty name="wait">
							<h4>Still collecting meter information for this month!</h4>
						</logic:notEmpty>
						<logic:notEmpty name="noagent">Bank Agent does not start yet ! Try Later !</logic:notEmpty>
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

				<!-- end #sidebar -->
			</div>
			<!-- end #page -->
		</div>
		<div style="clear: both;">&nbsp;</div>
	</div>

	<!-- end #footer -->
</body>
</html:html>
