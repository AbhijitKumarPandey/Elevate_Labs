package Elevate_Labs;


import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor
    public Account(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created for " + accountHolder + " with balance: " + initialBalance);
    }


    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + " | Balance: " + balance);
            System.out.println(" Successfully deposited: " + amount);
        } else {
            System.out.println(" Invalid deposit amount!");
        }
    }


    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + " | Balance: " + balance);
            System.out.println("Successfully withdrew: " + amount);
        } else {
            System.out.println(" Insufficient balance or invalid amount!");
        }
    }

    // Check Balance
    public double getBalance() {
        return balance;
    }


    public void printTransactionHistory() {
        System.out.println("\n📜 Transaction History for " + accountHolder + ":");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}
public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Account Creation
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = sc.nextDouble();

        Account account = new Account(name, initialBalance);

        int choice;
        do {
            System.out.println("\n===== Banking Menu =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println(" Current Balance: " + account.getBalance());
                    break;
                case 4:
                    account.printTransactionHistory();
                    break;
                case 5:
                    System.out.println(" Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}

