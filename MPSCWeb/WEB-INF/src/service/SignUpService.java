package service;

import java.util.List;
import database.query;
import form.LoginForm;

public class SignUpService {
	
	public List getByTown(String tname) {
		System.out.println("townname="
				+ tname);
		query q=new query();
		int tid=q.selecttidByTname(tname);
		List<String> b=q.selectBlockByTown(tid);
		System.out.println("block size..."+b.size());
		return b;
	}

	public List getByBlock(String bname) {
		System.out.println("blockname="
				+ bname);
		query q=new query();
		int bid=q.getBidFromBname(bname);
		List<String> s=q.selectStreetByBlock(bid);
		System.out.println("block size..."+s.size());
		return s;
	}

	
	public boolean existsHome(int town,String home){
		boolean e=false;
		query q=new query();
		e=q.existHome(town,home);
		return e;
	}
	
	public int getTownid(String tname){
		int tid=0;
		query q=new query();
		tid=q.selecttidByTname(tname);
		return tid;
	}
	
	public void SaveHome(LoginForm f){
		query q=new query();
		int bid=0,sid=0;int bsid=0;
		
		bid=q.findblockid(f.getBlockname(), f.getTownid());
		sid=q.findstreetid(f.getStreetname());
		System.out.println("bid: "+bid);
		System.out.println("sid: "+sid);
		if(bid!=0 || sid!=0)bsid=q.getBsid(bid, sid);
		else bsid=1;
		q.saveLogin(f.getLoginname(), f.getPassword(), 4);
		int lid=q.getLoginCount();
		System.out.println("townid..."+f.getTownid());
		System.out.println("bsid..."+bsid);
		q.saveHome(f.getHome(), bsid, f.getTownid(), lid);		
	}
	
	public List<String> getAllTown() {
		// TODO Auto-generated method stub
		query q=new query();
		List<String> tl=q.selectAllTown();
		System.out.println("Size of town in service is "+tl.size());
		return tl;
	}
	
	
}
