package lib.connectors;

import lib.Book;
import lib.command_options.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Books implements Serializable {

    ArrayList<Book> booksList = new ArrayList<>();

    public void add(Object options) throws Exception{
        Book book = new Book();
        AddCommandOptions addCommandOptions = (AddCommandOptions) options;
        System.out.println(addCommandOptions.getAuthor());
        book.setAuthor(addCommandOptions.getAuthor());
        book.setTitle(addCommandOptions.getTitle());
        book.setYear(addCommandOptions.getYear());

        book.setId(booksList.size() + 1);
        booksList.add(book);
    }

    public void delete(Object options) throws Exception{
        DeleteCommandOptions deleteCommandOptions = (DeleteCommandOptions) options;
        int id = Integer.valueOf(deleteCommandOptions.getId()) - 1;
        booksList.remove(id);
    }

    public void update(Object options) throws Exception {
        UpdateCommandOptions updateCommandOptions = (UpdateCommandOptions) options;
        int id = Integer.valueOf(updateCommandOptions.getId()) - 1;
        if (updateCommandOptions.getAuthor() != null){
            booksList.get(id).setAuthor(updateCommandOptions.getAuthor());
        }
        if (updateCommandOptions.getTitle() != null){
            booksList.get(id).setTitle(updateCommandOptions.getTitle());
        }
        if (updateCommandOptions.getYear() != null){
            booksList.get(id).setYear(updateCommandOptions.getYear());
        }
    }

    public List list(Object options) throws Exception {
        ListCommandOptions listCommandOptions = (ListCommandOptions) options;
        List model;
        if (listCommandOptions.getAll() != null) {
            model = new ArrayList<Book>();
            model.addAll(booksList);
        }
        else{
            model = new ArrayList<String>();
            for (Book book : booksList){
                if (listCommandOptions.getAuthors() != null)
                    model.add(book.getAuthor());
                if (listCommandOptions.getTitles() != null)
                    model.add(book.getTitle());
                if (listCommandOptions.getYears() != null)
                    model.add(book.getYear());
            }
        }
        return model;
    }

    public List<Book> search(Object options) throws Exception {
        SearchCommandOptions searchCommandOptions = (SearchCommandOptions) options;
        for (Book book : booksList){
            if (searchCommandOptions.getAuthor() != null)
                if (!book.getAuthor().equals(searchCommandOptions.getAuthor())){
                    booksList.remove(book);
                    continue;
                }
            if (searchCommandOptions.getTitle() != null)
                if (!book.getTitle().equals(searchCommandOptions.getTitle())){
                    booksList.remove(book);
                    continue;
                }
            if (searchCommandOptions.getYear() != null)
                if (!book.getYear().equals(searchCommandOptions.getYear())){
                    booksList.remove(book);
                }
        }
        return booksList;
    }
}
