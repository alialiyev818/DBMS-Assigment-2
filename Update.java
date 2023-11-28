public class BookDAO {
    public void updateBookPrice(int bookId, double newPrice) throws SQLException {
        String sql = "UPDATE Books SET Price = ? WHERE BookID = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        }
    }
}
