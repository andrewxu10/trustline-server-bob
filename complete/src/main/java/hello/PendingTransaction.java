package hello;

public class PendingTransaction {
    private Long id;
    private String name;
    private Long balance;

    public PendingTransaction() {
        super();
    }
    public PendingTransaction(Long id, String name, Long balance) {
        super();
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public PendingTransaction(String name, Long balance) {
        super();
        this.name = name;
        this.balance = balance;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getBalance() {
        return balance;
    }
    public void setBalance(Long balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return String.format("Student [id=%s, name=%s, balance=%s]", id, name, balance);
    }
}