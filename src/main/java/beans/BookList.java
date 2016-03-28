package beans;

import db.DbConnection;
import enums.SearchType;

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

    private List<Book> getBooks(String query) {


        try (ResultSet resultSet = DbConnection.getResultSetByQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setGenre(resultSet.getString("genre_id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPageCount(resultSet.getInt("page_count"));
                book.setPublishYear(resultSet.getInt("publish_year"));
                book.setAuthor(resultSet.getString("fio"));

                book.setPublisher(resultSet.getString("publisher_name"));
                book.setImage(resultSet.getBytes("image"));

                bookList.add(book);

            }
        } catch (SQLException e) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, e);
        }

        return bookList;
    }

    public List<Book> getBookList() {
        return !bookList.isEmpty() ? bookList : getBooks("select * from book inner join" +
                " author on book.author_id=author.id inner join(select publisher.id, publisher.name as publisher_name from publisher) " +
                "publisher on \n" +
                "publisher_id = publisher.id order by book.name ;");
    }

    public List<Book> getBooksByGenre(long id) {
//        return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
//                + "inner join author a on b.author_id=a.id "
//                + "inner join genre g on b.genre_id=g.id "
//                + "inner join publisher p on b.publisher_id=p.id "
//                + "where genre_id=" + id + " order by b.name "
//                + "limit 0,5");

        return getBooks("select * from book inner join author on book.author_id=author.id inner join(select publisher.id, publisher.name as publisher_name from publisher) publisher on \n" +
                "publisher_id = publisher.id where genre_id=" + id + " order by book.name ");

    }

    //TODO
    public List<Book> getBooksByLetter(String letter) {
        String query = null;

        return getBooks(query);
    }

    public List<Book> getBooksBySearch(String searchStr, SearchType type) {
        String endQueryByType = null;


        switch (type) {
            case AUTHOR:
                endQueryByType = "lower(fio)";
                break;
            case TITLE:
                endQueryByType = "lower(name)";
        }

        String query = "select * from book inner join author on book.author_id=author.id inner join(select publisher.id, publisher.name as" +
                " publisher_name from publisher) publisher on " +
                "publisher_id = publisher.id where " + endQueryByType + " like '%" + searchStr.toLowerCase() +
                "%' order by book.name ;";
        System.out.println(endQueryByType);
        System.out.println(searchStr);
        System.out.println(query);


        return getBooks(query);
    }

    public static void main(String[] args) {
        List<Book> jav = new BookList().getBooksBySearch("j", SearchType.TITLE);
        for (Book book : jav) {
            System.out.println(book);
        }
    }
}
