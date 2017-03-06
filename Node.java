public class Node
{
    private String color;
    private Node left;
    private Node right;
    private Node p;
    private Customer customer;
    private Node parallel;
    
    public Node(Customer customer)
    {
        this.color = null;
        this.left = null;
        this.right = null;
        this.p = RBTree.getNil();
        this.customer = customer;
        this.parallel = null;
    }
    
    public Node()//this constructor is used only to create nil
    {
        this.color = new String("Black");
        this.left = null;
        this.right = null;
        this.p = null;
        this.customer = null;
        this.parallel = null;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public Node getRight()
    {
        return right;
    }
    
    public Node getLeft()
    {
        return left;
    }
    
    public Node getP()
    {
        return p;
    }
    
    public Customer getCustomer()
    {
        return customer;
    }
    
    public Node getParallel()
    {
        return parallel;
    }
    
    public void setColor(String color)
    {
        this.color = color;
    }
    
    public void setRight(Node right)
    {
        this.right = right;
    }
    
    public void setLeft(Node left)
    {
        this.left = left;
    }
    
    public void setP(Node p)
    {
        this.p = p;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    public void setParallel(Node parallel)
    {
        this.parallel = parallel;
    }
}
