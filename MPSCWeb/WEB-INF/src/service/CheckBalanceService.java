package service;

import java.util.ArrayList;
import java.util.List;

import database.balance;
import database.query;
import database.userbill;

public class CheckBalanceService {
	
	
	
	public List<userbill> getBillPayment(String lname, int year, int month) {
		query q = new query();
		int lid=q.getLidFromLname(lname);
		int tid=q.getTidFromLid(lid);
		int tbid = q.getTbid(tid, year, month);
		List<userbill> ulist = new ArrayList<userbill>();
		List<userbill> unewlist = new ArrayList<userbill>();
		ulist = q.getUserBill(tbid);

		for (userbill b : ulist) {
			int hid = b.getHid();
			String hname = q.selectHomeNameByNo(hid);
			System.out.println("receive...."+b.getReceiveDate());
			if (!b.getReceiveDate().equalsIgnoreCase("")) {
				System.out.println("enter view bill payment user");
				userbill nbal = new userbill(hname, b.getBill(), b.getUnits(),b.getReceiveDate());
				System.out.println("...."+nbal.getBill());
				unewlist.add(nbal);
			}
		}
		return unewlist;
	}

	public List<balance> getBalance(int tid, int year, int month) {
		query q = new query();
		int tbid = q.getTbid(tid, year, month);
		List<balance> blist = new ArrayList<balance>();
		List<balance> bnewlist = new ArrayList<balance>();
		blist = q.getBalanceByTown(tbid);
		for (balance b : blist) {
			int hid = b.getHid();
			String hname = q.selectHomeNameByNo(hid);
			balance nbal = new balance(hname, b.getBill(), b.getReceivedate());
			bnewlist.add(nbal);
		}
		for (balance b : bnewlist) {
			System.out.println(b.getHname() + "  " + b.getBill() + "  "
					+ b.getReceivedate());
		}
		return bnewlist;
	}

	public List<userbill> getIndividualBalance(String lname, int year) {
		query q = new query();
		List tbids=q.selectTbid(year);
		int lid=q.getLidFromLname(lname);
		System.out.println("lname : "+lname+ " year :"+year+" tbids :" +tbids.size()+" lid : "+lid);
		int hid=q.getHome(lid).get(0).getHid();
		List<userbill> blist = new ArrayList<userbill>();
		List<userbill> unewlist = new ArrayList<userbill>();
		for(int i=0;i<tbids.size();i++){
			System.out.println("tbid..."+tbids.get(i)+"  hid..."+hid);
			userbill ub=q.getBalanceByYear((int)tbids.get(i), hid);
			if(ub!=null)blist.add(ub);
		}
		System.out.println("blist..."+blist.size());
		for (userbill b : blist) {
			if (!b.getReceiveDate().equalsIgnoreCase("")) {
				userbill nbal = new userbill(b.getHid(), b.getBill(), b.getUnits(),b.getReceiveDate());
				System.out.println("...."+nbal.getBill());
				unewlist.add(nbal);
			}
		}
		return unewlist;
	}

	
	public List<userbill> getUserList(int tid, int year, int month) {
		query q = new query();
		int tbid = q.getTbid(tid, year, month);
		List<userbill> ulist = new ArrayList<userbill>();
		List<userbill> unewlist = new ArrayList<userbill>();
		ulist = q.getUserBill(tbid);

		for (userbill b : ulist) {
			int hid = b.getHid();
			String hname = q.selectHomeNameByNo(hid);
			
			if (b.getReceiveDate().equalsIgnoreCase("")) {
				userbill nbal = new userbill(hname, b.getBill(), b.getUnits());
				unewlist.add(nbal);
			}
		}
		return unewlist;
	}

	public List<userbill> getPaidUserList(int tid, int year, int month) {
		query q = new query();
		int tbid = q.getTbid(tid, year, month);
		List<userbill> ulist = new ArrayList<userbill>();
		List<userbill> unewlist = new ArrayList<userbill>();
		ulist = q.getUserBill(tbid);

		for (userbill b : ulist) {
			int hid = b.getHid();
			String hname = q.selectHomeNameByNo(hid);
			System.out.println("receive...."+b.getReceiveDate());
			if (!b.getReceiveDate().equalsIgnoreCase("")) {
				System.out.println("enter view user");
				userbill nbal = new userbill(hname, b.getBill(), b.getUnits(),b.getReceiveDate());
				System.out.println("...."+nbal.getBill());
				unewlist.add(nbal);
			}
		}
		return unewlist;
	}
	
	public List<userbill> getPaidPercentage(int tid, int year, int month) {
		query q = new query();
		int tbid = q.getTbid(tid, year, month);
		List<userbill> ulist = new ArrayList<userbill>();
		List<userbill> unewlist = new ArrayList<userbill>();
		ulist = q.getUserBill(tbid);

		for (userbill b : ulist) {
			int hid = b.getHid();
			String hname = q.selectHomeNameByNo(hid);
			System.out.println("receive...."+b.getReceiveDate());
			
			if (!b.getReceiveDate().equalsIgnoreCase("")) {
				System.out.println("enter view user");
				userbill nbal = new userbill(hname, (b.getBill()/100)*5, b.getUnits(),b.getReceiveDate());
				System.out.println("...."+nbal.getBill());
				unewlist.add(nbal);
			}
		}
		return unewlist;
	}
	
	public int getTotalIncome(int tid, int year, int month){
		query q = new query();
		int tbid = q.getTbid(tid, year, month);
		List<userbill> ulist = new ArrayList<userbill>();
		ulist = q.getUserBill(tbid);
		int total=0;
		for (userbill b : ulist) {
			if (!b.getReceiveDate().equalsIgnoreCase("")) {
				total+=b.getBill();
			}
		}
		return total;
	}
	
	public int getTotalPercentage(int tid, int year, int month){
		query q = new query();
		int tbid = q.getTbid(tid, year, month);
		List<userbill> ulist = new ArrayList<userbill>();
		ulist = q.getUserBill(tbid);
		int total=0;
		for (userbill b : ulist) {
			if (!b.getReceiveDate().equalsIgnoreCase("")) {
				total+=(b.getBill()/100)*5;
			}
		}
		return total;
	}
	
	public int getCharges(int year,String lname){
		query q = new query();
		List tbids = q.selectTbid(year);
		List<userbill> ulist = new ArrayList<userbill>();
		int lid=q.getLidFromLname(lname);
		int hid=q.getHome(lid).get(0).getHid();
		
		int total=0;
		List<userbill> blist = new ArrayList<userbill>();
		for(int i=0;i<tbids.size();i++){
			System.out.println("tbid..."+tbids.get(i)+"  hid..."+hid);
			userbill ub=q.getBalanceByYear((int)tbids.get(i), hid);
			if(ub!=null)blist.add(ub);
		}
		System.out.println("blist..."+blist.size());
		for (userbill b : blist) {
			if (!b.getReceiveDate().equalsIgnoreCase("")) {
				userbill nbal = new userbill(b.getHid(), b.getBill(), b.getUnits(),b.getReceiveDate());
				total+=b.getBill();
			}
		}
		
		return total;
	}
	
	
	public int getTownidByLname(String lname) {
		int tid = 0;
		query q = new query();
		int lid=q.getLidFromLname(lname);
		tid = q.getTidFromLid(lid);
		return tid;
	}
	
	public int getTownid(String tname) {
		int tid = 0;
		query q = new query();
		tid = q.selecttidByTname(tname);
		return tid;
	}

}
