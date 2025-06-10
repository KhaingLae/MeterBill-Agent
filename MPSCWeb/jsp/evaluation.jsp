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
<body style="height: 500">

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
				href="/MPSCWeb/jsp/adminhome.jsp">Home</a></li>
			<li><a href="/MPSCWeb/jsp/meteroffice.jsp">Meter Office</a></li>
			<li><a href="/MPSCWeb/jsp/enduser.jsp">End User</a></li>
			<li><a href="/MPSCWeb/jsp/bank.jsp">Bank</a></li>
			<li><a href="/MPSCWeb/getEvaluation.do">Evaluation</a></li>
			<li><a href="/MPSCWeb/Logout.do">Log Out</a></li>
		</ul>
	</div>
	<!-- end #menu -->
	<div id="page">
		<div id="content">

			<div class="post">
			
				<div class="entry">
				<h3>Impact of Measures on Adaptability</h3>
				
				<center>
				<html:form action="/getEvaluation" method="POST"  >
				<br>
				<table><tr><td colspan=2>
				<img src="/MPSCWeb/css/images/kus.jpg" width="200" alt="" /></td></tr>
								<tr><td colspan=2>Type k value for KUS &nbsp;&nbsp;&nbsp;<html:text property="kusk" size="10"/></td></tr><tr> 	
									<td>Original Value</td><td>Normailzation Value</td></tr> <tr>
									<td>KUS(SA)=<bean:write name="evaluationForm" property="kussa" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nkussa" scope="session" /></td></tr><tr>
									<td>KUS(CA)=<bean:write name="evaluationForm" property="kusca" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nkusca" scope="session" /></td></tr><tr>
									<td>KUS(UA)=<bean:write name="evaluationForm" property="kusua" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nkusua" scope="session" /></td></tr><tr>
									<td>KUS(BA)=<bean:write name="evaluationForm" property="kusba" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nkusba" scope="session" /></td></tr><tr>
				<td colspan=2><img src="/MPSCWeb/css/images/kup.jpg" width="200"
									 alt="" /></td></tr></tr>
									 <tr><td colspan=2>Type k value for KUP &nbsp;&nbsp;&nbsp;<html:text property="kupk" size="10"/></td></tr><tr>
									<td>KUP(SA)=<bean:write name="evaluationForm" property="kupsa" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nkupsa" scope="session" /></td></tr><tr>
									<td>KUP(CA)=<bean:write name="evaluationForm" property="kupca" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nkupca" scope="session" /></td></tr><tr>
									<td>KUP(UA)=<bean:write name="evaluationForm" property="kupua" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nkupua" scope="session" /></td></tr><tr>
									<td>KUP(BA)=<bean:write name="evaluationForm" property="kupba" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nkupba" scope="session" /></td></tr><tr>
							<td colspan=2>Knowledge Usage(KUS) and Knowledge Update(KUP) use
									<img src="/MPSCWeb/css/images/vij.jpg" width="300" alt="" /></td></tr></tr><tr>
				<td colspan=2><img src="/MPSCWeb/css/images/ehf.jpg" width="200" alt="" /></td></tr>
				<tr><td colspan=2>Type k value for EHF &nbsp;&nbsp;&nbsp;<html:text property="ehfk" size="10"/></td></tr><tr>				
									<td> EHF(SA)=<bean:write name="evaluationForm" property="ehfsa" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nehfsa" scope="session" /></td></tr><tr>
									<td>EHF(CA)=<bean:write name="evaluationForm" property="ehfca" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nehfca" scope="session" /></td></tr><tr>
									<td>EHF(UA)=<bean:write name="evaluationForm" property="ehfua" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nehfua" scope="session" /></td></tr><tr>
									<td>EHF(BA)=<bean:write name="evaluationForm" property="ehfba" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nehfba" scope="session" /></td></tr><tr>
									<td colspan=2><br>Agent Lifespan (ALS) It measures the time duration that agent has spent in the system</td></tr><tr>
								<tr><td colspan=2>Type k value for ALS &nbsp;&nbsp;&nbsp;<html:text property="alsk" size="10"/></td></tr><tr>
									<td>ALS(SA)=<bean:write name="evaluationForm" property="alssa" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nalssa" scope="session" /></td></tr><tr>
									<td>ALS(CA)=<bean:write name="evaluationForm" property="alsca" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nalsca" scope="session" /></td></tr><tr>
									<td>ALS(UA)=<bean:write name="evaluationForm" property="alsua" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nalsua" scope="session" /></td></tr><tr>
									<td>ALS(BA)=<bean:write name="evaluationForm" property="alsba" scope="session" /></td>
									<td><bean:write name="evaluationForm" property="nalsba" scope="session" /></td></tr><tr>
									<td><p class="login button"><html:submit value="Calculate Normalization" property="normalize"/></td>
									<td><p class="login button"><html:submit value="Generate Graph" property="generate"/></p></td></tr>
									</table>
									<logic:notEmpty name="chart">
									<% System.out.println(session.getAttribute("charturl")); %>
									<img src='<%=response.encodeUrl("/MPSCWeb/jsp/getchart.jsp?"+session.getAttribute("charturl"))%>'
    usemap="#map1" border="0">
<map name="map1"><%=session.getAttribute("imagemap")%></map></logic:notEmpty>
									</html:form>
									
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
