package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import jade.*;
import jade.wrapper.*;
import jade.lang.acl.*;
import jade.core.behaviours.OneShotBehaviour;
import tweb.JadeBridgeLogger;
import agent.LoggerAgent;
import tweb.JadeBridgeSubscriber;
import agent.SubscriberAgent;
import action.globaldata;

public final class adminhome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fhtml;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fbean_005fwrite_005fscope_005fproperty_005fname_005fnobody;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fhtml_005fhtml = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fbean_005fwrite_005fscope_005fproperty_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fhtml_005fhtml.release();
    _005fjspx_005ftagPool_005fbean_005fwrite_005fscope_005fproperty_005fname_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

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

      out.write('\r');
      out.write('\n');
      //  html:html
      org.apache.struts.taglib.html.HtmlTag _jspx_th_html_005fhtml_005f0 = (org.apache.struts.taglib.html.HtmlTag) _005fjspx_005ftagPool_005fhtml_005fhtml.get(org.apache.struts.taglib.html.HtmlTag.class);
      _jspx_th_html_005fhtml_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005fhtml_005f0.setParent(null);
      int _jspx_eval_html_005fhtml_005f0 = _jspx_th_html_005fhtml_005f0.doStartTag();
      if (_jspx_eval_html_005fhtml_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("<head>\r\n");
          out.write("<meta name=\"keywords\" content=\"\" />\r\n");
          out.write("<meta name=\"description\" content=\"\" />\r\n");
          out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\r\n");
          out.write("<title>Meter Payment Service Center</title>\r\n");
          out.write("<link href=\"http://fonts.googleapis.com/css?family=Abel\"\r\n");
          out.write("\trel=\"stylesheet\" type=\"text/css\" />\r\n");
          out.write("<link href=\"/MPSCWeb/css/style.css\" rel=\"stylesheet\" type=\"text/css\"\r\n");
          out.write("\tmedia=\"screen\" />\r\n");
          out.write("<link href=\"/MPSCWeb/css/login.css\" rel=\"stylesheet\" type=\"text/css\"\r\n");
          out.write("\tmedia=\"screen\" />\r\n");
          out.write("<link href=\"/MPSCWeb/css/animate-custom.css\" rel=\"stylesheet\"\r\n");
          out.write("\ttype=\"text/css\" media=\"screen\" />\r\n");
          out.write("\r\n");
          out.write("<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\"></script>\r\n");
          out.write("<script type=\"text/javascript\" src=\"jquery.slidertron-1.0.js\"></script>\r\n");
          out.write("</head>\r\n");
          out.write("<body style=\"height:500\">\r\n");
          out.write("\r\n");
          out.write("\t<div id=\"header-wrapper\">\r\n");
          out.write("\t\t<div id=\"header\">\r\n");
          out.write("\t\t\t<div id=\"logo\">\r\n");
          out.write("\t\t\t\t<h2><a>MPSC</a></h2>\r\n");
          out.write("\t\t\t\t<p>Meter Payment Service Center</p>\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("\t\t</div>\r\n");
          out.write("\t</div>\r\n");
          out.write("\t<!-- end #header -->\r\n");
          out.write("\t<div id=\"menu\">\r\n");
          out.write("\t\t<ul>\r\n");
          out.write("\t\t\t<li class=\"current_page_item\"><a href=\"/MPSCWeb/jsp/adminhome.jsp\">Home</a></li>\r\n");
          out.write("\t\t\t<li><a href=\"/MPSCWeb/jsp/meteroffice.jsp\">Meter Office</a></li>\r\n");
          out.write("\t\t\t<li><a href=\"/MPSCWeb/jsp/enduser.jsp\">End User</a></li>\r\n");
          out.write("\t\t\t<li><a href=\"/MPSCWeb/jsp/bank.jsp\">Bank</a></li>\r\n");
          out.write("\t\t\t<li><a href=\"/MPSCWeb/getEvaluation.do\">Evaluation</a></li>\r\n");
          out.write("\t\t\t<li><a href=\"/MPSCWeb/Logout.do\">Log Out</a></li>\r\n");
          out.write("\t\t</ul>\r\n");
          out.write("\t</div>\r\n");
          out.write("\t<!-- end #menu -->\r\n");
          out.write("\t<div id=\"page\">\r\n");
          out.write("\t\t<div id=\"content\">\r\n");
          out.write("\t\r\n");
          out.write("\t\t\t<div class=\"post\">\r\n");
          out.write("\t\t\t\t\t<h2 class=\"title\">\r\n");
          out.write("\t\t\t\t\t\t<a href=\"#\">Welcome ");
          if (_jspx_meth_bean_005fwrite_005f0(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("!</a>\r\n");
          out.write("\t\t\t\t\t</h2>\r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\t\t\r\n");
          out.write("\t\t\t\t<div class=\"entry\">\r\n");
          out.write("\t\t\t\t<h3>Current Running Agent</h3>\r\n");
          out.write("\t\t\t\t<center>\r\n");
          out.write("\t\t\t\t\t\t");

							out.println(la.print);
							
						
          out.write("\r\n");
          out.write("\t\t\t\t\t\t</center>\r\n");
          out.write("\t\t\t\t\t\t<a href=\"/MPSCWeb/jsp/createlog.jsp\">\r\n");
          out.write("\t\t\t\t\t\t<center>\r\n");
          out.write("\t\t\t\t\t\t<table width=\"300\"><tr><td>\r\n");
          out.write("\t\t\t\t\t\t\t<p class=\"login button\">\r\n");
          out.write("\t\t\t\t\t\t\t\t<input type=\"submit\" value=\"Generate Record\" />\r\n");
          out.write("\t\t\t\t\t\t\t</p>\r\n");
          out.write("\t\t\t\t\t\t\t</td></tr>\r\n");
          out.write("\t\t\t\t\t\t</table>\t\r\n");
          out.write("\t\t\t\t\t\t</center>\r\n");
          out.write("\t\t\t\t\t\t</a>\r\n");
          out.write("\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("\t\t\t<div style=\"clear: both;\">&nbsp;</div>\r\n");
          out.write("\t\t</div>\r\n");
          out.write("\t\t<!-- end #content -->\r\n");
          out.write("\t\t<div id=\"sidebar-bg\">\r\n");
          out.write("\t\t\t<div id=\"sidebar\">\r\n");
          out.write("\t\t\t\t<ul>\r\n");
          out.write("\t\t\t\t\t<li>\r\n");
          out.write("\t\t\t\t\t\t<h2>About MPSC</h2>\r\n");
          out.write("\t\t\t\t\t\t<p>MPSC is meter payment service center to communicate meter offices, end-users and their respective banks in order to easily make meter bill payment process.</p>\r\n");
          out.write("\t\t\t\t\t</li>\r\n");
          out.write("\t\t\t\t\t\r\n");
          out.write("\t\t\t\t</ul>\t\r\n");
          out.write("\t\t\t\t\t\t\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("\t\t</div>\r\n");
          out.write("\t\t<!-- end #sidebar -->\r\n");
          out.write("\t\t<div style=\"clear: both;\">&nbsp;</div>\r\n");
          out.write("\t</div>\r\n");
          out.write("\t<!-- end #page -->\r\n");
          out.write("\r\n");
          out.write("<div id=\"footer\">\r\n");
          out.write("\t\t\t\t<p>2015. MPSC Website. All rights reserved.</p>\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("<!-- end #footer -->\r\n");
          out.write("</body>\r\n");
          int evalDoAfterBody = _jspx_th_html_005fhtml_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_html_005fhtml_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fhtml_005fhtml.reuse(_jspx_th_html_005fhtml_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fhtml_005fhtml.reuse(_jspx_th_html_005fhtml_005f0);
      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_bean_005fwrite_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f0 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_005fscope_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f0.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fwrite_005f0.setName("userLoginForm");
    _jspx_th_bean_005fwrite_005f0.setProperty("username");
    _jspx_th_bean_005fwrite_005f0.setScope("session");
    int _jspx_eval_bean_005fwrite_005f0 = _jspx_th_bean_005fwrite_005f0.doStartTag();
    if (_jspx_th_bean_005fwrite_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_005fscope_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_005fscope_005fproperty_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
    return false;
  }
}
