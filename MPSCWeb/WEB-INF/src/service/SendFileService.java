package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import database.query;

import util.UploadFileUtil;

public class SendFileService {

	public void getUploadedFile(String a_strAbsoluteFile, FormFile strutsFile)
			throws FileNotFoundException, IOException {

		UploadFileUtil util = new UploadFileUtil();
		util.write(a_strAbsoluteFile, strutsFile);

		return;
	}
	
	public void saveReport(String path,int tid, int year,int month){
		query q=new query();
		q.saveReportPath(path, tid, year, month);
	}
	
	public int saveFile(int tid, int year, int month){
		query q=new query();
		int ymonth=month+1;
		int yyear=year;
		if(ymonth>12){
			ymonth=1;
			yyear++;
		}
		String expire="25/"+ymonth+"/"+yyear;
		q.saveFile(tid, month, year,expire);
		int tbid=q.getTbid(tid, year, month);
		return tbid;
	}
	
	public int getTid(String lname){
		query q=new query();
		int lid=q.getLidFromLname(lname);
		int tid=q.getTidFromLid(lid);
		return tid;
	}
	public void saveFileDetail(ArrayList<String> h,ArrayList<Integer> u,int tid, int tbid){
		query q=new query();
		int hid=0;
		for(int i=0;i<h.size();i++){
			hid=q.selectNoByHomeNameTown(h.get(i), tid);
			
			int totalbill=0;
			int totalunits=u.get(i);
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
			q.saveFileDetail(hid, u.get(i),tbid,totalbill);
		}
		
	}
	
}
