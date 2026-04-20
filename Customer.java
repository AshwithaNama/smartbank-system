public class Customer {

    private final String fullName;
    private final String username;
    private final String passwordHash;
    private final BankAccount account;

    /** New registration — raw password gets hashed. */
    public Customer(String fullName, String username, String rawPassword, AccountType type) {
        this.fullName     = fullName;
        this.username     = username.toLowerCase();
        this.passwordHash = hashPassword(rawPassword);
        this.account      = createAccount(type);
    }

    /** DB reconstruction — password already hashed. */
    public Customer(String fullName, String username, String passwordHash, BankAccount account) {
        this.fullName     = fullName;
        this.username     = username.toLowerCase();
        this.passwordHash = passwordHash;
        this.account      = account;
    }

    public boolean authenticate(String rawPassword) {
        return passwordHash.equals(hashPassword(rawPassword));
    }

    public String getFullName()     { return fullName; }
    public String getUsername()     { return username; }
    public String getPasswordHash() { return passwordHash; }
    public BankAccount getAccount() { return account; }

    private String hashPassword(String password) {
        return String.valueOf(password.hashCode());
    }

    private BankAccount createAccount(AccountType type) {
        String id = "ACC-" + username.toUpperCase() + "-" + (int)(Math.random() * 9000 + 1000);
        return type == AccountType.SAVINGS ? new SavingsAccount(id) : new CurrentAccount(id);
    }
}