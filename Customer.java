public class Customer
{
    private String name;
    private int accountId;
    private int customerId;
    private int balance;
    
    public Customer(String name, int accountId, int customerId, int balance)
    {
        this.name = name;
        this.accountId= accountId;
        this.customerId = customerId;
        this.balance = balance;
    }

    public String getName()
    {
        return this.name;
    }
    public int getAccountId()
    {
        return this.accountId;   
    }
    public int getCustomerId()
    {
        return this.customerId;
    }
    public int getBalance()
    {
        return this.balance;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }
    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }
    public void setBalance(int balance)
    {
        this.balance = balance;
    }
    public void addToBalance(int amount)
    {
        this.balance = balance + amount;
    }
}
