package action;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import service.SendFileService;
import util.UploadFileUtil;
import form.BillForm;

public class SendReportAction  extends BaseAction {
	SendFileService mySendFileService;
	public SendFileService getMySendFileService() {
		return mySendFileService;
	}

	public void setMySendFileService(SendFileService mySendFileService) {
		this.mySendFileService = mySendFileService;
	}

	@Override
	protected String doExecute(ActionForm form, HttpServletRequest request,
			ActionMapping mapping) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("do execute of send report action...");
		BillForm myform=(BillForm)form;
		HttpSession session=request.getSession();
		FormFile upfile=myform.getUpfile();
		System.out.println("upfile name...."+upfile.getFileName());
		String tname=(String)session.getAttribute("tname");
		System.out.println("town name...."+tname);
		Integer tid=(Integer)session.getAttribute("town");
		Integer year=(Integer)session.getAttribute("year");
		Integer month=(Integer)session.getAttribute("month");
		System.out.println(tid+" "+year+" "+month);
		String newname=tid.toString()+year.toString()+month.toString();
		UploadFileUtil util = new UploadFileUtil();
		
		String filePath = "/reports/" +newname ;
		String strFileWithDir = util.getFilePath(filePath);
		
		//myform.setFilepath(filePath);
	
		String strFileWithAbsolute = getServlet().getServletContext()
				.getRealPath(strFileWithDir);
	
		System.out.println("FileSendAction;uploadFilePath="
				+ strFileWithAbsolute );

		// call service to upload the file
		try {

			if (!filePath.equals("/MPSCWeb/reports/")) {
				mySendFileService.getUploadedFile(
						strFileWithAbsolute, upfile);
			}
			
			// case error, show error msg to the user
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mySendFileService.saveReport("/MPSCWeb"+filePath, tid, year, month);
		return "sendsuccess";
	}

	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping){
		
		BillForm myform=(BillForm)form;
		return "success";
	}
}
