package beans;

import db.DbConnection;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Developer on 24.03.2016.
 */
public class Book implements Serializable {
    private long id;
    private String name;
    private byte[] content;
    private int pageCount;
    private String isbn;
    private String genre;
    private String author;
    private int publishYear;
    private String publisher;
    private byte[] image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", content=" + Arrays.toString(content) +
                ", pageCount=" + pageCount +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    public void fillPdfContent() {
        try (ResultSet resultSet = DbConnection.getResultSetByQuery("select content from book where id = " + this.getId());
        ) {

            while (resultSet.next()) {
                this.setContent(resultSet.getBytes("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
