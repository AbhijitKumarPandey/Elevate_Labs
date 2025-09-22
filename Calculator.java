package Elevate_Labs;

import java.util.*;
public class Calculator {

        // Methods
        public static int add(int a, int b) {
            return a + b;
        }
        public static int subtract(int a, int b) {
            return a - b;
        }
        public static int multiply(int a, int b) {
            return a * b;
        }
        public static int divide(int a, int b) {
            if (b == 0) {
                System.out.println("Error: Cannot divide by zero!");
                return 0;
            }
            return a / b;
        }
        public static int mod(int a, int b) {
            return a % b;
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("Enter your First Number: ");
                int A = sc.nextInt();

                System.out.println("Enter your Second Number: ");
                int B = sc.nextInt();

                System.out.println("Enter your Operator (+, -, *, /, %): ");
                char operator = sc.next().charAt(0);

                switch (operator) {
                    case '+':
                        System.out.println("Result: " + add(A, B));
                        break;
                    case '-':
                        System.out.println("Result: " + subtract(A, B));
                        break;
                    case '*':
                        System.out.println("Result: " + multiply(A, B));
                        break;
                    case '/':
                        System.out.println("Result: " + divide(A, B));
                        break;
                    case '%':
                        System.out.println("Result: " + mod(A, B));
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }

                System.out.print("Do you want to continue? (yes/no): ");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("no")) {
                    running = false;
                    System.out.println("Exiting Calculator. Goodbye!");
                }
            }

        }
    }


