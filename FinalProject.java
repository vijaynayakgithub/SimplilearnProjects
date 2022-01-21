package vijay.assitedprojects.com;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class FinalProject {
public static void main(String[] args) {
System.out.println("\n*********************************\n\n");
System.out.println("File Manipulation Application\nDeveloped By :- Vijay Nayak\n\n");
System.out.println("*********************************\n\n");
File newFolder=new File("NewFolder");
newFolder.mkdir();
optionSelection(newFolder);
}
private static void optionSelection(File newFolder) {
String []arr= {"1. Show all the files.","2.File Manipulation Options","3.Exit the Application"};
int []arr1= {1,2,3};
int slen= arr1.length;
System.out.println();
for(int i = 0;i<slen;i++) {
System.out.println(arr[i]);
}
System.out.println("Enter your choice:");
Scanner scan=new Scanner(System.in);
int options= scan.nextInt();
switch(options){
case 1:
String[] fileNames=new String[100];
fileNames = newFolder.list();
if(fileNames.length==0) {
System.out.println("the folder is empty");
}
else {
Arrays.sort(fileNames);
System.out.println("The files present are ");
for(String name:fileNames) {
System.out.println(name);
}
}
optionSelection(newFolder);
break;
case 2:
System.out.println("File manipulation options");
fileManipulation(newFolder);
break;
case 3:
System.out.println("ThankYou For Using Theapplication\n\nLogging off!!!");
break;
default:
System.out.println("Wrong input\nTry Again");
optionSelection(newFolder);
break;
}
}
private static void fileManipulation(File newFolder) {
System.out.println("1.Add a File\n2.Delete a file \n3.Search a file\n4.go tomain menu");
System.out.println("Enter your choice");
Scanner sc = new Scanner(System.in);
int choice=sc.nextInt();
switch(choice) {
case 1:
System.out.println("Adding a file\n");
System.out.println("Enter the name of the file you wish to add:");
String newFile=new Scanner(System.in).nextLine();
File addFile=new File(newFolder,newFile);
try {
if(addFile.createNewFile())
System.out.println("The file was added to the folder");
else
System.out.println("File already exist");
} catch (IOException e) {
System.out.println("Issue :"+ e.getMessage());
}
fileManipulation(newFolder);
break;
case 2:
System.out.println("Delete a file");
System.out.println("Enter the name of the file you have to delete:");
String fileName= new Scanner(System.in).nextLine();
File delFile=new File(newFolder,fileName);
if(delFile.exists()) {
if(delFile.delete()) {
System.out.println("The file deleted successfuly");
}
else
System.out.println("The file deletion wasunsuccessfull");
}
else
System.out.println("The specified file was not found");
fileManipulation(newFolder);
break;
case 3:
System.out.println("Search a file");
System.out.println("Enter the name of the file you have to search");
String serFile=new Scanner(System.in).nextLine();
File searchFile= new File(newFolder,serFile);
if(searchFile.exists()) {
System.out.println("The Searched file is present in the directory");
}
else
System.out.println("The searched file is not present in the directory");
fileManipulation(newFolder);
break;
case 4:
System.out.println("Going to main menu");
optionSelection(newFolder);
break;
default:
System.out.println("Wrong input\ntry again");
fileManipulation(newFolder);
break;
}
}
}
