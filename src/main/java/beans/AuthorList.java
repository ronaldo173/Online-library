package beans;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorList {

    private ArrayList<Author> authorList = new ArrayList<Author>();

    private ArrayList<Author> getAuthors() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select * from author")) {
                while (resultSet.next()) {
                    Author author = new Author();
                    author.setName(resultSet.getString("fio"));
                    authorList.add(author);
                }
            }


        } catch (SQLException e) {
            Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, e);
        }

        return authorList;
    }

    public ArrayList<Author> getAuthorList() {
        if (!authorList.isEmpty()) {
            return authorList;
        } else {
            ArrayList<Author> authors = getAuthors();
            Collections.sort(authors);
            return authors;
        }
    }
}
