import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books JOIN Authors ON Books.AuthorID = Authors.AuthorID";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Assuming Book is a class that represents the book entity
                Book book = new Book(
                    rs.getInt("BookID"),
                    rs.getString("Title"),
                    // ... other fields
                );
                books.add(book);
            }
        }
        return books;
    }
}
