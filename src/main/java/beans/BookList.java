package beans;

import db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Developer on 24.03.2016.
 */
public class BookList {
    private List<Book> bookList = new ArrayList<>();

    private List<Book> getBooks() {

        String query = "SELECT * from BOOK order by name";
        try (ResultSet resultSet = DbConnection.getResultSetByQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setName(resultSet.getString("name"));
                book.setGenre(resultSet.getString("genre"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPageCount(resultSet.getInt("page_count"));
                book.setPublishDate(resultSet.getDate("publish_date"));
                book.setPublisher(resultSet.getString("publisher"));

                bookList.add(book);

            }
        } catch (SQLException e) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, e);
        }

        return bookList;
    }

    public List<Book> getBookList() {
        return !bookList.isEmpty() ? bookList : getBooks();
    }
}
