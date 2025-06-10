package evaluation;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class calculateKUP {
  public double calculateKnowledgeUpdate(File f) throws FileNotFoundException{
    double vij=0;    
    Set varCount=countVariables(f);
    double lCount=0;
    System.out.println("Variable count :"+varCount.size());
    try(Scanner sc = new Scanner(new FileInputStream(f))){
      //sc.useDelimiter("\n \r");      
      sc.useDelimiter(System.getProperty("line.separator"));
        while(sc.hasNext()){
          String word=sc.next();
              if(word.contains(";")){
              lCount++;   
              for(Iterator it=varCount.iterator();it.hasNext();){
                String var=it.next().toString();
                if(word.contains(var)){
                 // System.out.println("word..."+word+" contain variable "+var);
                  vij++;
                }
              }//for
            }//if
       }//while   
        System.out.println("Line Count :"+lCount);
        System.out.println("vij :"+vij);
        double kus=vij/lCount;
        return kus;
    }//try
    
  }
  
 
  public static void main (String [] args)
  {
    calculateKUP ck=new calculateKUP();
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
    calculateKUP ck=new calculateKUP();
    double count;
    try {
      System.out.println("================================");
      System.out.println("       Knowledge Update          ");
      System.out.println("================================");
      count=ck.calculateKnowledgeUpdate(file1);     
      System.out.println("KUP(SA):"+count+"\n");
      count=ck.calculateKnowledgeUpdate(file2);     
      System.out.println("KUP(CA):"+count+"\n");
      count=ck.calculateKnowledgeUpdate(file3);     
      System.out.println("KUP(UA):"+count+"\n");
      count=ck.calculateKnowledgeUpdate(file4);     
      System.out.println("KUP(BA):"+count+"\n");
      
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
       if(w.equals("Logger")||w.equals("ACLMessage")||w.equals("Location")||w.equals("MessageTemplate")
              ||w.equals("Runtime")||w.equals("String")){
            varList.add(sc.next());
          }              
      }
     }
    return varList;
    }

}
