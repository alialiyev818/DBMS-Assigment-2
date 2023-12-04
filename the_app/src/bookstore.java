import java.sql.*;
import java.util.Scanner;

public class bookstore {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/bookstore";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "8118";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
                Connection conn = DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD)) {

            System.out.println(
                    "Choose an action: \n1. Create Book\n2. Create Author\n3. Create Customer\n4. Create Order\n5. Retrieve\n6. Update\n7. Delete\n8 - Get Metadata for Table Structure\n9 - Get Metadata for individual table");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    insertBook(scanner, conn);
                    break;
                case "2":
                    insertAuthor(scanner, conn);
                    break;
                case "3":
                    insertCustomer(scanner, conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String promptForAction(Scanner scanner) {
        System.out.println("Choose an action: add, view, update, delete");
        return scanner.nextLine().toLowerCase();
    }

    private static void insertBook(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Inserting a new book.");
        System.out.print("Enter book ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter book name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter author ID: ");
        int authorId = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO Books (book_id, name, quantity, author_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, authorId);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        }
    }

    private static void insertAuthor(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Inserting a new author.");
        System.out.print("Enter author ID: ");
        int authorId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter author's name: ");
        String authorName = scanner.nextLine();

        String sql = "INSERT INTO Authors (author_id, name) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, authorId);
            pstmt.setString(2, authorName);
            pstmt.executeUpdate();
            System.out.println("Author added successfully.");
        }
    }

    private static void insertCustomer(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Inserting a new customer.");
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter customer's name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer's age: ");
        int age = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO Customers (customer_id, name, age) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, customerName);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
            System.out.println("Customer added successfully.");
        }
    }

}