package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import database.balance;

import form.LoginForm;

import service.ChangeMonth;
import service.CheckBalanceService;
import service.SignUpService;

public class CheckBalanceAction extends BaseAction {
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
	
		System.out.println("do execute of check balance action..");
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
		List<balance> balancelist = new ArrayList<balance>();
		List<LoginForm> balanceformlist = new ArrayList<LoginForm>();
		LoginForm resultform;
		if (myform.getPage() == 0) {
			balancelist = myCheckBalanceService.getBalance(townid, year, month);
			for (balance bal : balancelist) {
				resultform = new LoginForm();
				resultform.setHome(bal.getHname());
				resultform.setBill(bal.getBill());
				resultform.setReceive(bal.getReceivedate());
				balanceformlist.add(resultform);
			}
			session.setAttribute("resultForm", balanceformlist);
			return WebConstants.processPage(session, myform, balanceformlist);
		} else {
			balanceformlist = (List<LoginForm>) request.getSession()
					.getAttribute("resultForm");
			return WebConstants.processPage(session, myform, balanceformlist);
		}
	
	}
	
	

}
