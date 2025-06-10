package evaluation;

import jade.lang.acl.MessageTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class calculateKUS {

  
  public int calculateKnowledgeUsage(File f) throws FileNotFoundException{
    int vij=0;    
    Set varCount=countVariables(f);
    int ifCount=0;
  //  for(Iterator it= varCount.iterator();it.hasNext();System.out.println(it.next());
    System.out.println("variable count :"+varCount.size());
    try(Scanner sc = new Scanner(new FileInputStream(f))){
        while(sc.hasNext()){
          String word=sc.next();
            if(word.equalsIgnoreCase("if")){
              ifCount++;   
              vij++;
              do{
                if(word.equals("&&")||word.equals("||"))vij++;
                  word=sc.next();
                  }while(word.equals("{")==false);
               }//if
       }//while   
        System.out.println("Decision Statement :"+ifCount);
        System.out.println("vij :"+vij);
        int kus=vij/ifCount;
        return kus;
    }//try
 
  }
  
 
  public static void main (String [] args)
  {
    calculateKUS ck=new calculateKUS();
    ck.scanFile();
  }
  
  public void scanFile(){
    URL url1 = getClass().getResource("/./agent/SubscriberAgent.txt");   
    URL url2 = getClass().getResource("/./agent/LoggerAgent.txt");  
    URL url3 = getClass().getResource("/./agent/ClientAgent.txt");  
    URL url4 = getClass().getResource("/./agent/BankAgent.txt");  
    File file1 = new File(url1.getFile());
    File file2 = new File(url2.getFile());
    File file3 = new File(url3.getFile());
    File file4 = new File(url4.getFile());
    calculateKUS ck=new calculateKUS();
    int count;
    try {
      System.out.println("================================");
      System.out.println("       Knowledge Usage          ");
      System.out.println("================================");
      count=ck.calculateKnowledgeUsage(file1);     
      System.out.println("KUS(SA):"+count+"\n");
      count=ck.calculateKnowledgeUsage(file2);     
      System.out.println("KUS(CA):"+count+"\n");
      count=ck.calculateKnowledgeUsage(file3);     
      System.out.println("KUS(UA):"+count+"\n");
      count=ck.calculateKnowledgeUsage(file4);     
      System.out.println("KUS(BA):"+count+"\n");
      
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  public Set countVariables(File file) throws FileNotFoundException{
    int count=0;
    Set varList=new HashSet();
    try(Scanner sc = new Scanner(new FileInputStream(file))){
      while(sc.hasNext()){
       String w=sc.next();
      //  System.out.println("w..."+w);
        //String w= "MessageTemplate.MatchConversationId(CHAT_ID)";
          if(w.equals("Logger")||w.equals("ACLMessage")||w.equals("Location")||w.equals("MessageTemplate")
              ||w.equals("Runtime")||w.equals("String")){
            varList.add(sc.next());
          }              
      }
     }
    return varList;
  }

}
