import java.util.Scanner;

/**
 * Entry point for the Banking System Simulation.
 */
public class BankingApp {

    private static final BankService bankService = new BankService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("       Welcome to SmartBank System        ");
        System.out.println("==========================================");

        while (true) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> registerUser();
                case "2" -> loginAndOperate();
                case "3" -> { System.out.println("Thank you for using SmartBank. Goodbye!"); return; }
                default  -> System.out.println("[!] Invalid option. Please try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private static void registerUser() {
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine().trim();

        System.out.print("Choose a unique username (e.g. ashwitha_92): ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Account type: 1. Savings  2. Current");
        System.out.print("Choose: ");
        String typeChoice = scanner.nextLine().trim();
        AccountType type = typeChoice.equals("2") ? AccountType.CURRENT : AccountType.SAVINGS;

        boolean success = bankService.register(fullName, username, password, type);
        if (success) {
            System.out.println("[✓] Account created successfully! Login with username: " + username);
        } else {
            System.out.println("[!] Username '" + username + "' is already taken. Please choose another.");
        }
    }

    private static void loginAndOperate() {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        Customer customer = bankService.authenticate(username, password);
        if (customer == null) {
            System.out.println("[!] Invalid credentials.");
            return;
        }
        System.out.println("\n[✓] Welcome, " + customer.getFullName() + "!");
        operationsMenu(customer);
    }

    private static void operationsMenu(Customer customer) {
        while (true) {
            System.out.println("\n--- Account Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> System.out.printf("Balance: $%.2f%n", customer.getAccount().getBalance());
                case "2" -> handleDeposit(customer);
                case "3" -> handleWithdraw(customer);
                case "4" -> handleTransfer(customer);
                case "5" -> customer.getAccount().printTransactionHistory();
                case "6" -> { System.out.println("Logged out."); return; }
                default  -> System.out.println("[!] Invalid option.");
            }
        }
    }

    private static void handleDeposit(Customer customer) {
        System.out.print("Deposit amount: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            customer.getAccount().deposit(amount);
            System.out.printf("[✓] Deposited $%.2f successfully.%n", amount);
        } catch (NumberFormatException e) {
            System.out.println("[!] Invalid amount.");
        } catch (IllegalArgumentException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void handleWithdraw(Customer customer) {
        System.out.print("Withdraw amount: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            customer.getAccount().withdraw(amount);
            System.out.printf("[✓] Withdrawn $%.2f successfully.%n", amount);
        } catch (NumberFormatException e) {
            System.out.println("[!] Invalid amount.");
        } catch (IllegalArgumentException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void handleTransfer(Customer customer) {
        System.out.print("Recipient username: ");
        String recipientUsername = scanner.nextLine().trim();
        System.out.print("Transfer amount: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            boolean success = bankService.transfer(customer, recipientUsername, amount);
            if (success) {
                System.out.printf("[✓] Transferred $%.2f to %s.%n", amount, recipientUsername);
            } else {
                System.out.println("[!] Recipient not found or transfer failed.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] Invalid amount.");
        } catch (IllegalArgumentException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }
}