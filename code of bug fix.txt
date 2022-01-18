package Assignments.com;

import java.util.ArrayList;
import java.util.Scanner;
public class WorkingWithBugs {
 public static void main(String[] args) {
 /*System.out.println("Hello World!");*/
 System.out.println("\n**************************************\n");
 System.out.println("\tWelcome to TheDesk \n");
 System.out.println("**************************************");
 ArrayList<Integer> arrlist = new ArrayList<Integer>();
 ArrayList<Integer> expenses = new ArrayList<Integer>();
 expenses.add(1000);
 expenses.add(2300);
 expenses.add(45000);
 expenses.add(32000);
 expenses.add(110);
 expenses.addAll(arrlist);
 optionsSelection(expenses,arrlist);
 }
 private static void optionsSelection(ArrayList<Integer>
expenses,ArrayList<Integer> arrlist) {
 String[] arr = {"1. I wish to review my expenditure",
 "2. I wish to add my expenditure",
 "3. I wish to delete my expenditure",
 "4. I wish to sort the expenditures",
 "5. I wish to search for a particular expenditure",
 "6. Close the application"
 };
 int[] arr1 = {1,2,3,4,5,6};
 int slen = arr1.length;
 System.out.println();
 for(int i=0; i<slen;i++){
 System.out.println(arr[i]);
 // display the all the Strings mentioned in the String array
 }

 System.out.println("\nEnter your choice:\t");
 Scanner sc = new Scanner(System.in);
 int options = sc.nextInt();
 for(int j=1;j<=slen;j++){
 if(options==j){
 switch (options){
 case 1:
 System.out.println("Your saved expenses are listed below: \n");
 System.out.println(expenses+"\n");
 optionsSelection(expenses,arrlist);
 break;
 case 2:
 System.out.println("Enter the value to add your Expense: \n");
 int value = sc.nextInt();
 expenses.add(value);
 System.out.println("Your value is updated\n");
 expenses.addAll(arrlist);
 System.out.println(expenses+"\n");
 optionsSelection(expenses,arrlist);
 break;
 case 3:
 System.out.println("You are about the delete all yourexpenses! \nConfirm again by selecting the same option...\n");
 int con_choice = sc.nextInt();
 if(con_choice==options){
 expenses.clear();
 System.out.println(expenses+"\n");
 System.out.println("All your expenses are erased!\n");
 } else {
 System.out.println("Oops... try again!");
 }
 optionsSelection(expenses,arrlist);
 break;
 case 4:
 sortExpenses(expenses);
 optionsSelection(expenses,arrlist);
 break;
 case 5:
 searchExpenses(expenses);
 optionsSelection(expenses,arrlist);
 break;
 case 6:
 closeApp();
break;
 default:
 System.out.println("You have made an invalid choice!");
 break;
 }
 }
 }
 }
 private static void closeApp() {
 System.out.println("Closing your application... \nThank you!");
 }
 private static void searchExpenses(ArrayList<Integer> arrayList) {
 int leng = arrayList.size();
 System.out.println("Enter the expense you need to search:\t");
 int exp=new Scanner(System.in).nextInt();
 boolean flag=false;
 for(int val:arrayList) {
 if(exp==val)
 flag=true;
 }
 if(flag)
 System.out.println("The Expense You Searched is Present");
 else
 System.out.println("The Expense is Not Present");
 }
 private static void sortExpenses(ArrayList<Integer> arrayList) {
 int arrlength = arrayList.size(),i=0,temp;
 int []number=new int[arrlength];
 for(int val:arrayList) {
 number[i]=val;
 i++;
 }
 for(i=0;i<arrlength;i++) {
 for(int j=i+1;j<arrlength;j++) {
 if(number[i]>number[j]) {
 temp=number[i];
 number[i]=number[j];
 number[j]=temp;
 }
 }
 }
 arrayList.removeAll(arrayList);
 for(int val:number) {
 arrayList.add(val);
 }
 System.out.println("The sorted Expenses are ");
 System.out.println(arrayList);

 // or we can simply use "Collections.sort()" method
 }
}