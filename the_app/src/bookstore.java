import java.sql.*;
import java.util.Scanner;

public class bookstore {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/bookstore";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "8118";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD)) {

            System.out.println("Choose an action: \n1. Create Book\n2. Create Author\n3. Create Customer\n4. Create Order\n5. Retrieve\n6. Update\n7. Delete\n8 - Get Metadata for Table Structure\n9 - Get Metadata for individual table");
            String choice = scanner.nextLine();

            switch (choice) {}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static String promptForAction(Scanner scanner) {
        System.out.println("Choose an action: add, view, update, delete");
        return scanner.nextLine().toLowerCase();
    }
}