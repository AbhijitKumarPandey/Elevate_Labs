package Elevate_Labs;

import java.util.ArrayList;
import java.util.List;

// ---------- Book Class ----------
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }

    public boolean issueBook() {
        if (!isIssued) {
            isIssued = true;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (isIssued) {
            isIssued = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return id + ": " + title + " by " + author + " [" + (isIssued ? "Issued" : "Available") + "]";
    }
}

// ---------- User Class ----------
class User {
    int id;
    private String name;
    private int maxBooksAllowed = 3;
    private List<Book> issuedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        issuedBooks = new ArrayList<>();
    }

    public boolean issueBook(Book book) {
        if (issuedBooks.size() < maxBooksAllowed && book.issueBook()) {
            issuedBooks.add(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        if (issuedBooks.remove(book)) {
            book.returnBook();
            return true;
        }
        return false;
    }

    public void listIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println(name + " has no issued books.");
        } else {
            System.out.println(name + "'s issued books:");
            for (Book b : issuedBooks) {
                System.out.println(" - " + b.getTitle());
            }
        }
    }

    public String getName() { return name; }
}

// ---------- Library Class ----------
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }
    public void addUser(User user) { users.add(user); }

    private Book findBookById(int id) {
        for (Book b : books) if (b.getId() == id) return b;
        return null;
    }

    private User findUserById(int id) {
        for (User u : users) if (u.id == id) return u;
        return null;
    }

    public boolean issueBookToUser(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book != null && user != null) return user.issueBook(book);
        return false;
    }

    public boolean returnBookFromUser(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book != null && user != null) return user.returnBook(book);
        return false;
    }

    public void listBooks() {
        System.out.println("\nLibrary Books:");
        for (Book b : books) System.out.println(b);
    }
}
public class Library_Management {
    public static void main(String[] args) {
        Library lib = new Library();

        // Adding books
        lib.addBook(new Book(1, "Java Programming", "Herbert Schildt"));
        lib.addBook(new Book(2, "C++ Programming", "Bjarne Stroustrup"));
        lib.addBook(new Book(3, "Python Basics", "Guido van Rossum"));

        // Adding users
        lib.addUser(new User(101, "Abhijit"));
        lib.addUser(new User(102, "Archana"));

        // List all books
        lib.listBooks();

        // Issue books
        System.out.println("\nIssuing Java book to Abhijit:");
        if (lib.issueBookToUser(1, 101)) System.out.println("Book issued successfully!");
        else System.out.println("Cannot issue book.");

        System.out.println("Trying to issue Java book to Archana:");
        if (lib.issueBookToUser(1, 102)) System.out.println("Book issued successfully!");
        else System.out.println("Cannot issue book.");

        // List books after issuing
        lib.listBooks();

        // Return book
        System.out.println("\nReturning Java book from Abhijit:");
        if (lib.returnBookFromUser(1, 101)) System.out.println("Book returned successfully!");
        else System.out.println("Cannot return book.");

        // List books after returning
        lib.listBooks();
    }
}
