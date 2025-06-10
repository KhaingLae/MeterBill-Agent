package service;

import java.util.Calendar;

import database.bankquery;
import database.query;

public class PayBillService {
	
	
	public static boolean payBill(int tid, int year, int month, String lname,Integer bill,Integer percent){
		boolean flag=true;
		query q=new query();
		int tbid=q.getTbid(tid, year, month);
		int lid=q.getLidFromLname(lname);
		int hid=q.getHome(lid).get(0).getHid(); 
		bankquery bq=new bankquery();
		int accountid=bq.getAccountid(lname);
		int cbal=bq.getBalance(accountid);
		if(cbal<bill){
			flag=false;
			System.out.println("not enough money..");
		}
		else{
			int cash=cbal-bill;
			System.out.println("update cash "+cash);
			bq.updateBalance(cash, accountid);//update user account
			int mpscbal=bq.getBalance(999)+percent;//update mpsc account
			int bankbal=bq.getBalance(111)+percent;//update bank account
			bq.updateBalance(mpscbal, 999);
			bq.updateBalance(bankbal, 111);
			System.out.println("tid..."+tid);
			int tlid=q.getLidFromTid(tid);
			System.out.println("Office Login id ..."+tlid);
			String uname=q.getLnameFromLid(tlid);
			System.out.println("Office name..."+uname);
			int aid=bq.getAccountid(uname);
			System.out.println("office account id "+aid);
			int officebal=bq.getBalance(aid)+(bill-(percent+percent));
			System.out.println("transfer amount .."+officebal);
			bq.updateBalance(officebal, aid);//update meter office account
			Calendar cc=Calendar.getInstance();
			int yy=cc.get(Calendar.YEAR);
			int mm=cc.get(Calendar.MONTH)+1;
			int dd=cc.get(Calendar.DATE);
			String rdate=dd+"/"+mm+"/"+yy;
			System.out.println("Received Date : "+rdate);
			q.setReceiveDate(rdate, hid, tbid);
		}
		return flag;
	}
	
	public static int getTownid(String lname) {
		int tid = 0;
		query q = new query();
		int lid=q.getLidFromLname(lname);
		int hid=q.getHome(lid).get(0).getHid(); 
		tid=q.getTidFromHid(hid);
		return tid;
	}
	
	public static String getTownname(int tid){
		query q=new query();
		String tname=q.selectTnameByTid(tid);
		return tname;
	}
	public static String getHomename(String loginname){
		query q=new query();
		int lid=q.getLidFromLname(loginname);
		System.out.println("lid...."+lid);
		String home=q.getHome(lid).get(0).getHname();
		System.out.println("home...."+home);
		return home;
	}
}

