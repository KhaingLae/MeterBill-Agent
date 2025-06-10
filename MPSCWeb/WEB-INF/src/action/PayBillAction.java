package action;

import java.util.ArrayList;
import java.util.List;

import jade.BootProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import agent.BankAgent;
import agent.ClientAgent;

import form.LoginForm;

import service.ChangeMonth;
import service.PayBillService;
import tweb.JspJadeAg;


public class PayBillAction extends BaseAction{

	@Override
	protected String doExecute(ActionForm form, HttpServletRequest request,
			ActionMapping mapping) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("in execute of pay bill action......");
		HttpSession session=request.getSession();
		
		String loginname=(String) session.getAttribute("loginname");
		ServletContext application=session.getServletContext();
		JspJadeAg ag = (JspJadeAg)session.getAttribute("myag"); 
		
		LoginForm mform=(LoginForm)form;
		int bill=mform.getActualbill();
		int percent=mform.getPercent();
		String year=mform.getYear();
		String month=mform.getMonth();
		
	
		try {    
		    if (ag == null) {
		    	 ContainerController cc = (ContainerController)application.getAttribute("cc");
		    	 if(cc.getAgent("BankAgent")==null){
		    		 	session.setAttribute("noagent", 1);
		    		 	return "success";
		    	 }
		        if (cc == null) {
		            // creates the main container
		            String host = "127.0.0.1";
		            int port = 4457;
		            System.out.println("starting jade container at "+host+" and port "+port+"<br/>");
		            String [] args = {"local-port:"+port, "host:"+host}; //,"-container"};
		            cc = jade.core.Runtime.instance().createMainContainer(new BootProfileImpl(args));
		            application.setAttribute("cc", cc);
		            System.out.println("Container for client started!");
		        }
		       
		        // creates a test agent that sends messages to snooper
		        Long bf=System.currentTimeMillis();
		        AgentController bob = cc.createNewAgent(loginname, "agent.ClientAgent", new Object[] {bill,year,month,loginname,session,percent});
		        bob.start();
		        Long af=System.currentTimeMillis();
		        globaldata gb=new globaldata();
		        gb.ua=af-bf;
		        System.out.println("Client Agent Started !");
		        System.out.println("Total Time : "+gb.ua+"ms");	      
		        
		    }
		} catch (Exception ex) {
		       System.out.println(ex);
		}
		
		 return "success";
	}
	
	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping){
		
		HttpSession session=request.getSession();
		session.removeAttribute("noagent");
		session.removeAttribute("enough");
		session.removeAttribute("noenough");
		session.removeAttribute("wait");
		return "success";
	}

}
