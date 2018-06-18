package lib.commands;

import java.util.Map;

public class SearchCommandOptions {

    private String author;
    private String title;
    private String year;

    public void setFields(Map<String, String> map) {

        map.forEach((key, value) -> {
            try {
            getClass().getDeclaredField(key).set(this, value);
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

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
}
