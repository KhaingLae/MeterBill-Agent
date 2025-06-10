package action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import service.ChangeMonth;
import service.SendFileService;
import util.UploadFileUtil;

import form.BillForm;
import form.LoginForm;

public class SendFileAction extends BaseAction {

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
		BillForm myform=(BillForm)form;
		String year=myform.getYear();
		String month=myform.getMonth();
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("loginname");
		System.out.println("user..."+username);
		String newname=username+month+year;
		System.out.println("saving new name..."+newname);
		FormFile upfile=myform.getUpfile();
		
		UploadFileUtil util = new UploadFileUtil();
		
		String filePath = "/files/" +newname ;
		String strFileWithDir = util.getFilePath(filePath);
		
		myform.setFilepath(filePath);
	
		String strFileWithAbsolute = getServlet().getServletContext()
				.getRealPath(strFileWithDir);
	
		System.out.println("FileSendAction;uploadFilePath="
				+ strFileWithAbsolute );

		// call service to upload the file
		try {

			if (!filePath.equals("/MPSCWeb/files/")) {
				mySendFileService.getUploadedFile(
						strFileWithAbsolute, upfile);
			}
			Scanner scan = new Scanner(new File(strFileWithAbsolute)); 
			ArrayList<String> homes = new ArrayList<String>();
		    ArrayList<Integer> units = new ArrayList<Integer>();
		    while(scan.hasNext()){
		        String curLine = scan.nextLine();
		        String[] splitted = curLine.split("\t");
		        String home = splitted[0].trim();
		        String unit = splitted[1].trim();
		        if(!"Home_No".equals(home)){
		        	homes.add(home);
		        }
		        if(!"Units".equals(unit)){
		            units.add(Integer.parseInt(unit));
		        }
		    }
		    System.out.println(homes);
		    System.out.println(units);
		    int tid=mySendFileService.getTid(username);
		    ChangeMonth cm = new ChangeMonth();
			int monthh = cm.StringtoInt(month);
		    int tbid=mySendFileService.saveFile(tid, Integer.parseInt(year), monthh);
		    mySendFileService.saveFileDetail(homes, units,tid,tbid);
		    scan.close();
			 
			// case error, show error msg to the user
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sendsuccess";
	}

	protected String doInit(ActionForm form, HttpServletRequest request,
			ActionMapping mapping){
		
		BillForm myform=(BillForm)form;
		myform.setMonth("");
	
		
		return "success";
	}

}
