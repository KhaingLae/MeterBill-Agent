package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import service.LoginService;

import database.login;
import form.LoginForm;

public class ContactAction extends BaseAction{
	
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
		return null;
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
