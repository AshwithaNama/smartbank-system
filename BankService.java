import java.util.HashMap;
import java.util.Map;

/**
 * Core service layer — registration, authentication, and transfers.
 */
public class BankService {

    private final Map<String, Customer> customers = new HashMap<>();

    public boolean register(String fullName, String username, String password, AccountType type) {
        if (customers.containsKey(username.toLowerCase())) return false;
        customers.put(username.toLowerCase(), new Customer(fullName, username, password, type));
        return true;
    }

    public Customer authenticate(String username, String password) {
        Customer customer = customers.get(username.toLowerCase());
        if (customer != null && customer.authenticate(password)) return customer;
        return null;
    }

    public boolean transfer(Customer sender, String recipientUsername, double amount) {
        Customer recipient = customers.get(recipientUsername.toLowerCase());
        if (recipient == null || recipient.getUsername().equals(sender.getUsername())) return false;
        try {
            sender.getAccount().withdraw(amount);
            recipient.getAccount().deposit(amount);
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}