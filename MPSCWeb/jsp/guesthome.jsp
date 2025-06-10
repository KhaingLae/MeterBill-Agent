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
				<li class="current_page_item"><a href="/MPSCWeb/jsp/guesthome.jsp">Home</a></li>
				<li><a href="/MPSCWeb/jsp/about.jsp">About System</a></li>
			</ul>
		</div>
		<!-- end #menu -->
		
			<!-- end #content -->
			<div id="page">
				<div id="gcontent">
					<section>
							<div id="container_demo">
								<a class="hiddenanchor" id="toregister"></a> <a
									class="hiddenanchor" id="tologin"></a> 
								<div id="wrapper">
								<logic:empty name="output">
									
									<div id="login" class="animate form">
										<html:form action="/Login" method="POST"  >
											<h1>Log in</h1>
											<p>
												<label>
													username </label> <html:text  property="username"/>
											</p>
											<p>
												<label>
													password </label><html:password  property="password"/>
											</p>
											<html:errors property="nouser" />
											
											<p class="login button">
												<html:submit value="Login"/>
											</p>
											<p class="change_link">
												Not a member yet?<a href="#toregister" class="to_register">Join
													us</a>
											</p>
										</html:form>
									</div>
									
									
									<div id="register" class="animate form">
										<html:form action="/SignUp" method="POST" >
											<h1>Sign up</h1>
											<p>
												<label>home</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><small>eg. m/6</small></i>
												 <html:text property="home" 
													 />
											</p>
											
										<p>
											<label>township</label>	</p>
											<div class="select_wrapper">
											<html:select onchange="sendTown(this[this.selectedIndex].value)" property="txttown">
											<html:options property="townList"/>												
											</html:select>
											</div>
										
											<p>
											<label>block</label></p>
											<div class="select_wrapper">
											<html:select property="blockname" onchange="sendBlock(this[this.selectedIndex].value)" styleId="block" >
											</html:select>
											</div>
											
											<p>
												<label>street</label> </p>
												<div class="select_wrapper">
											<html:select  property="streetname" styleId="street" >											
											</html:select>	
											</div>
											
											<p>
												<label>password </label>
												<html:password  property="password"/>
											
											</p>
											<p class="signin button">
												<input type="submit" value="Sign up" />
											</p>
											<p class="change_link">
												Already a member?<a href="#tologin" class="to_register">Go
													and log in</a>
											</p>

										</html:form>
									</div>
									</logic:empty>
									<logic:notEmpty name="output">
									
									<div  class="animate form">
										<html:form action="/Login" method="POST"  >
											<h4>Your registration name is </h4><bean:write name="userLoginForm" property="loginname" scope="session" />!
											<p class="signin button">
												<input type="submit" value="Go to Home" />
											</p>
										</html:form>
									</div>
									</logic:notEmpty>
									</div>
							</section>
						
						</div>
					
			</div>
			<div id="footer">
				<p>2015. MPSC Website. All rights reserved.</p>
			</div>
			<!-- end #footer -->
</body>
</html:html>
