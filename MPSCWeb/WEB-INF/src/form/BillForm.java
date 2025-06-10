package form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class BillForm extends ActionForm {
	 
	String month, year;
		FormFile upfile;
	String filepath;
	List<String> townList;
	String txttown;

	
	public String getTxttown() {
		return txttown;
	}

	public void setTxttown(String txttown) {
		this.txttown = txttown;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public FormFile getUpfile() {
		return upfile;
	}

	public void setUpfile(FormFile upfile) {
		this.upfile = upfile;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

}
