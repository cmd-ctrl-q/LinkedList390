// Resources: https://www.javatpoint.com/how-to-read-file-line-by-line-in-java

import java.io.*;  
import java.util.Scanner;

public class FileReader {

    String file;
    LinkedList llist = new LinkedList(); 

    FileReader(String file) { 
        this.file = file;
    }

    // delete 2
    public LinkedList insertNamesIntoList() {
    
        try {  
            //the file to be opened for reading  
            FileInputStream fis = new FileInputStream(file);       
            Scanner sc=new Scanner(fis); 
            
            while(sc.hasNextLine()) {  
                String name = sc.nextLine();
                llist.insertInOrder(name);
            }  
            sc.close(); 
        }  
        catch(IOException e) {  
            e.printStackTrace();  
        }  
        return llist;
    }  
}  