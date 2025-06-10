package service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import database.home;
import database.login;
import database.query;

public class LoginService {

   public List<login> IdentifyUser(String uname, String pwd){
	   query q=new query();
	   List l=q.login(uname, pwd);
	   if(l.isEmpty())return null;
	   else{
		    return l;
	   }
	      
   }
   
   public void ChangePass(String pass, String uname){
	   query q=new query();
	   q.changePassword(pass, uname);
   }
   public List<String> getAllStreet() {
		// TODO Auto-generated method stub
		query q=new query();
		List<String> tl=q.selectAllStreet();
		System.out.println("Size of street in service is "+tl.size());
		return tl;
	}
   public List<String> getAllTown() {
		// TODO Auto-generated method stub
		query q=new query();
		List<String> tl=q.selectAllTown();
		System.out.println("Size of town in service is "+tl.size());
		return tl;
	}
   
   public int getMeterUnits(int loginid){
	   int units=0;
	   Calendar dd=Calendar.getInstance();
		int year=dd.get(Calendar.YEAR);
		int month=dd.get(Calendar.MONTH);
		System.out.println("year.."+year+"  month..."+month);
		query qu=new query();
		List<home> homelist=qu.getHome(loginid);
		System.out.println("loginid...."+loginid);
		System.out.println("hid......"+homelist.get(0).getHid());
		int hid=homelist.get(0).getHid();
		int tid=homelist.get(0).getTid();
		System.out.println("home id..."+hid+"....town id...."+tid);
		int tbid=qu.getTbid(tid, year, month);
		units=qu.getMeterUnits(hid,tbid);
		return units;
   }
   
   public String getExpireDate(int loginid){
	   String expdate="";
	   Calendar dd=Calendar.getInstance();
		int year=dd.get(Calendar.YEAR);
		int month=dd.get(Calendar.MONTH);
		query qu=new query();
		List<home> homelist=qu.getHome(loginid);
		int tid=homelist.get(0).getTid();
		expdate=qu.getExpireDate(tid, year, month);
		return expdate;
   }
 
   public String getPdfPath(int lid, int year, int month){
	   String path="";
	   query q=new query();
	   System.out.println("get report for "+lid+" "+year+" "+month);
	   int tid=q.getTidFromLid(lid);
	   path=q.getPdfPath(tid, year, month);
	   return path;
   }
  
   public String getReceiveDate(int lid){
	   query q=new query();
	   int hid=q.getHome(lid).get(0).getHid();
	   Calendar dd=Calendar.getInstance();
		int year=dd.get(Calendar.YEAR);
		int month=dd.get(Calendar.MONTH);
		int tid=q.getHome(lid).get(0).getTid();
		int tbid=q.getTbid(tid, year, month);
		String rdate=q.getReceiveDate(hid, tbid);
		System.out.println("Receive date..."+rdate);
		return rdate;
   }
}
