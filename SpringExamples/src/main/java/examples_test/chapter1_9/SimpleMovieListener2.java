package examples_test.chapter1_9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SimpleMovieListener2 {

    private MovieFinder movieFinder;

    @Autowired
    private MovieCatalog[] movieCatalogs;

    @Autowired
    private ApplicationContext context;

    @Autowired(required = false)
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

}
