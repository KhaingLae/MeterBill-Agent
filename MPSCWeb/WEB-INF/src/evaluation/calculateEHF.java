package evaluation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class calculateEHF {
  
  public int calExceptionHandling(File f) throws FileNotFoundException{
    int ehf=0;
    int mCount=0;
    Map methods=new HashMap();
    int id=0;
    try(Scanner sc = new Scanner(new FileInputStream(f))){      
          while(sc.hasNext()){
           
          String word=sc.next();
        //  System.out.println("===================");        
              if(word.equals("protected")||word.equals("public")||word.equals("private")){
               sc.next();
               String temp=sc.next();               
               if(temp.startsWith("handleUnexpected")){}
               else if(temp.contains("(")){
               int open=0;int close=0; int err=0;
               while(sc.hasNext()){
                 word=sc.next();
                if(word.contains("Exception")||word.startsWith("handleUnexpected"))err++;                       
                if(word.contains("{")){open++;}
                if(word.contains("}"))close--;
                if(open+close==0 && open!=0){
                  id++;
                  String methodname=id+" "+temp;
                  methods.put(methodname, err);break;} 
                 }
                      
                   }//while            
              
              }//if
       }//while 
         Set set = methods.entrySet();
         Iterator it = set.iterator();
         while(it.hasNext()){
             Map.Entry mentry = (Map.Entry)it.next();
             System.out.println(mentry.getKey()+" : "+mentry.getValue());
             ehf+=(int)mentry.getValue();
         }
    }//try
    return ehf;
  }
  public static void main (String [] args)
  {
    calculateEHF ck=new calculateEHF();
    ck.scanFile();
  }
  
  public void scanFile() {
    URL url1 = getClass().getResource("/./agent/SubscriberAgent.txt");   
    URL url2 = getClass().getResource("/./agent/LoggerAgent.txt");  
    URL url3 = getClass().getResource("/./agent/ClientAgent.txt");  
    URL url4 = getClass().getResource("/./agent/BankAgent.txt");  
    File file1 = new File(url1.getFile());
    File file2 = new File(url2.getFile());
    File file3 = new File(url3.getFile());
    File file4 = new File(url4.getFile());
    calculateEHF ck=new calculateEHF();
    int count;
    System.out.println("================================");
    System.out.println(" Exception Handling Functionality");
    System.out.println("================================");
    try {
      count=ck.calExceptionHandling(file1);
       
    System.out.println("EHF(SA):"+count+"\n");
    count=ck.calExceptionHandling(file2);     
    System.out.println("EHF(CA):"+count+"\n");
    count=ck.calExceptionHandling(file3);     
    System.out.println("EHF(UA):"+count+"\n");
    count=ck.calExceptionHandling(file4);     
    System.out.println("EHF(BA):"+count+"\n");
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }  
    
  }

}
