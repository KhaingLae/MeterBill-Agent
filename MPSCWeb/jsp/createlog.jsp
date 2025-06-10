<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@page import="javax.swing.border.TitledBorder"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.lowagie.text.DocumentException"%>
<%@page import="com.lowagie.text.Paragraph"%>
<%@page import="com.lowagie.text.html.HtmlWriter"%>
<%@page import="com.lowagie.text.pdf.PdfWriter"%>
<%@page import="com.lowagie.text.Document"%>
<%@page import="java.io.*"%>;
<%@page import="java.awt.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="com.lowagie.text.*"%>
<%@page import="com.lowagie.text.pdf.*"%>
<%@page import="com.lowagie.text.html.*"%>
<%@page import="database.DBConnector"%>
<%@page import="java.awt.*"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="com.lowagie.text.*"%>
<%@page import="com.lowagie.text.pdf.*"%>
<%@page import="com.lowagie.text.html.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tweb.JadeBridgeLogger"%>
<%@page import="agent.LoggerAgent"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar" %>;
<%
String str="pdf";

Connection conn=null;
PreparedStatement ps=null;
ResultSet rs=null;
try
{ 
    Document document=new Document();
    if(str.equals("pdf"))
    {
        response.setContentType("application/pdf");
        PdfWriter.getInstance(document,response.getOutputStream());
    }
    
	document.open();
	LoggerAgent la = (LoggerAgent) session.getAttribute("mylg");
	String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
	
    /* new paragraph instance initialized and add function write in pdf file*/
    document.add(new Paragraph("---------------------------------------------------------RECORD---------------------------------------------------------\n\n"));
    document.add(new Paragraph("                                                      	 Pay Agents"));
    document.add(new Paragraph("                                               "+"Time     :      "+ timeStamp));
    document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
    document.addCreationDate();
   
	
	document.add(new Paragraph(la.print));
	
	
    
    document.close(); //document instance closed
    conn.close();  //db connection close
}
catch(Exception de) 
{
        de.printStackTrace();
            System.err.println("document: " + de.getMessage());
            
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
				<li class="current_page_item"><a href="#">Home</a></li>
				<li><a href="/MPSCWeb/jsp/meteroffice.jsp">Meter Office</a></li>
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
					
					<div style="clear: both;">&nbsp;</div>
			
			</div>
					<!-- end #content -->
					<div id="sidebar-bg">
						<div id="sidebar">
							<ul>
								<li>
									<h2>About MPSC</h2>
									<p>MPSC is meter payment service center to communicate
										meter offices, end-users and their respective banks in order
										to easily make meter bill payment process.</p>
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
					
