package lib;

import java.io.Serializable;

public class Book implements Serializable {    //

    private String author = "";
    private String title = "";
    private String year = "";
    private int id;

    public Book() {}

    public Book(String author, String title, String year){
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return Integer.toString(id) + " --" + author + " --" + title + " --" + year;
    }
}
