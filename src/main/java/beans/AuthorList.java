package beans;

import db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorList {

    private ArrayList<Author> authorList = new ArrayList<Author>();

    private ArrayList<Author> getAuthors() {


        try (ResultSet resultSet = DbConnection.getResultSetByQuery("select * from author order by fio")) {
            while (resultSet.next()) {
                Author author = new Author();
                author.setName(resultSet.getString("fio"));
                authorList.add(author);
            }

        } catch (SQLException e) {
            Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, e);
        }

        return authorList;
    }

    public ArrayList<Author> getAuthorList() {
        return !authorList.isEmpty() ? authorList : getAuthors();
    }
}
