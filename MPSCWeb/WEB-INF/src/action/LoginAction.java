package action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.taglibs.standard.lang.jpath.adapter.Convert;

import database.login;
import database.query;

import form.LoginForm;

import service.ChangeMonth;
import service.LoginService;

public class LoginAction extends BaseAction  {
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
		
		request.getSession().removeAttribute("output");
		
		LoginForm myform=(LoginForm)form;
		String username=null;
		if(myform.getLoginname()==null)
		username=myform.getUsername().trim();
		else
			username=myform.getLoginname().trim();
		
		HttpSession session=request.getSession();
		session.setAttribute("loginname", username);
		String pwd=myform.getPassword().trim();
			
		myform.setUsername(username);
		int usertype=0;int lid=0;
		List<login> loginuser=(ArrayList<login>) myLoginService.IdentifyUser(username, pwd);
		System.out.println("login user information..........");
		if(loginuser==null){
			ActionErrors errors = new ActionErrors();
			myform.setUsername("");
			myform.setPassword("");
			errors.add("nouser", new ActionMessage("errors.nouser"));
			saveErrors(request, errors);
			return "nosuccess";
		}
		for(login l: loginuser){
		System.out.println(l.getLid());
		System.out.println(l.getUsername());
		System.out.println(l.getUsertype());
		usertype=l.getUsertype();
		lid=l.getLid();
		}
		
		//session.setAttribute("user", username);
		if(usertype==4){
			int totalunits=myLoginService.getMeterUnits(lid);
			System.out.println("total units..."+totalunits);
			String expdate=myLoginService.getExpireDate(lid);
			System.out.println("expire date..."+expdate);
			int totalbill=0;
			int first=0,second=0,third=0;
			if(totalunits>200){			
				first=100*35;
				second=100*40;
				third=(totalunits-200)*50;						
			}
			else if(totalunits>100){
				first=100*35;
				second=(totalunits-100)*40;;
			}
			else{
				first=totalunits*35;
			}
			
			totalbill=first+second+third;
			System.out.println(first+"+"+second+"+"+third+"="+totalbill);
			
			if(totalbill==0)session.setAttribute("wait",1);
			
			int percentage=(totalbill/100)*5;
			int actualbill=totalbill+percentage+percentage;
			
			myform.setActualbill(actualbill);
			myform.setPercent(percentage);
			myform.setUnits(totalunits);
			myform.setFirst(first);
			myform.setSecond(second);
			myform.setThird(third);
			myform.setTotal(totalbill);
			myform.setExpire(expdate);			
			
			Calendar dd=Calendar.getInstance();
			int year=dd.get(Calendar.YEAR);
			int month=dd.get(Calendar.MONTH);
			int date=dd.get(Calendar.DATE);
			String cyear=Convert.toString(year);
			ChangeMonth cm=new ChangeMonth();
			String cmonth=cm.InttoString(month);								
			myform.setYear(cyear);
			myform.setMonth(cmonth);
			
			if(myLoginService.getReceiveDate(lid).equals("")==false){
				session.setAttribute("enough", 1);
			}
			if(date>17)session.setAttribute("expire", 1);
			return "loginsuccess";
		}
		else if(usertype==2){
			Calendar dd=Calendar.getInstance();
			int year=dd.get(Calendar.YEAR);
			int month=dd.get(Calendar.MONTH)+1;
			String path="";
			if(month==1){
				month=12;
				year--;
			}
			else month--;
			path=myLoginService.getPdfPath(lid, year, month);
			
			if(path!=null){
				myform.setReport(path);
			}
			return "officesuccess";
		}
		else if(usertype==1)
			return "adminsuccess";
		else if (usertype==3){
				return "banksuccess";
		}
		else return "nosuccess";
	}

	

	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping){
		
		LoginForm myform=(LoginForm)form;
		request.getSession().removeAttribute("enough");
		request.getSession().removeAttribute("noenough");
		request.getSession().removeAttribute("wait");
		request.getSession().removeAttribute("expire");
		myform.setUsername("");
		myform.setPassword("");
		List<String> townList = new ArrayList<String>();
		//List<String> streetList = new ArrayList<String>();
		townList.add("Please select one township");
		townList.addAll(myLoginService.getAllTown());
		/*streetList=myLoginService.getAllStreet();
		System.out.println("town size " + townList.size());
		System.out.println("street size " + streetList.size());*/
		myform.setTownList(townList);
	//	myform.setStreetList(streetList);
		return "success";
	}

}
