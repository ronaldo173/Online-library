package beans;

import java.awt.*;
import java.io.DataInput;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Developer on 24.03.2016.
 */
public class Book implements Serializable {
    private String name;
    private byte[] content;
    private int pageCount;
    private String isbn;
    private String genre;
    private String author;
    private Date publishDate;
    private String publisher;
    private Image image;

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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
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
                ", publishDate=" + publishDate +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
