import java.util.Scanner;

/**
 * SecureBank API — Week 1 (ArrayList-backed)
 * Same menu and behavior as the HashMap version — only the storage
 * data structure inside Bank.java differs.
 */
public class BankConsoleApp {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": // Create account
                    System.out.print("Customer name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Initial deposit amount: ");
                    double initial = readDouble(sc);
                    Account created = bank.createAccount(name, initial);
                    System.out.println("Account created -> " + created);
                    break;

                case "2": // Deposit
                    System.out.print("Account ID: ");
                    int depId = readInt(sc);
                    System.out.print("Amount to deposit: ");
                    double depAmt = readDouble(sc);
                    try {
                        bank.deposit(depId, depAmt);
                        System.out.printf("Deposited ₹%.2f. New balance: ₹%.2f%n", depAmt, bank.getBalance(depId));
                    } catch (AccountNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "3": // Withdraw
                    System.out.print("Account ID: ");
                    int wId = readInt(sc);
                    System.out.print("Amount to withdraw: ");
                    double wAmt = readDouble(sc);
                    try {
                        bank.withdraw(wId, wAmt);
                        System.out.printf("Withdrew ₹%.2f. New balance: ₹%.2f%n", wAmt, bank.getBalance(wId));
                    } catch (AccountNotFoundException | InsufficientFundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "4": // Balance inquiry
                    System.out.print("Account ID: ");
                    int bId = readInt(sc);
                    try {
                        System.out.printf("Balance for Account #%d: ₹%.2f%n", bId, bank.getBalance(bId));
                    } catch (AccountNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "5": // Close account
                    System.out.print("Account ID: ");
                    int cId = readInt(sc);
                    try {
                        bank.closeAccount(cId);
                        System.out.println("Account #" + cId + " closed.");
                    } catch (AccountNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "6": // List all accounts
                    if (bank.getAllAccounts().isEmpty()) {
                        System.out.println("No accounts yet.");
                    } else {
                        bank.getAllAccounts().forEach(System.out::println);
                    }
                    break;

                case "7": // Exit
                    System.out.println("Exiting SecureBank. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Choose 1-7.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== SecureBank (ArrayList) =====");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. Close Account");
        System.out.println("6. List All Accounts");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    // --- Small input helpers so a bad number doesn't crash the app ---
    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid whole number: ");
            }
        }
    }

    private static double readDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid amount: ");
            }
        }
    }
}
