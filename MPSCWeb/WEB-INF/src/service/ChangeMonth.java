package service;

public class ChangeMonth {

	public int StringtoInt(String m){
		int month=0;
		switch(m){
			case "JANAUARY":month=1;break;
			case "FEBRUARY":month=2;break;
			case "MARCH":month=3;break;
			case "APRIL":month=4;break;
			case "MAY":month=5;break;
			case "JUNE":month=6;break;
			case "JULY":month=7;break;
			case "AUGUST":month=8;break;
			case "SEPTEMBER":month=9;break;
			case "OCTOBER":month=10;break;
			case "NOVEMBER":month=11;break;
			case "DECEMBER":month=12;break;
		}
		return month;		
	}
	
	public int StringtoInt2(String m){
		int month=0;
		switch(m){
			case "Jan":month=1;break;
			case "Feb":month=2;break;
			case "Mar":month=3;break;
			case "Apr":month=4;break;
			case "May":month=5;break;
			case "Jun":month=6;break;
			case "Jul":month=7;break;
			case "Aug":month=8;break;
			case "Sep":month=9;break;
			case "Oct":month=10;break;
			case "Nov":month=11;break;
			case "Dec":month=12;break;
		}
		return month;		
	}
	
	public String InttoString(int m){
		String month="";
		switch(m){
		case 1:month="Jan";break;
		case 2:month="Feb";break;
		case 3:month="Mar";break;
		case 4:month="Apr";break;
		case 5:month="May";break;
		case 6:month="Jun";break;
		case 7:month="Jul";break;
		case 8:month="Aug";break;
		case 9:month="Sep";break;
		case 10:month="Oct";break;
		case 11:month="Nov";break;
		case 12:month="Dec";break;
		}
		return month;		
	}
}
