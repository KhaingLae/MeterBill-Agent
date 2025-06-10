package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import form.LoginForm;


public abstract class BaseAction extends Action {

	private static final Log LOG = LogFactory.getLog(BaseAction.class);

	
	@Override
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("in execute of BaseAction");
		System.out.println(request.getMethod().toString());
		if (isCheckScreenTransition(request)) {

			if (!isValidScreenTransition(request)) {
				throw new RuntimeException("screen transition not allowed.");
			}
		}
		// set screen name to the session
		if (request.getParameter(WebConstants.SESSION_SCREENNAME) != null) {
			request.getSession().setAttribute(WebConstants.SESSION_SCREENNAME,
					request.getParameter(WebConstants.SESSION_SCREENNAME));
		}
		if (request.getMethod().toLowerCase().equals("get")) {
			System.out.println("in get of baseaction");
			String strForward = doInit(form, request, mapping);
			return mapping.findForward(strForward);
		}
		// transfer control to each action
		String name = doExecute(form, request, mapping);

		// save token to control ilegal screen transition
		saveToken(request);

		// show the jsp returned by each action
		return mapping.findForward(name);
	}
	
	
	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping) {
		System.out.println("in doinit of baseaction");
		
		
		
		return "success";
	}

	
	protected boolean isRequiredLogin(HttpServletRequest request) {
		return true;
	}

	
	protected boolean isCheckScreenTransition(HttpServletRequest request) {
		return false;
	}

	
	protected boolean isValidScreenTransition(HttpServletRequest request) {
		return isTokenValid(request);
	}

	
	protected abstract String doExecute(ActionForm form,
			HttpServletRequest request, ActionMapping mapping) throws Exception;

	
	protected String getMessage(HttpServletRequest request, String key) {
		return getResources(request).getMessage(key);
	}
	protected LoginForm getUserBean(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return (LoginForm) session.getAttribute(WebConstants.KEY_USERBEAN);

	}
	
	protected void setUserBean(HttpServletRequest req, LoginForm userBean) {
		HttpSession session = req.getSession();
		session.setAttribute(WebConstants.KEY_USERBEAN, userBean);
	//	session.setAttribute("namelabel",userBean.getName());

	}

}