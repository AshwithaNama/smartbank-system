/**
 * Savings account — enforces a minimum balance requirement.
 */
public class SavingsAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 100.0;

    public SavingsAccount(String accountId) {
        super(accountId, AccountType.SAVINGS, 0.0);
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                String.format("Savings accounts must maintain a minimum balance of $%.2f.", MINIMUM_BALANCE)
            );
        }
        super.withdraw(amount);
    }
}