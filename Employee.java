import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
 import java.sql.ResultSet;
//import static java.sql.DriverManager.getConnection;

public class Employee {
    private static final String url = "jdbc:mysql://localhost:3306/employee";
    private static final String user = "root";
    private static final String Password = "Abhijit@123";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, Password);
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
        return con;
    }

    public static void AddEmployee(Scanner sc) {
        System.out.print("Enter Name");
        String name = sc.nextLine();
        System.out.println("Enter position ");
        String position = sc.nextLine();
        System.out.println("Enter your Sallary");
        double salary = sc.nextDouble();
        sc.nextLine();

        String sql = "Insert into employee(name,position,salary) values(?,?,?)";

        try
                (Connection con = getConnection();
                 PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, position);
            preparedStatement.setDouble(3, salary);

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + "Employee(s) added. ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void viewEmployees() {
        String sql = "SELECT * FROM employee";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("\n--- Employee List ---");
            System.out.printf("%-5s %-20s %-20s %-10s%n", "ID", "Name", "Position", "Salary");
            System.out.println("--------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-20s %-10.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployee(Scanner sc) {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        String sql = "UPDATE employee SET salary = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setDouble(1, salary);
            pst.setInt(2, id);

            int rows = pst.executeUpdate();
            System.out.println(rows + " employee(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ---- Delete Employee ----
    public static void deleteEmployee(Scanner sc) {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            int rows = pst.executeUpdate();
            System.out.println(rows + " employee(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Database App =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    AddEmployee(sc);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
