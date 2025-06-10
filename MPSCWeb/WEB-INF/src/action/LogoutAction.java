package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.request.SessionScope;

import agent.BankAgent;
import agent.ClientAgent;

import service.LoginService;

import form.LoginForm;



public class LogoutAction extends BaseAction {
	LoginService myLoginService;
	

	public LoginService getMyLoginService() {
		return myLoginService;
	}

	public void setMyLoginService(LoginService myLoginService) {
		this.myLoginService = myLoginService;
	}

	@Override
	protected String doExecute(ActionForm form, HttpServletRequest request,
			ActionMapping mapping) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("in do execute in  logoust");
		
		return "success";
	}
	
	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping) {
		
		System.out.println("in doinit in  logoust");
		HttpSession session2 = request.getSession();
		BankAgent ag = (BankAgent)session2.getAttribute("myag");
		ClientAgent ca=(ClientAgent)session2.getAttribute("myca");
		if(ag!=null)ag.doDelete();
		if(ca!=null){
			System.out.println("Deleting agent...."+ca.getLocalName());
			ca.doDelete();	
			
		}
		if (session2!=null){
		    session2.invalidate();
		}	
		
		return "success";
	}

}
