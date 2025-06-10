package action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import form.LoginForm;

public class WelcomeAction {
	protected String doExecute(ActionForm form, HttpServletRequest request,
			ActionMapping mapping) throws Exception {
		// TODO Auto-generated method stub
		
		
		return "success";
	}
	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping){
		
		LoginForm myform=(LoginForm)form;
		myform.setUsername("");
		myform.setPassword("");
		
		return "success";
	}

}
