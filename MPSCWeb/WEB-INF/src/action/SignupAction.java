package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import form.LoginForm;
import service.SignUpService;


public class SignupAction extends BaseAction {

	SignUpService mySignUpService;
	public SignUpService getMySignUpService() {
		return mySignUpService;
	}
	public void setMySignUpService(SignUpService mySignUpService) {
		this.mySignUpService = mySignUpService;
	}
	@Override
	protected String doExecute(ActionForm form, HttpServletRequest request,
			ActionMapping mapping) throws Exception {
		// TODO Auto-generated method stub
		
		request.getSession().removeAttribute("output");
		LoginForm myform=(LoginForm)form;
		String home=myform.getHome().trim();
		System.out.println("street name in form/..."+myform.getStreetname());
		//String town=myform.getTownship().trim();
		int townid=mySignUpService.getTownid(myform.getTxttown());
		
		System.out.println("town id..."+townid);
				
		boolean f=mySignUpService.existsHome(townid, home);
		System.out.println(f);
		if(f==false){
			System.out.println("output false..");
			request.getSession().setAttribute("output", 1);
		}
		String init="";
		switch(townid){
			case 1:init="ca";break;
			case 2:init="cm";break;
			case 3:init="pg";break;
			case 4:init="am";break;	
			case 5:init="mh";break;	
		}
		String[] hname=home.split("/");
		String lname=init+hname[0].toLowerCase()+hname[1];
		myform.setLoginname(lname);
		myform.setTownid(townid);
		mySignUpService.SaveHome(myform);
		return "registersuccess";
	}
	@Override
	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping){
		
		LoginForm myform=(LoginForm)form;
		request.getSession().removeAttribute("output");
		
		return "registersuccess";
	}

}
