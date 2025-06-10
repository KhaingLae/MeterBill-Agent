package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import database.balance;

import form.LoginForm;

public class WebConstants {

    // HttpSession ------------------------------------------------------------------
    /** HttpSession */

     /** user bean key name */
	public static final String KEY_USERBEAN = "userBean";


    // HttpServletRequest -----------------------------------------------------------
    /** HttpServletRequest */
    public static final String ATTR_MESSAGE = "message";
    
    public static final String SESSION_SCREENNAME = "screen_name"; 
    
    // ReserveForm ------------------------------------------------------------------
    public static final String FORM_PROPERTY_IS_CHANGE = "isChange";

	static final String KEY_MESSAGE_SCREENTRANSERR = "message.error.screentransition";


	static final String SUCCESS = "success";


	public static final String KEY_BOOKDBEAN = "success";
	
	public static final Integer PAGE_SIZE = 9;
	
    
    // ------------------------------------------------------------------------------

    private WebConstants() {
        // nothing to do.
    }
    public static String processPage(HttpSession session, LoginForm myform,
			List<LoginForm> balancelist) {

		Integer p = myform.getPage();

		System.out.println("Form Page " + p);
		Integer pageSize =20;

		Integer lsize = balancelist.size();
		System.out.println("balancelistsize " + lsize);
		//session.setAttribute("listcount", lsize);
		//System.out.println("listsize - " + lsize);
		//System.out.println("myform getpage - " + myform.getPage());

		Integer max, curcs = 0, curce = 0;
		if (lsize % pageSize == 0) {
			max = (lsize /pageSize);
			System.out.println("Max " + max);
		} else {
			max = (lsize /pageSize) + 1;
		}

		List<LoginForm> temp = null;
		if (p <= 0) {
			p = 1;
			myform.setPage(1);
			if (lsize < pageSize) {
				temp = balancelist.subList(0, lsize);
				curcs = 1;
				curce = lsize;

				System.out.println("zero to 1");
			} else {
				temp = balancelist.subList(0, pageSize);
				curcs = 1;
				curce =  pageSize;
				System.out.println("else case");
			}
		} else if (p >= max) {
			p = max;
			myform.setPage(max);
			temp = balancelist.subList((max - 1) *  pageSize, lsize);
			curcs = (max - 1) *  pageSize;
			curce = lsize;
		} else {
			p = myform.getPage();
			temp = balancelist.subList((p - 1) * pageSize, p
					*  pageSize);
			myform.setPage(p);
			curcs = (p - 1) * pageSize;
			curce = p * pageSize;

		}
		// myform.setViewbookdetail(temp);

		//

		List seq = new ArrayList();
		for (int i = 1; i <= max; i++) {
			seq.add(new Integer(i));
		}
		Integer Displaycnt = 10, StartCount, EndCount;
		StartCount = p - 5;
		EndCount = p + 4;
		if (max > 2) {

			//  
			if (p < 10) {

				StartCount = 1;
				EndCount = 10;
			} else if (p == max) {

				StartCount = max - 10;
				EndCount = max;
			} else if (p > max - 6) {

				StartCount = max - 10;
				EndCount = max;
			}

		}
		List seqbr = new ArrayList();
		for (int i = StartCount; i <= EndCount; i++) {
			seqbr.add(new Integer(i));
		}
			
		
		
		
		session.setAttribute("master", myform);
		session.setAttribute("pagecount", max);
		session.setAttribute("balanceresultlist", temp);
		session.setAttribute("allist", balancelist);
		session.setAttribute("sequence", seq);
		session.setAttribute("sequencebreak", seqbr);
		session.setAttribute("current", p);
		session.setAttribute("display", Displaycnt);
		session.setAttribute("start", StartCount);
		session.setAttribute("end", EndCount);
		session.setAttribute("cshows", curcs);
		session.setAttribute("cshowe", curce);
		// end of test
		return WebConstants.SUCCESS;
	}

	
}