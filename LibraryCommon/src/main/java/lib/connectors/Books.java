package lib.connectors;

import lib.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Books implements Serializable {

    List<Book> booksList = new ArrayList<>();

    public void add(Book book) {
        if (booksList.size() != 0)
            book.setId(booksList.get(booksList.size() - 1).getId() + 1);
        else
            book.setId(1);
        booksList.add(book);
    }

    public void delete(int bookId) {
        for (Book book : booksList)
            if (book.getId() == bookId) {
                booksList.remove(book);
                break;
            }
    }

    public void update(int id, Book book) {
        if (book.getAuthor() != null){
            booksList.get(id).setAuthor(book.getAuthor());
        }
        if (book.getTitle() != null){
            booksList.get(id).setTitle(book.getTitle());
        }
        if (book.getYear() != null){
            booksList.get(id).setYear(book.getYear());
        }
    }

    public List<Book> list() {
        return booksList;
    }

    public List<Book> search(Book searchBook) {
        List<Book> searchResult = new ArrayList<>();
        searchResult.addAll(booksList);

        for (Book book : searchResult){
            if (searchBook.getAuthor() != null && !book.getAuthor().equals(searchBook.getAuthor())) {
                searchResult.remove(book);
                continue;
            }
            if (searchBook.getTitle() != null && !book.getTitle().equals(searchBook.getTitle())) {
                searchResult.remove(book);
                continue;
            }
            if (searchBook.getYear() != null && !book.getYear().equals(searchBook.getYear())) {
                searchResult.remove(book);
            }
        }
        if (searchResult.isEmpty())
            return null;
        else
            return searchResult;
    }
}
