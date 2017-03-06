public class AccountIdRBTree extends RBTree
{
    
    public AccountIdRBTree()
    {
       super();
    }

    public Node Search(int accountId)// search function changes slightly to search by accountId.
    {
        Node x = root;
        while(x!=nil && x.getCustomer().getAccountId() != accountId)
        {
            if(accountId < x.getCustomer().getAccountId())//comapre accountId's.
            {
                x = x.getLeft();
            }
            else
            {
                x = x.getRight();
            }
        }
        return x;
    }
    
    
    public void Insert(Node z)//insert function changes slightly to search by accountId.
    {
        if(root != nil)
        {
            Node y = nil;
            Node x = root;
            while(x!=nil)
            {
                y=x;
                if(z.getCustomer().getAccountId() < x.getCustomer().getAccountId())// comapare accountId's.
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
                if(z.getCustomer().getAccountId() < y.getCustomer().getAccountId())//coampre accountId's.
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
