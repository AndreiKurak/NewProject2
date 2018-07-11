package lib.command_options;

public class AddCommandOptions {

    private String author;
    private String title;
    private String year;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String toString(){
        return author + title + year;
    }
}
