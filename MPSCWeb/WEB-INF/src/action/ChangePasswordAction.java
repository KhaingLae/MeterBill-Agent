package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import database.login;

import service.LoginService;

import form.LoginForm;

public class ChangePasswordAction extends BaseAction{
	
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
		
		LoginForm myform=(LoginForm)form;
		String cpass=myform.getCpass();
		String npass=myform.getNpass();
		String rpass=myform.getRpass();
		String opass=myform.getPassword();
		ActionErrors errors = new ActionErrors();
		if(cpass.matches(opass)==false){
			myform.setRpass("");
			myform.setNpass("");
			myform.setCpass("");
			errors.add("wrongpass", new ActionMessage("errors.wrongpass"));
			saveErrors(request, errors);
			return "nosuccess";
		}
		else if(npass.matches(rpass)==false){
			myform.setRpass("");
			myform.setNpass("");
			myform.setCpass("");
			errors.add("mismatch", new ActionMessage("errors.mismatch"));
			saveErrors(request, errors);
			return "nosuccess";
		}
		else{
			myLoginService.ChangePass(npass, myform.getUsername());
			request.getSession().setAttribute("afterchange", 1);
			myform.setRpass("");
			myform.setNpass("");
			myform.setCpass("");
			return "success";
		}
	}
	
	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping){
		
		System.out.println("init of change password...");
		HttpSession session=request.getSession();
		session.removeAttribute("ulogin");
		session.removeAttribute("ologin");
		session.removeAttribute("blogin");
	
		LoginForm myform=(LoginForm)form;
		request.getSession().removeAttribute("afterchange");
		myform.setRpass("");
		myform.setNpass("");
		myform.setCpass("");
		
		String username=myform.getUsername();
		String pwd=myform.getPassword();
		System.out.println("username :"+username);
		System.out.println("password :"+pwd);
		List<login> loginuser=(ArrayList<login>) myLoginService.IdentifyUser(username, pwd);
		int utype=loginuser.get(0).getUsertype();
		System.out.println("usertype :"+utype);
		if(utype==4)
			session.setAttribute("ulogin", 1);
		else if(utype==2)
			session.setAttribute("ologin",1);
		else if(utype==3)
			session.setAttribute("blogin", 1);
		return "success";
	}


}
