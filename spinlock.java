import java.util.*;
import java.io.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class spinlock{
    
    public static void main(String[] args){
        String filename = "input.txt";
        ArrayList<String> result = new ArrayList<String>();
        try{
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line = null;
                FileWriter fw = null;
                BufferedWriter bw = null;
            
                String tmp = "";
                while ((line = reader.readLine()) != null) {
                    if(line.equals("z")) // end of file
                        break;
                    if(line.equals("x")){ // next puzzle
                        result.add(tmp);
                        tmp = "";
                        continue;
                    }
                    else{ // add all the numbers on the same digit together and mod 10
                        tmp = (tmp=="")?line:addString(line,tmp);
                        if(tmp == "error")
                            System.out.println("error occured when reading input file!");
                    }
                }
            
            }catch (IOException x) {
                System.err.format("IOException: %s%n", x);
                System.exit(1);
            }
        
        //for(int i = 0; i < result.size(); i++)
        //    System.out.println(result.get(i));
        
        // write to output file
        writeToFile(result);

    }
    
    public static void writeToFile(ArrayList<String> result){
        String filename = "output.txt";
        try{
            //create files
            File f = new File(filename);
            if (!f.exists())
				f.createNewFile();
            
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("Spinlock Results");
            bw.newLine();
        
            for(int i = 0; i < result.size(); i++){
                bw.write(result.get(i)); // write line by line
                bw.newLine();
            }
        
            bw.close();
            fw.close();
            
        }catch (IOException x) {
            System.err.format("IOException: %s%n", x);
            System.exit(1);
        }
    }

    public static String addString(String s1, String s2){
        if(s1.length() != s2.length())
            return "error";
        
        String result = "";
        for(int i = 0; i < s1.length(); i++){
            int tmp = ((s1.charAt(i) - '0') + (s2.charAt(i) - '0'))%10;
            result+=tmp;
        }
        return result;
    }

}