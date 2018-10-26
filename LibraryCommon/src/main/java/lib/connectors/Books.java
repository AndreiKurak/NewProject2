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

    public void update(int id, Book updateBook) {
        for (Book book : booksList) {
            if (book.getId() == id) {
                if (updateBook.getAuthor() != null) {
                    book.setAuthor(updateBook.getAuthor());
                }
                if (updateBook.getTitle() != null) {
                    book.setTitle(updateBook.getTitle());
                }
                if (updateBook.getYear() != null) {
                    book.setYear(updateBook.getYear());
                }
            }
        }
    }

    public List<Book> list() {
        return booksList;
    }

    public List<Book> search(Book searchBook) {
        List<Book> searchResult = new ArrayList<>();
        for (Book book : booksList) {
            if (searchBook.getAuthor() != null && !searchBook.getAuthor().equals(book.getAuthor())) {
                continue;
            }
            if (searchBook.getTitle() != null && !searchBook.getTitle().equals(book.getTitle())) {
                continue;
            }
            if (searchBook.getYear() != null && !searchBook.getYear().equals(book.getYear())) {
                continue;
            }
            searchResult.add(book);
        }
        if (searchResult.isEmpty())
            return null;
        else
            return searchResult;
    }
}
