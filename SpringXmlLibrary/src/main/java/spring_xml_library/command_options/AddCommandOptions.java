package spring_xml_library.command_options;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddCommandOptions {

    @NotNull(message = "Missed option \"author\"")
    @Size(min = 2, message = "Too short value of option \"author\"")
    private String author;

    @NotNull(message = "Missed option \"title\"")
    @Size(min = 2, message = "Too short value of option \"title\"")
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
