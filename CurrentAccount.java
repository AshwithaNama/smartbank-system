/**
 * Current account — supports overdraft up to a defined limit.
 */
public class CurrentAccount extends BankAccount {

    private static final double OVERDRAFT_LIMIT = 500.0;

    public CurrentAccount(String accountId) {
        super(accountId, AccountType.CURRENT, 0.0);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (getBalance() - amount < -OVERDRAFT_LIMIT) {
            throw new IllegalArgumentException(
                String.format("Exceeds overdraft limit of $%.2f.", OVERDRAFT_LIMIT)
            );
        }
        adjustBalance(-amount);
    }
}
