package Model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DatastoreTest {

    @Test
    void searchActorsByName__pass_unmatchedName_return_emptyList() {
        List<Actor> foundActor = Datastore.searchActorsByName("John");
        assertTrue(foundActor.isEmpty());
    }

    @Test
    void searchActorsByName__pass_matchingFirstName_return_correctActors() {
        List<Actor> foundActor = Datastore.searchActorsByName("Tom");
        assertTrue(foundActor.size() == 1);
        assertEquals("Tom", foundActor.get(0).getFirstName());
    }

    @Test
    void searchActorsByName__pass_matchingLastName_return_correctActors() {
        List<Actor> foundActor = Datastore.searchActorsByName("McKean");
        assertTrue(foundActor.size() == 1);
        assertEquals("McKean", foundActor.get(0).getLastName());
    }

    @Test
    void searchMoviesByTitle__pass_unmatchedTitle_return_emptyList() {
        List<Movie> foundMovies = Datastore.searchMoviesByTitle("Spiderman");
        assertTrue(foundMovies.isEmpty());
    }

    @Test
    void searchMoviesByTitle__pass_matchedTitle_return_correctMovies() {
        List<Movie> foundMovies = Datastore.searchMoviesByTitle("Top");
        assertTrue(foundMovies.size() == 1);
        assertEquals("Top Gun", foundMovies.get(0).getTitle());
    }

    @Test
    void searchTheatersByName__pass_unmatchedName_return_emptyList() {
        List<Theater> foundTheaters = Datastore.searchTheatersByName("Oak Cinema");
        assertTrue(foundTheaters.isEmpty());
    }

    @Test
    void searchTheatersByName__pass_matchedName_return_correctTheaters() {
        List<Theater> foundTheaters = Datastore.searchTheatersByName("AMC");
        assertEquals(1, foundTheaters.size());
        assertTrue(foundTheaters.get(0).getName().contains("AMC"));
    }
}