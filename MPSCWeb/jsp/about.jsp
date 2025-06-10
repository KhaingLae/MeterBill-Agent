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
	
<script src='dwr/interface/AjaxSearchTown.js'></script>
<script src='dwr/interface/AjaxSearchBlock.js'></script>
<script src='dwr/engine.js'></script>
<script src='dwr/util.js'></script>
<script type="text/javascript"><!--
	// <![CDATA[
           
	  //communicate with server to search subcategory
	
	         function sendTown(txttown) {  
		  		  AjaxSearchTown.getByTown(txttown,callbackCat);         
	     		 }
	     		
	     		// when subcategory is obtained from server
	     		function callbackCat(message) { 
	     			dwr.util.removeAllOptions("block");    
	     				   			   	
	         	dwr.util.addOptions("block",message); 
	         	
	     		}	
	     		
	     		function sendBlock(blockname) {  
			  		  AjaxSearchBlock.getByBlock(blockname,callbackBlock);         
		     		 }
		     		
		     		// when subcategory is obtained from server
		     		function callbackBlock(message) { 
		     			dwr.util.removeAllOptions("street");    
		     			   			   	
		         	dwr.util.addOptions("street",message); 
		         	
		     		}	
	     			
	     		
	     		

		// ]]>
	    --></script>
</head>
<body onload="DWRUtil.useLoadingMessage(); createAutoCompleter()">
	
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
				<li><a href="/MPSCWeb/jsp/guesthome.jsp">Home</a></li>
				<li class="current_page_item"><a href="/MPSCWeb/jsp/about.jsp">About System</a></li>
			</ul>
		</div>
		<!-- end #menu -->
		<div id="page">
			<div id="gcontent">
				<center>
								<img src="/MPSCWeb/css/images/front.jpg" width="590"
									height="300" alt="" />
					</center>		

					<h2 class="title">
						<a href="#">Welcome to MPSC</a>
					</h2>

					<div class="entry">
						<p>The recent development of internet and its related
							technologies have created a new channel for commerce, and hence
							also billing system. A consumer can use internet connection to
							pay their electricity usage of charges for a wide range of
							services instead of with cash, cheque, or credit cards. In this
							system, end users can pay for their electricity usage of meter in
							homes, offices or companies in his residence easily and
							comfortably. The system includes four collaborating actors: Meter
							office, Meter Payment Service Center (MPSC), Bank and end-user.
							Agents are used to exchange messages between MPSC, Banks and
							users in implementing billing system. Java Agent Development
							Framework (JADE) is used to develop multi-agents in implementing
							meter billing process.</p>
						<center> <img src="/MPSCWeb/css/images/overall.png"
							alt="pic1" width="500px" height="300px" align="absmiddle" /></center>


					</div>
				</div>
				
			
			<!-- end #content -->
			
				</div>
				<!-- end #page -->
			
			<div id="footer">
				<p>2015. MPSC Website. All rights reserved.</p>
			</div>
			<!-- end #footer -->
</body>
</html:html>
