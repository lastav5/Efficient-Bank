public class Bank
{
    AccountIdRBTree accountIdTree;
    BalanceRBTree balanceTree;
    
    public Bank()
    {
        accountIdTree = new AccountIdRBTree();
        balanceTree = new BalanceRBTree();
    }

    public void UpdateBalanceByAccountId(int accountId, int amount)
    {
        //update the balance of customer and delete both nodes from their trees:
        Node custNodeAccount = accountIdTree.Search(accountId);//find the node in accountId tree
        Customer cust = custNodeAccount.getCustomer();
        cust.addToBalance(amount);// update the node's customer's balance.
        Node custNodeBalance = custNodeAccount.getParallel();//the node in the balance tree is the parallel of the node in accoundId tree - custNodeAccount.
        accountIdTree.Delete(custNodeAccount);//delete the node in accountId tree
        balanceTree.Delete(custNodeBalance);// delete the node in balance tree
        
        //create new nodes with the original customer object and add them to their trees again:
        Node newCust = new Node(cust);//customer node - we create this without parallel right now and later update
        Node newBalance = new Node(cust);//balance node - same here.
        newCust.setParallel(newBalance);// now we set the parallel to be the other node we just created.
        newBalance.setParallel(newCust);// same set parallel here
        accountIdTree.Insert(newCust);// inserting the nodes into their respective trees.
        balanceTree.Insert(newBalance);
        
        System.out.println("The customer "+cust.getName()+" with Account ID: "+cust.getAccountId()+" and Customer ID: "+cust.getCustomerId()+" deposited "+amount+" NIS. current balance: "+cust.getBalance()+" NIS");
    }
    
    public void AddNewCustomer(Customer cust)
    {
        Node newCust = new Node(cust);//customer node - we create this without parallel right now and later update
        Node newBalance = new Node(cust);//balance node - same here.
        newCust.setParallel(newBalance);// now we set the parallel to be the other node we just created.
        newBalance.setParallel(newCust);// same set parallel here
        accountIdTree.Insert(newCust);// inserting the nodes into their respective trees.
        balanceTree.Insert(newBalance);
        System.out.println("The Customer "+cust.getName()+" with Account ID: "+cust.getAccountId()+" and Customer ID: "+cust.getCustomerId()+" joined the bank with a balance of "+cust.getBalance()+" NIS");
    }
    
    public void RemoveCustomerByAccountId(int accountId)
    {
        Node custNodeAccount = accountIdTree.Search(accountId);//find the node in accountId tree
        //Node custNodeBalance = custNodeAccount.getParallel();
        Customer cust = custNodeAccount.getCustomer();
        if(cust.getBalance()!=0)//if the customer's balance is not zero then he has to deposit or draw money to reset his balance.
        {
            UpdateBalanceByAccountId(cust.getAccountId(),(-1)*(cust.getBalance()));
        }
        Node custNodeAccount2 = accountIdTree.Search(accountId);//find the node again - it's different now
        Node custNodeBalance2 = custNodeAccount2.getParallel();//the node in the balance tree is the parallel of the node in accoundId tree-custNodeAccount2.
        accountIdTree.Delete(custNodeAccount2);//delete the node in accountId tree
        balanceTree.Delete(custNodeBalance2);// delete the node in balance tree
        System.out.println("The Customer "+cust.getName()+" with Account ID: "+cust.getAccountId()+" and Customer ID: "+cust.getCustomerId()+" left the bank");
    }
    
    public void printBalanceByAccountId(int accountId)
    {
        Node custNodeAccount = accountIdTree.Search(accountId);//find the node with the given accountId in the accountId tree.
        Customer cust = custNodeAccount.getCustomer();//get the node's cutomer object.
        System.out.println("The Customer "+cust.getName()+" with Account ID: "+cust.getAccountId()+" and Customer ID: "+cust.getCustomerId()+" has a balance of "+cust.getBalance()+" NIS");
    }
    
    public void printMaxBalanceCustomer()
    {
        if(balanceTree.getRoot()!= RBTree.getNil())
        {
            Customer maxCust = (balanceTree.TreeMaximum(balanceTree.getRoot())).getCustomer();// get the maximum node in the balance tree and get its customer object.
            System.out.println("The Customer with the highest balance is: "+maxCust.getName()+", Account Id: "+maxCust.getAccountId()+", Customer ID: "+maxCust.getCustomerId()+", Balance: "+maxCust.getBalance()+" NIS");
        }
        else
        {
            System.out.println("The bank is empty");
        }
    }
    
    public void printNegativeBalanceCustomers()
    {
       Node x = balanceTree.getRoot();
       if(x!=balanceTree.getNil())//checking that the balance tree is not empty
       {
           System.out.println("Customers with a negative balance:");
           printNeg(x, balanceTree);
       }
       else
       {
           System.out.println("The bank is empty");
       }
    }
    /**Gets a root and prints all of the customers in the tree with a negative balance. */
    private static void printNeg(Node x, BalanceRBTree balanceTree)
    {
       if(x!= balanceTree.getNil())
       {
          Customer cust;
          printNeg(x.getLeft(),balanceTree);
          if(x.getCustomer().getBalance() < 0) 
          {
              cust = x.getCustomer();
              System.out.println(cust.getName()+" with Account ID: "+cust.getAccountId()+" and Customer ID: "+cust.getCustomerId()+" with a balance of "+cust.getBalance()+" NIS");
              printNeg(x.getRight(), balanceTree);//we bother going to the right sub-tree only if the current node is negative. otherwise the node is positive and so it is not possible any of the nodes in the right sub-tree are negative.
          }
       }
    }
    /**prints both trees in an Inorder scan.*/
    public void printTreesInOrder()
    {
        System.out.println("account id tree:");
        inOrder(accountIdTree.getRoot(), accountIdTree);
        System.out.println("balance tree:");
        inOrder(balanceTree.getRoot(), balanceTree);
    }
    
    /**Gets a tree object and the tree's root and prints the entire tree using an InOrder scan.*/
    private static void inOrder(Node root, RBTree tree) 
    {  
      if(root !=  RBTree.getNil())
      {  
           inOrder(root.getLeft(), tree);     
           System.out.println(root.getCustomer().getName()+", accountId: "+root.getCustomer().getAccountId()+", balance "+root.getCustomer().getBalance()+" NIS");  
           inOrder(root.getRight(), tree);  
      }  
     }  
}
