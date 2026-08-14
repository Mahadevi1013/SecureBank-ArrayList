/**
 * Represents a single bank account in the SecureBank ledger.
 */
public class Account {
    private final int id;
    private final String customerName;
    private double balance;

    public Account(int id, String customerName, double balance) {
        this.id = id;
        this.customerName = customerName;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Account #%d | Holder: %-15s | Balance: ₹%.2f", id, customerName, balance);
    }
}
