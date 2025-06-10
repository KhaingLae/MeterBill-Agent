<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="jade.*"%>
<%@page import="jade.wrapper.*"%>
<%@page import="jade.lang.acl.*"%>
<%@page import="jade.core.behaviours.OneShotBehaviour"%>
<%@page import="tweb.JadeBridgeLogger"%>
<%@page import="agent.LoggerAgent"%>
<%@page import="tweb.JadeBridgeSubscriber"%>
<%@page import="agent.SubscriberAgent"%>
<%@page import="action.globaldata"%>
<%
	// init the page:
	//     get (or create if necessary) the jade ag for this session
	SubscriberAgent su = (SubscriberAgent) session.getAttribute("mysu");
	LoggerAgent la = (LoggerAgent) session.getAttribute("mylg");
	try {
		if (la == null && su==null) {
			ContainerController cc = (ContainerController) application
					.getAttribute("cc");
			if (cc == null) {
				// creates the main container
				String host = "127.0.0.1";
				int port = 4456;
				out.println("starting jade container at " + host
						+ " and port " + port + "<br/>");
				String[] args = { "local-port:" + port, "host:" + host }; //,"-container"};
				Long bf=System.currentTimeMillis();
				cc = jade.core.Runtime.instance().createMainContainer(
						new BootProfileImpl(args));
				application.setAttribute("cc", cc);
				Long af=System.currentTimeMillis();
				Long df=af-bf;
				System.out.println("Container started!");
				System.out.println("Total Time : "+df+"ms");
			}
			JadeBridgeSubscriber a = new JadeBridgeSubscriber();
			Long sbf=System.currentTimeMillis();
			AgentController agCtr = cc.createNewAgent("SubscriberAgent",
					"agent.SubscriberAgent", new Object[] { a });
			JadeBridgeLogger b = new JadeBridgeLogger();
			Long lbf=System.currentTimeMillis();
			AgentController agCtrl = cc.createNewAgent("LoggerAgent",
					"agent.LoggerAgent", new Object[] { b });
			agCtr.start(); // starts the subscriber
			agCtrl.start(); // starts the logger
			
			 while (a.su == null) {
					System.out.println("Waiting subscriber agent setup...");
					Thread.sleep(100);
				}
				 
				while (b.la == null) {
					System.out.println("Waiting logger agent setup...");
					Thread.sleep(100);
				}

			su = a.su;
			System.out.println("Subscriber Agent started!");
			Long saf=System.currentTimeMillis();
			globaldata gb=new globaldata();
			gb.sa=saf-sbf;
			System.out.println("Total Time : "+gb.sa+"ms");
			
			la = b.la;
			System.out.println("Logger Agent started!");
			Long laf=System.currentTimeMillis();
			gb.ca=saf-sbf;
			System.out.println("Total Time : "+gb.ca+"ms");
			// wait for setup
			
			
			System.out.println("output in logger.."+la.print);

			// add behaviours if necessary (an example follows)
			session.setAttribute("mysu", su);
			session.setAttribute("mylg", la);

		}
	} catch (Exception ex) {
		out.println(ex);
	}
%>
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
<body style="height:500">

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
			<li class="current_page_item"><a href="/MPSCWeb/jsp/adminhome.jsp">Home</a></li>
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
					<h2 class="title">
						<a href="#">Welcome <bean:write name="userLoginForm" property="username" scope="session" />!</a>
					</h2>
				
			
				<div class="entry">
				<h3>Current Running Agent</h3>
				<center>
						<%
							out.println(la.print);
							
						%>
						</center>
						<a href="/MPSCWeb/jsp/createlog.jsp">
						<center>
						<table width="300"><tr><td>
							<p class="login button">
								<input type="submit" value="Generate Record" />
							</p>
							</td></tr>
						</table>	
						</center>
						</a>
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
