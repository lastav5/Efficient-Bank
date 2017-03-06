public class BalanceRBTree extends RBTree
{
    public BalanceRBTree()
    {
        super();
    }
    
    public void Insert(Node z)//insert function changed slightly to search by customer balance.
    {
        if(root != nil)
        {
            Node y = nil;
            Node x = root;
            while(x!=nil)
            {
                y=x;
                if(z.getCustomer().getBalance() < x.getCustomer().getBalance())//comapre balances.
                    x = x.getLeft();
                else
                    x = x.getRight();
            }
            z.setP(y);
            if(y == nil)
            {
                root = z;
            }
            else
            {
                if(z.getCustomer().getBalance() < y.getCustomer().getBalance())//comampre balances.
                    y.setLeft(z);
                else
                    y.setRight(z);
            }
            z.setLeft(nil);
            z.setRight(nil);
            z.setColor(new String("Red"));
            InsertFixup(z);
        }
        else //tree is empty
        {
            z.setLeft(nil);
            z.setRight(nil);
            z.setColor(new String("Black"));
            root = z;
        }
    }
}
