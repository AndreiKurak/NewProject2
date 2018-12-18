package lib.hibernate_tests.proxies.method2;

import lib.hibernate_tests.TestBookGenres;
import lib.hibernate_tests.TestBookISBN;

public interface TestBookInterface {

    TestBookISBN getNumber();

    void setNumber(TestBookISBN number);

    TestBookGenres getBookGenre();

    void setBookGenre(TestBookGenres bookGenre);

    Integer getId();

    void setId(Integer id);

    String getAuthor();

    void setAuthor(String author);

    String getTitle();

    void setTitle(String title);

    String getYear();

    void setYear(String year);

    long getVersion();

    void setVersion(long version);
}
