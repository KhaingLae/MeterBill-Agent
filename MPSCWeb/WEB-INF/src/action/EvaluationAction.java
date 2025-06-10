package action;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import evaluation.calculateEHF;
import evaluation.calculateKUP;
import evaluation.calculateKUS;
import form.EvaluationForm;

import agent.BankAgent;
import agent.ClientAgent;
import ChartDirector.*;

public class EvaluationAction extends BaseAction{

  @Override
  protected String doExecute(ActionForm form, HttpServletRequest request, ActionMapping mapping)
      throws Exception {
    // TODO Auto-generated method stub
    System.out.println("in execute in  evaluation");
    EvaluationForm myform=(EvaluationForm)form;
    double tkussa=logOfBase(myform.getKusk()+1,myform.getKussa()+1);
    myform.setNkussa(tkussa);
    double tkusca=logOfBase(myform.getKusk()+1,myform.getKusca()+1);
    myform.setNkusca(tkusca);
    double tkusua=logOfBase(myform.getKusk()+1,myform.getKusua()+1);
    myform.setNkusua(tkusua);
    double tkusba=logOfBase(myform.getKusk()+1,myform.getKusba()+1);
    myform.setNkusba(tkusba);
    System.out.println("kupk...."+myform.getKupk());
    double tkupsa=logOfBase(myform.getKupk()+1,myform.getKupsa()+1);
    myform.setNkupsa(tkupsa);
    double tkupca=logOfBase(myform.getKupk()+1,myform.getKupca()+1);
    myform.setNkupca(tkupca);
    double tkupua=logOfBase(myform.getKupk()+1,myform.getKupua()+1);
    myform.setNkupua(tkupua);
    double tkupba=logOfBase(myform.getKupk()+1,myform.getKupba()+1);
    myform.setNkupba(tkupba);
    System.out.println("ehfk...."+myform.getEhfk());
    double tehfsa=logOfBase(myform.getEhfk()+1,myform.getEhfsa()+1);
    myform.setNehfsa(tehfsa);
    double tehfca=logOfBase(myform.getEhfk()+1,myform.getEhfca()+1);
    myform.setNehfca(tehfca);
    double tehfua=logOfBase(myform.getEhfk()+1,myform.getEhfua()+1);
    myform.setNehfua(tehfua);
    double tehfba=logOfBase(myform.getEhfk()+1,myform.getEhfba()+1);
    myform.setNehfba(tehfba);
    System.out.println("alsk....."+myform.getAlsk());
    double talssa=getExp(myform.getAlssa(),myform.getAlsk());
    myform.setNalssa(talssa);
    double talsca=getExp(myform.getAlsca(),myform.getAlsk());
    myform.setNalsca(talsca);
    double talsua=getExp(myform.getAlsua(),myform.getAlsk());
    myform.setNalsua(talsua);
    double talsba=getExp(myform.getAlsba(),myform.getAlsk());
    myform.setNalsba(talsba);
    if(request.getParameter("generate")!=null){

      System.out.println("enter chart...");
      HttpSession session=request.getSession();
    double[] data0 = {myform.getNkussa(),myform.getNkusca(),myform.getNkusua(),myform.getNkupba()};
    double[] data1 = {myform.getNkupsa(),myform.getNkupca(),myform.getNkupua(),myform.getNkupba()};
    double[] data2 = {myform.getNehfsa(),myform.getNehfca(),myform.getNehfua(),myform.getNehfba()};
    double[] data3 = {myform.getNalssa(),myform.getNalsca(),myform.getNalsua(),myform.getNalsba()};
    // The labels for the line chart
    String[] labels = {"SA", "CA", "UA", "BA"};

    // Create an XYChart object of size 600 x 300 pixels, with a light blue (EEEEFF) background, black
    // border, 1 pxiel 3D border effect and rounded corners
    XYChart c = new XYChart(600, 300, 0xeeeeff, 0x000000, 1);
    c.setRoundedFrame();

    // Set the plotarea at (55, 58) and of size 520 x 195 pixels, with white background. Turn on both
    // horizontal and vertical grid lines with light grey color (0xcccccc)
    c.setPlotArea(55, 58, 520, 195, 0xffffff, -1, -1, 0xcccccc, 0xcccccc);

    // Add a legend box at (50, 30) (top of the chart) with horizontal layout. Use 9pt Arial Bold font.
    // Set the background and border color to Transparent.
    c.addLegend(50, 30, false, "Arial Bold", 9).setBackground(Chart.Transparent);

    // Add a title box to the chart using 15pt Times Bold Italic font, on a light blue (CCCCFF)
    // background with glass effect. white (0xffffff) on a dark red (0x800000) background, with a 1
    // pixel 3D border.
    c.addTitle("Impact of measures on Adaptability", "Times New Roman Bold Italic", 15).setBackground(
        0xccccff, 0x000000, Chart.glassEffect());

    // Add a title to the y axis
    c.yAxis().setTitle("Normalization Values");

   
    // Display 1 out of 3 labels on the x-axis.
    c.xAxis().setLabelStep(1);

    
    // Add a line layer to the chart
    LineLayer layer = c.addLineLayer2();

    // Set the default line width to 2 pixels
    layer.setLineWidth(2);

    // Add the three data sets to the line layer. For demo purpose, we use a dash line color for the
    // last line
    layer.addDataSet(data0, 0xff0000, "KUS");
    layer.addDataSet(data1, 0x008800, "KUP");
    layer.addDataSet(data2, c.dashLineColor(0x3333ff, Chart.DashLine), "EHF");
    layer.addDataSet(data3, c.dashLineColor(0xFFC300, Chart.DotLine), "ALS");
    // Output the chart
    String chart1URL = c.makeSession(request, "chart1");

    // Include tool tip for the chart
    String imageMap1 = c.getHTMLImageMap("", "", "title='Impact of measures on Adaptability'"
        );
    session.setAttribute("graph", 1);
    session.setAttribute("charturl", chart1URL);
    session.setAttribute("imagemap", imageMap1);
    session.setAttribute("chart",1);
    
      
    }
    return "evaluationsuccess";
  }
  
  public double getExp(int x,int k){
    double ans=1;
    if(x>k){
      double t=Math.pow((x-k), 2);
      double tt=Math.pow(k, 2);
      double ttt=-t/tt;
      ans=Math.exp(ttt);
    }
    return round(ans);
  }


  public double logOfBase(int base, double num) {
    double rnum=Math.round(Math.log(num)*100.0)/100.0;
    double rbase=Math.round(Math.log(base)*100.0)/100.0;
    return Math.round((rnum / rbase)*100.0)/100.0;
}
  public double round(double num) {
    return Math.round(num*100.0)/100.0;    
}
  protected String doInit(ActionForm form, HttpServletRequest request,
      ActionMapping mapping) {
    
    System.out.println("in do init in  evaluation");
    EvaluationForm myform=(EvaluationForm)form;
    
    URL url1 = getClass().getResource("/./agent/SubscriberAgent.txt");   
    URL url2 = getClass().getResource("/./agent/LoggerAgent.txt");  
    URL url3 = getClass().getResource("/./agent/ClientAgent.txt");  
    URL url4 = getClass().getResource("/./agent/BankAgent.txt");  
    File file1 = new File(url1.getFile());
    File file2 = new File(url2.getFile());
    File file3 = new File(url3.getFile());
    File file4 = new File(url4.getFile());
   
    int count;double k;
    
    try {
      System.out.println("================================");
      System.out.println("       Knowledge Usage          ");
      System.out.println("================================");
      calculateKUS ck=new calculateKUS();
      count=ck.calculateKnowledgeUsage(file1);     
      System.out.println("KUS(SA):"+count+"\n");
      myform.setKussa(count);
      count=ck.calculateKnowledgeUsage(file2);     
      System.out.println("KUS(CA):"+count+"\n");
      myform.setKusca(count);
      count=ck.calculateKnowledgeUsage(file3);     
      System.out.println("KUS(UA):"+count+"\n");
      myform.setKusua(count);
      count=ck.calculateKnowledgeUsage(file4);     
      System.out.println("KUS(BA):"+count+"\n");
      myform.setKusba(count);
      System.out.println("================================");
      System.out.println("       Knowledge Update          ");
      System.out.println("================================");
      calculateKUP kup=new calculateKUP();
      k=round(kup.calculateKnowledgeUpdate(file1));     
      System.out.println("KUP(SA):"+k+"\n");
      myform.setKupsa(k);
      k=round(kup.calculateKnowledgeUpdate(file2));     
      System.out.println("KUP(CA):"+k+"\n");
      myform.setKupca(k);
      k=round(kup.calculateKnowledgeUpdate(file3));     
      System.out.println("KUP(UA):"+k+"\n");
      myform.setKupua(k);
      k=round(kup.calculateKnowledgeUpdate(file4));     
      System.out.println("KUP(BA):"+k+"\n");
      myform.setKupba(k);
      calculateEHF ehf=new calculateEHF();
      count=ehf.calExceptionHandling(file1);      
      System.out.println("EHF(SA):"+count+"\n");
      myform.setEhfsa(count);
      count=ehf.calExceptionHandling(file2);     
      System.out.println("EHF(CA):"+count+"\n");
      myform.setEhfca(count);
      count=ehf.calExceptionHandling(file3);     
      System.out.println("EHF(UA):"+count+"\n");
      myform.setEhfua(count);
      count=ehf.calExceptionHandling(file4);     
      System.out.println("EHF(BA):"+count+"\n");
      myform.setEhfba(count);
      System.out.println("kussa in form..."+myform.getKussa());
      System.out.println("================================");
      System.out.println("       Agent Lifespan         ");
      System.out.println("================================");
      globaldata gb=new globaldata();
      System.out.println("ALS(SA):"+gb.sa);
      System.out.println("ALS(CA):"+gb.ca);
      System.out.println("ALS(UA):"+gb.ua);
      System.out.println("ALS(BA):"+gb.ba);
      myform.setAlssa(gb.sa.intValue());
      myform.setAlsca(gb.ca.intValue());
      myform.setAlsua(gb.ua.intValue());
      myform.setAlsba(gb.ba.intValue());
      

      
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    return "success";
  }
}
