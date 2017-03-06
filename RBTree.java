public abstract class RBTree
{
    protected Node root;
    protected static Node nil = new Node();// nil must be static since throughout the entire code we compare nodes' addresses to nil's address. therefore we must only have one nil.
    
    public RBTree()
    {
        this.root = nil;
    }
    
    public Node getRoot()
    {
        return root;
    }
    
    public static Node getNil()// this function is staic in order to be able to access nil outside this class' 'family'.
    {
        return nil;
    }
    
    public void LeftRotate(Node x)
    {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if(y.getLeft() != nil)
        {
            ((Node)y.getLeft()).setP(x);
        }
        y.setP(x.getP());
        if(x.getP() == nil)
        {
            root = y;
        }
        else
        {
            if(x == ((Node)x.getP()).getLeft())
            {
                ((Node)x.getP()).setLeft(y);
            }
            else
            {
                ((Node)x.getP()).setRight(y);
            }
        }
        y.setLeft(x);
        x.setP(y);
    }
    
    public void RightRotate(Node x)
    {
        Node y = x.getLeft();
        x.setLeft(y.getRight());
        if(y.getRight() != nil)
        {
            ((Node)y.getRight()).setP(x);
        }
        y.setP(x.getP());
        if(x.getP() == nil)
        {
            root = y;
        }
        else
        {
            if(x == ((Node)x.getP()).getRight())
            {
                ((Node)x.getP()).setRight(y);
            }
            else
            {
                ((Node)x.getP()).setLeft(y);
            }
        }
        y.setRight(x);
        x.setP(y);
    }
   
    public void InsertFixup(Node z)
    {   
        String RedStr = new String("Red");
        while(((String)(z.getP()).getColor()).equals(RedStr))
        {
            if(z.getP() == z.getP().getP().getLeft())
            {
                Node y = z.getP().getP().getRight();
                if(y.getColor().equals(RedStr))
                {
                    z.getP().setColor(new String("Black"));
                    y.setColor(new String("Black"));
                    z.getP().getP().setColor(new String("Red"));
                    z = z.getP().getP();
                }
                else
                {
                    if(z == z.getP().getRight())
                    {
                        z = z.getP();
                        LeftRotate(z);
                    }
                    z.getP().setColor(new String("Black"));
                    z.getP().getP().setColor(new String("Red"));
                    RightRotate(z.getP().getP());
                }
            }
            else
            {
                Node y = z.getP().getP().getLeft();
                if(y.getColor().equals(RedStr))
                {
                    z.getP().setColor(new String("Black"));
                    y.setColor(new String("Black"));
                    z.getP().getP().setColor(new String("Red"));
                    z = z.getP().getP();
                }
                else
                {
                   if(z == z.getP().getLeft())
                    {
                        z = z.getP();
                        RightRotate(z);
                    }
                   z.getP().setColor(new String("Black"));
                   z.getP().getP().setColor(new String("Red"));
                   LeftRotate(z.getP().getP());
                }
            }
        }
        root.setColor(new String("Black"));
    }
    
    public Node Delete(Node z)
    {
        Node y;
        Node x;
        if(z.getLeft() == nil || z.getRight() == nil)
            y = z;
        else
        {
            Node tmp = z;
            y = TreeSuccessor(tmp);
        }
        
        if(y.getLeft()!=nil)
            x = y.getLeft();
        else
            x = y.getRight();
        x.setP(y.getP());
        if(y.getP()== nil)
        {
            root = x;
        }
        else
        {
            if(y == y.getP().getLeft())
                y.getP().setLeft(x);
            else
                y.getP().setRight(x);
        }
        if(y != z)
        {// here we insert y's data into z.
            z.setCustomer(y.getCustomer());// move the customer data from y to z.
            z.setParallel(y.getParallel());//z now must point to y's parallel (to where y is pointing).
            y.getParallel().setParallel(z);//the parallel in the other tree is still pointing to y. so we must change it so it points to z.
        }
        if(y.getColor().equals(new String("Black")))
        {
            this.DeleteFixup(x);
        }
        return y;
    }
    
    public void DeleteFixup(Node x)
    {
        while(x != root && x.getColor().equals(new String("Black")))
        {
            if(x == x.getP().getLeft()) //if x is left son
            {
                Node w = x.getP().getRight();
                if(w.getColor().equals(new String("Red")))
                {
                    w.setColor(new String("Black"));
                    x.getP().setColor(new String("Red"));
                    LeftRotate(x.getP());
                    w = x.getP().getRight();
                }
                if(w.getLeft().getColor().equals(new String("Black")) && w.getRight().getColor().equals(new String("Black")))
                {
                    w.setColor(new String("Red"));
                    x = x.getP();
                }
                else
                {
                    if(w.getRight().getColor().equals(new String("Black")))
                    {
                        w.getLeft().setColor(new String("Black"));
                        w.setColor(new String("Red"));
                       RightRotate(w);
                       w = x.getP().getRight();
                    }
                    w.setColor(x.getP().getColor());
                    x.getP().setColor(new String("Black"));
                    w.getRight().setColor(new String("Black"));
                    LeftRotate(x.getP());
                    x = root;
                }
            }
            else
            {
                Node w = x.getP().getLeft();
                if(w.getColor().equals(new String("Red")))
                {
                    w.setColor(new String("Black"));
                    x.getP().setColor(new String("Red"));
                    RightRotate(x.getP());
                    w = x.getP().getLeft();
                }
                if(w.getRight().getColor().equals(new String("Black")) && w.getLeft().getColor().equals(new String("Black")))
                {
                    w.setColor(new String("Red"));
                    x = x.getP();
                }
                else
                {
                    if(w.getLeft().getColor().equals(new String("Black")))
                    {
                       w.getRight().setColor(new String("Black"));
                       w.setColor(new String("Red"));
                       LeftRotate(w);
                       w = x.getP().getLeft();
                    }
                    w.setColor(x.getP().getColor());
                    x.getP().setColor(new String("Black"));
                    w.getLeft().setColor(new String("Black"));
                    RightRotate(x.getP());
                    x = root;
                }
            }
        }
        x.setColor(new String("Black"));
    }
    
    public Node TreeSuccessor(Node x)
    {
        if(x.getRight() != nil)
        {
            return TreeMinimum(x.getRight());
        }
        Node y = x.getP();
        while(y != nil && x == y.getRight())
        {
            x = y;
            y = y.getP();
        }
        return y;
    }
    
    public Node TreeMinimum(Node x)
    {
        while(x.getLeft() != nil)
        {
            x = x.getLeft();
        }
        return x;
    }
    
    public Node TreeMaximum(Node x)
    {
        while(x.getRight() != nil)
        {
            x = x.getRight();
        }
        return x;
    }
}
