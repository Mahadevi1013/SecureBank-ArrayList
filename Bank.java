import java.util.ArrayList;
import java.util.List;

/**
 * ARRAYLIST-BACKED VERSION
 * ------------------------
 * Accounts are stored in a plain ArrayList<Account>, in creation order.
 * There is no direct key -> value mapping, so every lookup by ID
 * (deposit, withdraw, balance, close) has to walk the list checking each
 * Account's id field: O(n) worst case, versus O(1) average case for the
 * HashMap version. For a small in-memory demo this is invisible, but it's
 * the exact tradeoff interviewers ask about: HashMap trades some memory
 * overhead and no guaranteed ordering for constant-time lookup, while
 * ArrayList keeps insertion order and less memory overhead but pays a
 * linear scan per lookup.
 */
public class Bank {
    private final List<Account> accounts = new ArrayList<>();
    private int nextId = 1;

    /** Creates a new account and assigns it the next available ID. Always succeeds. */
    public Account createAccount(String customerName, double initialBalance) {
        Account acc = new Account(nextId, customerName, initialBalance);
        accounts.add(acc);
        nextId++;
        return acc;
    }

    /** Adds amt to the account's balance. */
    public void deposit(int id, double amt) throws AccountNotFoundException {
        Account acc = getAccountOrThrow(id);
        acc.setBalance(acc.getBalance() + amt);
    }

    /** Subtracts amt from the account's balance, if there's enough to cover it. */
    public void withdraw(int id, double amt) throws AccountNotFoundException, InsufficientFundsException {
        Account acc = getAccountOrThrow(id);
        if (amt > acc.getBalance()) {
            throw new InsufficientFundsException(
                String.format("Insufficient funds. Requested ₹%.2f but balance is only ₹%.2f.", amt, acc.getBalance()));
        }
        acc.setBalance(acc.getBalance() - amt);
    }

    /** Returns the current balance of an account. */
    public double getBalance(int id) throws AccountNotFoundException {
        return getAccountOrThrow(id).getBalance();
    }

    /** Permanently removes an account from the ledger. */
    public void closeAccount(int id) throws AccountNotFoundException {
        Account acc = getAccountOrThrow(id);
        accounts.remove(acc);
    }

    /** Returns all accounts currently in the ledger (for listing/display). */
    public List<Account> getAllAccounts() {
        return accounts;
    }

    // --- Helper: linear scan for the account with a matching ID.
    // This is the O(n) step the HashMap version avoids. ---
    private Account getAccountOrThrow(int id) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                return acc;
            }
        }
        throw new AccountNotFoundException("Account #" + id + " not found (never created, or already closed).");
    }
}
