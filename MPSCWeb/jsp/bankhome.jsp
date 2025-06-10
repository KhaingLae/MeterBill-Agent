<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="jade.*"%>
<%@page import="jade.wrapper.*"%>
<%@page import="jade.lang.acl.*"%>
<%@page import="jade.core.behaviours.OneShotBehaviour"%>
<%@page import="tweb.JadeBridgeBank"%>
<%@page import="agent.BankAgent"%>
<%@page import="action.globaldata"%>
<%
	// init the page:
	//     get (or create if necessary) the jade ag for this session

	BankAgent ga = (BankAgent) session.getAttribute("myag");
	try {
		if (ga == null) {
			ContainerController cc = (ContainerController) application
					.getAttribute("cc");
			if (cc == null) {
				// creates the main container
				String host = "127.0.0.1";
				int port = 4456;
				out.println("starting jade container at " + host
						+ " and port " + port + "<br/>");
				String[] args = { "local-port:" + port, "host:" + host }; //,"-container"};
				cc = jade.core.Runtime.instance().createMainContainer(
						new BootProfileImpl(args));
				application.setAttribute("cc", cc);
				System.out.println("Container started!");
			}
			Long bf=System.currentTimeMillis();
			JadeBridgeBank b = new JadeBridgeBank();
			AgentController agCtrl = cc.createNewAgent("BankAgent",
					"agent.BankAgent", new Object[] { b });
			agCtrl.start(); // starts the JspJadeAg

			// wait for setup

			while (b.ga == null) {
				System.out.println("Waiting bank agent setup...");
				Thread.sleep(100);
			}

			ga = b.ga;
			Long af=System.currentTimeMillis();
			globaldata gb=new globaldata();
			gb.ba=af-bf;
			System.out.println("Bank Agent started!");
			System.out.println("Total Time : "+gb.ba+"ms");
			// add behaviours if necessary (an example follows)

			session.setAttribute("myag", ga);

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
					href="/MPSCWeb/jsp/bankhome.jsp">Home</a></li>
				<li><a href="/MPSCWeb/BankBalance.do">View Balance</a></li>
				<li><a href="/MPSCWeb/ChangePassword.do">Edit Password</a></li>
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
						<%
							out.println(ga.output);
						%>

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
	</div>
	<div id="footer">
		<p>2015. MPSC Website. All rights reserved.</p>
	</div>
	<!-- end #footer -->
</body>
</html:html>
