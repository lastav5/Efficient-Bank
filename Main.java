import java.util.Scanner;
public class Main
{
    public static void Main()
    {
        Scanner s = new Scanner(System.in);
        Bank bank = new Bank();
        //faux tree
        bank.AddNewCustomer(new Customer(new String("Stav Lagziel"), 100, 200, 700));
        bank.AddNewCustomer(new Customer(new String("Sivan Lagziel"), 101, 201, -3000));
        bank.AddNewCustomer(new Customer(new String("Saar Lagziel"), 102, 202, 50));
        bank.AddNewCustomer(new Customer(new String("omer Lagziel"), 103, 203, 100));
        
        String[] arr;
        while(1==1)//run for as long as there was no "break" inside the loop
        {
            System.out.println("enter a command.  enter quit to exit program.");
            String str = new String(s.nextLine());
            arr = str.split(" ");//seperate each word in the string and insert them into an array
            
            if(arr[0].equals("quit"))
            {
                break;
            }
            if(((String)arr[0]).matches("[a-zA-Z]+"))//if first word is made of alphabets then this is a request for a deposit.
            {
                int amount = Integer.parseInt(arr[3]);
                int accountId = Integer.parseInt(arr[2]);
                bank.UpdateBalanceByAccountId(accountId,amount);
            }
            if(arr[0].equals("+"))//if first "word" is a plus sign then this is a message about a customer joining.
            {
                Customer cust = new Customer(arr[1]+" "+arr[2], Integer.parseInt(arr[4]), Integer.parseInt(arr[3]), Integer.parseInt(arr[5]));
                bank.AddNewCustomer(cust);
            }
            if(arr[0].equals("-"))//if first "word" is a minus sigh then this is a message about a customer leaving.
            {
                int accountId = Integer.parseInt(arr[1]);
                bank.RemoveCustomerByAccountId(accountId);
            }
            if(arr[0].equals("?"))//if first word is a question mark then this is a request of some sort.
            {
                if(isNumber((String)arr[1]))// if second word is a number then this is a request for a customer's balance.
                {
                    int accountId= Integer.parseInt(arr[1]);
                    bank.printBalanceByAccountId(accountId);
                }
                if(arr[1].equals("MAX"))// if second word is MAX then this is a request for the customer with the highest balance.
                {
                    bank.printMaxBalanceCustomer();
                }
                if(arr[1].equals("MINUS"))//if second word is MINUS then this is a request for all the customers with a negative balance.
                {
                    bank.printNegativeBalanceCustomers();
                }
            }
            if(arr[0].equals("!"))// if first word is an exclamation mark then show all customers in an Inorder scan - both trees. this is for comfort and not a part of the project's requirements.
            {
                bank.printTreesInOrder();
            }
            System.out.println("Done");
        }
    }
    /** checks wether a String is a number*/
    private static boolean isNumber(String str)
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
}
