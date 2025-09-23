package Elevate_Labs;


import java.util.*;


    class Student {
        int id;
        String name;
        double marks;

        Student(int id, String name, double marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
        }
    }


        public class Student_Management {
        private static ArrayList<Student> students = new ArrayList<>();
        private static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {
            int choice;
            do {
                System.out.println("\n=== Student Record Management System ===");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> updateStudent();
                    case 4 -> deleteStudent();
                    case 5 -> System.out.println("Exiting... Goodbye!");
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 5);
        }


        private static void addStudent() {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            students.add(new Student(id, name, marks));
            System.out.println("Student added successfully!");
        }


        private static void viewStudents() {
            if (students.isEmpty()) {
                System.out.println("No student records found.");
            } else {
                System.out.println("\n--- Student List ---");
                for (Student s : students) {
                    System.out.println(s);
                }
            }
        }


        private static void updateStudent() {
            System.out.print("Enter Student ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            for (Student s : students) {
                if (s.id == id) {
                    System.out.print("Enter new Name: ");
                    s.name = sc.nextLine();
                    System.out.print("Enter new Marks: ");
                    s.marks = sc.nextDouble();
                    System.out.println("Student record updated!");
                    return;
                }
            }
            System.out.println("Student with ID " + id + " not found.");
        }

         
        private static void deleteStudent() {
            System.out.print("Enter Student ID to delete: ");
            int id = sc.nextInt();

            Iterator<Student> itr = students.iterator();
            while (itr.hasNext()) {
                Student s = itr.next();
                if (s.id == id) {
                    itr.remove();
                    System.out.println("Student record deleted!");
                    return;
                }
            }
            System.out.println("Student with ID " + id + " not found.");
        }
    }

