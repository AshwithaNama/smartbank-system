import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all bank account types.
 * Encapsulates balance management and transaction history.
 */
public abstract class BankAccount implements Transactable { //abstract used to avoid direct instantiation, ensures only specific account types are created

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//static for all instances ..

    private final String accountId;//unique
    private final AccountType accountType;//SAVINGS or CURRENT 
    private double balance;
    private final List<String> transactionHistory;//stores transaction logs

    protected BankAccount(String accountId, AccountType accountType, double initialBalance) {//protected allows access within package and subclasses
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
        logTransaction("DEPOSIT", amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds.");
        balance -= amount;
        logTransaction("WITHDRAWAL", amount);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n--- Transaction History [" + accountId + "] ---");
       for (String t : transactionHistory) {
       System.out.println(t);
}
    }

    public String getAccountId() { return accountId; }
    public AccountType getAccountType() { return accountType; }

    void adjustBalance(double amount) {
        this.balance += amount;
    }

    private void logTransaction(String type, double amount) {
        String entry = String.format("[%s] %s: $%.2f | Balance: $%.2f",
                LocalDateTime.now().format(FORMATTER), type, amount, balance);
        transactionHistory.add(entry);
    }
    public List<String> getTransactionHistory() { 
        return Collections.unmodifiableList(transactionHistory);//prevent list.add() from outside.....
    }
}
