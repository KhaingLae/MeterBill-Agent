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

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="jquery.slidertron-1.0.js"></script>
</head>
<body>

	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h2><a>MPSC</a></h2>
				<p>Meter Payment Service Center</p>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="menu">
		<ul>
		<li><a href="/MPSCWeb/jsp/adminhome.jsp">Home</a></li>
			<li><a href="/MPSCWeb/jsp/meteroffice.jsp">Meter Office</a></li>
			<li><a href="/MPSCWeb/jsp/enduser.jsp">End User</a></li>
			<li class="current_page_item"><a href="/MPSCWeb/jsp/bank.jsp">Bank</a></li>
			<li><a href="/MPSCWeb/Logout.do">Log Out</a></li>
		</ul>
	</div>
	<!-- end #menu -->
	<div id="page">
		<div id="content">
			<div id="slider">
				
			</div>
			<html:form action="/CheckIncome" method="POST">
			<div class="post">
				<h2 class="title">
							<a href="#">Welcome <bean:write name="userLoginForm" property="username" scope="session" />!</a>
						</h2>
						<h4><i>Bank</i></h4>
					
				
			
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
						Select Township:</td><td><div class="select_wrapper">
						<html:select property="txttown">
											<html:option value="Chan Aye Thar Zan">Chan Aye Thar Zan</html:option>
											<html:option value="Chan Mya Thar Se">Chan Mya Thar Se</html:option>
											<html:option value="Pye Kye Ta Gon">Pye Gyi Tagon</html:option>
											<html:option value="Aung Myay Thar Zan">Aung Myay Thar Zan</html:option>
											<html:option value="Maha Aung Myay">Aung Myay Thar Zan</html:option>
										</html:select></div></td></tr>	
						<tr><td></td><td>
						<p class="login button">
						<html:submit value="Check Income" />
						</p>
						</td>
						</tr></table>
						
						<logic:notEmpty name="balanceresultlist">
						<p align="center">
									<b><i>Meter Bill Information for <bean:write
												name="userLoginForm" property="txttown" scope="session" />
											in <bean:write name="userLoginForm" property="month"
												scope="session" /> <bean:write name="userLoginForm"
												property="year" scope="session" /></b></i>
								</p>
							<center>
								<table width="400" border="0">
									<tr>
										<td><b>Home</b></td>
										<td><b>Received Cash (Kyats)</b></td>
										<td><b>Received Date</b></td>
									</tr>
									<logic:iterate id="id" name="balanceresultlist">

										<tr>
											<td><bean:write property="home" name='id' /></td>
											<td><bean:write property="bill" name='id' />&nbsp;ks</td>
											<td><bean:write property="receive" name='id' /></td>
										<tr>
									</logic:iterate>
								</table>
							</center>
						</logic:notEmpty>
						<logic:notEmpty name="total"><b><i>Total : <%= session.getAttribute("total") %></i>&nbsp;ks</b></logic:notEmpty>
					</div>
			</div>
			</html:form>
			<div style="clear: both;">&nbsp;</div>
		</div>
		<!-- end #content -->
		<div id="sidebar-bg">
			<div id="sidebar">
				<ul>
					<li>
						<h2>About MPSC</h2>
						<p>MPSC is meter payment service center to communicate meter offices, end-users and their respective banks in order to easily make meter bill payment process.</p>
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
