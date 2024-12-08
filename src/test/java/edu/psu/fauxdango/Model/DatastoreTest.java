package edu.psu.fauxdango.Model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DatastoreTest {

    @Test
    void getMovieById__pass_validId__return_correctMovie() {
        Movie movie = Datastore.getMovieById(1);
        assertEquals("Top Gun", movie.getTitle());
    }

    @Test
    void getMovieById__pass_invalidId__return_null() {
        Movie movie = Datastore.getMovieById(100);
        assertNull(movie);
    }

    @Test
    void searchMoviesByTitle__pass_unmatchedTitle__return_emptyList() {
        List<Movie> foundMovies = Datastore.searchMoviesByTitle("Spiderman");
        assertTrue(foundMovies.isEmpty());
    }

    @Test
    void searchMoviesByTitle__pass_matchedTitle__return_correctMovies() {
        List<Movie> foundMovies = Datastore.searchMoviesByTitle("Top");
        assertTrue(foundMovies.size() == 1);
        assertEquals("Top Gun", foundMovies.get(0).getTitle());
    }

    @Test
    void getActorById__pass_validId__return_correctActor() {
        Actor actor = Datastore.getActorById(1);
        assertEquals("Tom", actor.getFirstName());
        assertEquals("Cruise", actor.getLastName());
    }

    @Test
    void getActorById__pass_invalidId__return_null() {
        Actor actor = Datastore.getActorById(100);
        assertNull(actor);
    }

    @Test
    void searchActorsByName__pass_unmatchedName__return_emptyList() {
        List<Actor> foundActor = Datastore.searchActorsByName("John");
        assertTrue(foundActor.isEmpty());
    }

    @Test
    void searchActorsByName__pass_matchingFirstName__return_correctActors() {
        List<Actor> foundActor = Datastore.searchActorsByName("Tom");
        assertTrue(foundActor.size() == 1);
        assertEquals("Tom", foundActor.get(0).getFirstName());
    }

    @Test
    void searchActorsByName__pass_matchingLastName__return_correctActors() {
        List<Actor> foundActor = Datastore.searchActorsByName("McKean");
        assertTrue(foundActor.size() == 1);
        assertEquals("McKean", foundActor.get(0).getLastName());
    }

    @Test
    void getAuditoriumById__pass_validId__return_correctAuditorium() {
        Auditorium auditorium = Datastore.getAuditoriumById(1);
        assertEquals("1", auditorium.toString());
    }

    @Test
    void getAuditoriumById__pass_invalidId__return_null() {
        Auditorium auditorium = Datastore.getAuditoriumById(100);
        assertNull(auditorium);
    }

    @Test
    void searchTheatersByName__pass_unmatchedName__return_emptyList() {
        List<Theater> foundTheaters = Datastore.searchTheatersByName("Oak Cinema");
        assertTrue(foundTheaters.isEmpty());
    }

    @Test
    void searchTheatersByName__pass_matchedName__return_correctTheaters() {
        List<Theater> foundTheaters = Datastore.searchTheatersByName("AMC");
        assertEquals(1, foundTheaters.size());
        assertTrue(foundTheaters.get(0).getName().contains("AMC"));
    }

    @Test
    void searchTheatersByZipcode__pass_unmatchedZipcode__return_emptyList() {
        List<Theater> foundTheaters = Datastore.searchTheatersByZipcode("12345");
        assertTrue(foundTheaters.isEmpty());
    }

    @Test
    void searchTheatersByZipcode__pass_matchedZipcode__return_correctTheaters() {
        List<Theater> foundTheaters = Datastore.searchTheatersByZipcode("19047");
        assertEquals(1, foundTheaters.size());
        assertTrue(foundTheaters.get(0).getName().contains("Regal"));
    }

    @Test
    void getShowingById__pass_validId__return_correctShowing() {
        Showing showing = Datastore.getShowingById(1);
        assertTrue(showing.toString().contains("Top Gun"));
    }

    @Test
    void getShowingById__pass_invalidId__return_null() {
        Showing showing = Datastore.getShowingById(100);
        assertNull(showing);
    }
}