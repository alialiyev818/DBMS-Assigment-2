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

            switch (choice) {
                case "1":
                    insertBook(scanner, conn);
                    break;
                case "2":
                    insertAuthor(scanner, conn);
                    break;
                case "3":
                    insertCustomer(scanner, conn);
                    break;
                case "4":
                    insertOrder(scanner, conn);
                    break;
                case "5":
                    retrieve(conn);
                    break;
                case "6":
                    update(scanner, conn);
                    break;
                case "7":
                    delete(scanner, conn);
                    break;
                case "8":
                    getTableStructures(conn);
                case"9":
                    System.out.print("Table name: ");
                    String name_of_table = scanner.nextLine();
                    get_metadata(conn, name_of_table);
                default:
                    System.out.println("Invalid choice.");
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

    private static void insertOrder(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Inserting a new order.");
        System.out.print("Enter order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter payment amount: ");
        int payment = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter order quantity: ");
        int orderQuantity = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO Orders (order_id, payment, customer_id, book_id, orders_quantity) VALUES (?, ?, ?, ?, ?)";
        String checkStock = "SELECT quantity FROM Books WHERE book_id = ?";
        String updateQuantitySql = "UPDATE Books SET quantity = quantity - ? WHERE book_id = ?";

        try (PreparedStatement checkQuantity = conn.prepareStatement(checkStock)) {

            checkQuantity.setInt(1, bookId);
            ResultSet resultSet = checkQuantity.executeQuery();

            if (!resultSet.next() || resultSet.getInt("quantity") < orderQuantity) {
                throw new SQLException("There is not enough books in the stock");
            }
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, payment);
            pstmt.setInt(3, customerId);
            pstmt.setInt(4, bookId);
            pstmt.setInt(5, orderQuantity);
            pstmt.executeUpdate();
            System.out.println("Order added successfully.");
        }


        try (PreparedStatement updateBookVolumeStmt = conn.prepareStatement(updateQuantitySql)) {
            updateBookVolumeStmt.setInt(1, orderQuantity);
            updateBookVolumeStmt.setInt(2, bookId);
            updateBookVolumeStmt.executeUpdate();
        }
    }

    private static void retrieve(Connection conn) throws SQLException {
        String sql = "SELECT b.name, b.quantity, a.name AS author_name, " +
                "COALESCE(SUM(o.orders_quantity), 0) AS total_orders_quantity " +
                "FROM Books b " +
                "LEFT JOIN Authors a ON b.author_id = a.author_id " +
                "LEFT JOIN Orders o ON b.book_id = o.book_id " +
                "GROUP BY b.name, a.name, b.quantity";


        System.out.println("Displaying all books with associated orders and authors:");
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String bookName = rs.getString("name");
                String authorName = rs.getString("author_name");
                String quantity = rs.getString("quantity");
                String orderInfo = rs.getString("total_orders_quantity");
                System.out.println("Book: " + bookName + ", Quantity " + quantity + ", Author: " + authorName + ", Order Quantity: " + orderInfo);
            }
        }
    }


    private static void update(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Updating a book.");
        System.out.print("Enter the ID of the book to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new name (press enter to skip): ");
        String name = scanner.nextLine();
        System.out.print("Enter new quantity (enter 0 to skip): ");
        int quantity = Integer.parseInt(scanner.nextLine());

        String sql = "UPDATE Books SET name = COALESCE(NULLIF(?, ''), name), " +
                "quantity = COALESCE(NULLIF(?, 0), quantity) WHERE book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, quantity);
            pstmt.setInt(3, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " book(s) updated.");
        }
    }

    private static void delete(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Deleting a book.");
        System.out.print("Enter the ID of the book to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        String sql = "DELETE FROM Books WHERE book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " book(s) deleted.");
        }
    }

    private static void get_metadata(Connection conn, String table_name) {
        try {

            getColumnDetails(conn, table_name);
            getPrimaryKeys(conn, table_name);
            getForeignKeys(conn, table_name);

        } catch (SQLException e) {

            System.out.println("An error occurred while accessing metadata for table: " + table_name);
            e.printStackTrace();
        }
    }

    private static void getTableStructures(Connection conn) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();

        try (ResultSet rsTables = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
            while (rsTables.next()) {
                System.out.println("Found Table: " + rsTables.getString("TABLE_NAME") + ", Type: " + rsTables.getString("TABLE_TYPE"));
            }
        }
    }

    private static void getColumnDetails(Connection conn, String table_name) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();

        System.out.println("Details of columns in table " + table_name + ":");
        try (ResultSet rsColumns = metaData.getColumns(null, null, table_name, null)) {
            while (rsColumns.next()) {
                System.out.println("Column Name: " + rsColumns.getString("COLUMN_NAME") +
                        ", Data Type: " + rsColumns.getString("TYPE_NAME") +
                        ", Width: " + rsColumns.getInt("COLUMN_SIZE"));
            }
        }
    }

    private static void getPrimaryKeys(Connection conn, String table_name) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();

        System.out.println("Listing primary keys in table: " + table_name);
        try (ResultSet rsPrimaryKeys = metaData.getPrimaryKeys(null, null, table_name)) {
            while (rsPrimaryKeys.next()) {
                System.out.println("Primary Key Column: " + rsPrimaryKeys.getString("COLUMN_NAME") +
                        ", Key Name: " + rsPrimaryKeys.getString("PK_NAME"));
            }
        }
    }

    private static void getForeignKeys(Connection conn, String table_name) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();

        System.out.println("Foreign keys associated with table " + table_name + ":");
        try (ResultSet rsForeignKeys = metaData.getImportedKeys(null, null, table_name)) {
            while (rsForeignKeys.next()) {
                System.out.println("FK Name: " + rsForeignKeys.getString("FK_NAME") +
                        ", Column: " + rsForeignKeys.getString("FKCOLUMN_NAME") +
                        ", Reference Table: " + rsForeignKeys.getString("PKTABLE_NAME") +
                        ", Reference Column: " + rsForeignKeys.getString("PKCOLUMN_NAME"));
            }
        }
    }
}