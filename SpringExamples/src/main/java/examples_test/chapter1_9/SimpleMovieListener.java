package examples_test.chapter1_9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import java.util.Optional;

public class SimpleMovieListener {

    private MovieFinder movieFinder;
    private MovieCatalog movieCatalog;

    @Required
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    @Autowired
    public void prepare(MovieFinder movieFinder, @Qualifier("main") MovieCatalog movieCatalog) {
        this.movieFinder = movieFinder;
        this.movieCatalog = movieCatalog;
    }
}
