package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import database.balance;
import database.userbill;
import form.LoginForm;

import service.ChangeMonth;
import service.CheckBalanceService;

public class BillPaymentAction extends BaseAction{
	
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
		// TODO Auto-generated method stub
		System.out.println("do execute of view bill payment action..");
		LoginForm myform = (LoginForm) form;
		HttpSession session = request.getSession();
		int year = Integer.parseInt(myform.getYear());
		String m = myform.getMonth();
		ChangeMonth cm = new ChangeMonth();
		int month = cm.StringtoInt(m);		
		String uname=myform.getUsername();
		int townid=myCheckBalanceService.getTownidByLname(uname);
		int total=myCheckBalanceService.getTotalIncome(townid, year, month);
		session.setAttribute("total", total);
		List<userbill> balancelist = new ArrayList<userbill>();
		List<LoginForm> balanceformlist = new ArrayList<LoginForm>();
		LoginForm resultform;
		if (myform.getPage() == 0) {
			balancelist = myCheckBalanceService.getBillPayment(uname, year, month);
			for (userbill bal : balancelist) {
				resultform = new LoginForm();
				resultform.setHome(bal.getHname());
				resultform.setBill(bal.getBill());
				resultform.setReceive(bal.getReceiveDate());
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
