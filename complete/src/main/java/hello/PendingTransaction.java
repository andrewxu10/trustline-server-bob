package hello;

public class PendingTransaction {
    private Long change;
    private Long proposedBalance;

    public PendingTransaction() {
        super();
    }
    public PendingTransaction(Long change, Long proposedBalance) {
        super();
        this.change = change;
        this.proposedBalance = proposedBalance;
//        this.balance = balance;
    }

//    public PendingTransaction(String name, Long balance) {
//        super();
//        this.name = name;
//        this.balance = balance;
//    }
    public Long getChange() {
        return change;
    }
    public void setChange(Long change) {
        this.change = change;
    }

//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }

    public Long getProposedBalance() {
        return proposedBalance;
    }
    public void setProposedBalance(Long proposedBalance) {
        this.proposedBalance = proposedBalance;
    }
    @Override
    public String toString() {
        return String.format("Student [change=%s, proposedBalance=%s]", change, proposedBalance);
    }
}