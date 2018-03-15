package lib;

import java.io.Serializable;

public class Book implements Serializable{

    String author = "";
    String title = "";
    String year = "";
    int id;

    public String toString(){
        return Integer.toString(id) + " --" + author + " --" + title + " --" + year;
    }
}
