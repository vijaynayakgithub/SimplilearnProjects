import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandling {
	public static void main(String[] args) {
		
		//creating an object of a file
		File myObj=new File("NewFilef1.txt");
		if (myObj.exists()) {
			
		//returning the file name
		System.out.println("File name: "+myObj.getName());
		
		//returning the path of the file
		System.out.println("Absolute path: "+myObj.getAbsolutePath());
	
		//displaying whether the file is writable
		System.out.println("Writable: "+myObj.canWrite());
		
		//Returning whether the file is readble or not
		System.out.println("Readable: "+myObj.canRead());
		
		//Returning the length of the file in bytes
		System.out.println("File size in bytes: "+myObj.length());
		
	}
		else {
			System.out.println("The file doesnot exists");
		}

		//file appending
		try {
			String data="Mphasis is the leading company";
			File f1=new File("D:\\Automation\\QSP  KCSM7\\Java practice\\NewFilef1.txt");
			if(f1.exists()) {
				f1.createNewFile();
			}
			FileWriter fileWritter= new FileWriter(f1.getName(),true);
			BufferedWriter bw=new BufferedWriter(fileWritter);
			bw.write(data);
			bw.close();
			System.out.println("File append is done");
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}