package spring_xml_library.command_options;

import javax.validation.constraints.Digits;

public class SearchCommandOptions {

    private String author;

    private String title;

    @Digits(integer = 4, fraction = 0, message = "Incorrect type of option \"year\"")
    private String year;

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
}
