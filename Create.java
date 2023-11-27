import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class Create {
    public void insertBook(String title, int authorId, String isbn, double price, int stock, Date publishedDate) throws SQLException {
        String sql = "INSERT INTO Books (Title, AuthorID, ISBN, Price, Stock, PublishedDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, authorId);
            pstmt.setString(3, isbn);
            pstmt.setDouble(4, price);
            pstmt.setInt(5, stock);
            pstmt.setDate(6, new java.sql.Date(publishedDate.getTime()));
            pstmt.executeUpdate();
        }
    }
}