package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import service.ChangeMonth;
import service.CheckBalanceService;
import database.balance;
import database.userbill;
import form.LoginForm;

public class ViewUsersAction extends BaseAction{

CheckBalanceService myCheckBalanceService;
	

	public CheckBalanceService getMyCheckBalanceService() {
		return myCheckBalanceService;
	}

	public void setMyCheckBalanceService(
			CheckBalanceService myCheckBalanceService) {
		this.myCheckBalanceService = myCheckBalanceService;
	}

	
	@Override
	protected String doExecute(ActionForm form, HttpServletRequest request,
			ActionMapping mapping) throws Exception {
	
		System.out.println("do execute of view users action..");
		LoginForm myform = (LoginForm) form;
		HttpSession session = request.getSession();
		int year = Integer.parseInt(myform.getYear());
		String m = myform.getMonth();
		ChangeMonth cm = new ChangeMonth();
		int month = cm.StringtoInt(m);
		System.out.println("Town :"+myform.getTxttown());
		int townid = myCheckBalanceService.getTownid(myform.getTxttown());
		System.out.println("year:" + year + " month:" + month + " townid:"
				+ townid);
		session.setAttribute("tname",myform.getTxttown());
		session.setAttribute("syear", myform.getYear());
		session.setAttribute("smonth", myform.getMonth());
		session.setAttribute("town",townid);
		session.setAttribute("year", year);
		session.setAttribute("month",month);
		List<userbill> userlist = new ArrayList<userbill>();
		List<LoginForm> balanceformlist = new ArrayList<LoginForm>();
		LoginForm resultform;
		
		if (request.getParameter("remain") != null) {
		    // Invoke FirstServlet's job here.
			
			if (myform.getPage() == 0) {
				userlist = myCheckBalanceService.getUserList(townid, year, month);
				if(userlist.isEmpty()==true){
					session.setAttribute("empty", 1);
					session.removeAttribute("paid");
					session.removeAttribute("remain");
					session.removeAttribute("resultForm");
					return "success";
				}
				for (userbill bal : userlist) {
					resultform = new LoginForm();
					resultform.setHome(bal.getHname());
					resultform.setBill(bal.getBill());
					resultform.setUnits(bal.getUnits());
					balanceformlist.add(resultform);
				}
				session.setAttribute("resultForm", balanceformlist);
				session.setAttribute("remain",1);
				session.removeAttribute("paid");
				session.removeAttribute("empty");
				return WebConstants.processPage(session, myform, balanceformlist);
			} else {
				balanceformlist = (List<LoginForm>) request.getSession()
						.getAttribute("resultForm");
				return WebConstants.processPage(session, myform, balanceformlist);
			}
		} else if (request.getParameter("paid") != null) {
		    // Invoke SecondServlet's job here.
			
			if (myform.getPage() == 0) {
				userlist = myCheckBalanceService.getPaidUserList(townid, year, month);
				if(userlist.isEmpty()==true){
					session.setAttribute("empty", 1);
					session.removeAttribute("paid");
					session.removeAttribute("remain");
					session.removeAttribute("resultForm");
					return "success";
				}
				for (userbill bal : userlist) {
					resultform = new LoginForm();
					resultform.setHome(bal.getHname());
					resultform.setBill(bal.getBill());
					resultform.setUnits(bal.getUnits());
					resultform.setReceive(bal.getReceiveDate());
					balanceformlist.add(resultform);
				}
				session.setAttribute("resultForm", balanceformlist);
				session.setAttribute("paid", 1);
				session.removeAttribute("remain");
				session.removeAttribute("empty");
				return WebConstants.processPage(session, myform, balanceformlist);
			} else {
				balanceformlist = (List<LoginForm>) request.getSession()
						.getAttribute("resultForm");
				return WebConstants.processPage(session, myform, balanceformlist);
			}
		}
		
		else return "success";
		
	
	}
	
	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping){
		return "success";
	}
	
	
}
