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
public class GenreList {
    private List<Genre> genreList = new ArrayList<>();

    private List<Genre> getGenres() {

        try (ResultSet resultSet = DbConnection.getResultSetByQuery("select * from genre order by name")) {

            while (resultSet.next()) {
                Genre genre = new Genre(resultSet.getString("name"), resultSet.getLong("id"));
                genreList.add(genre);
            }
        } catch (SQLException e) {
            Logger.getLogger(GenreList.class.getName()).log(Level.SEVERE, null, e);
        }

        return genreList;
    }

    public List<Genre> getGenreList() {
        return !genreList.isEmpty() ? genreList : getGenres();
    }

    public static void main(String[] args) {

        List<Book> bookList = new BookList().getBookList();
        System.out.println(bookList);
        Book book = bookList.get(0);
        System.out.println(book);



    }
}
