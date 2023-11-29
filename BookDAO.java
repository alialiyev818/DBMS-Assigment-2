import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    void updateBook(Book book);
    void deleteBook(int bookId);
}