package examples_test.chapter1_9;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class CachingMovieLister {

    @PostConstruct
    public void populateMovieCache() {
        // populates the movie cache upon initialization...
    }

    @PreDestroy
    public void clearMovieCache() {
        // clears the movie cache upon destruction...
    }
}
