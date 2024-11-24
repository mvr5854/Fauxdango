package Model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Datastore {
    private static List<Advertisement> advertisements;
    private static List<Theater> theaters;
    private static List<Movie> movies;
    private static List<Actor> actors;
    private static List<Auditorium> auditoriums;
    private static List<Showing> showings;

    public static void clearData() {
        System.out.println("Clearing 'database'");
        advertisements = new ArrayList<>();
        theaters = new ArrayList<>();
        auditoriums = new ArrayList<>();
        movies = new ArrayList<>();
        actors = new ArrayList<>();
        showings = new ArrayList<>();

    }

    public static void populateData() {
        System.out.println("Populating 'database'");
        initAdvertisements();
        initActors();
        initMovies();
        initShowings();
        initAuditoriums();
        initTheaters();
    }

    public static void resetData() {
        clearData();
        populateData();
    }

    static {
        resetData();
    }

    private static void initAuditoriums() {
        System.out.println("Initializing auditoriums");
        Auditorium auditorium;

        auditorium = new Auditorium(1, 1);
        auditorium.addShowing(1);
        auditorium.addShowing(2);
        auditoriums.add(auditorium);

        auditorium = new Auditorium(2, 2);
        auditorium.addShowing(3);
        auditorium.addShowing(4);
        auditoriums.add(auditorium);

        auditorium = new Auditorium(3, 1);
        auditorium.addShowing(5);
        auditorium.addShowing(6);
        auditoriums.add(auditorium);

        auditorium = new Auditorium(4, 2);
        auditorium.addShowing(7);
        auditorium.addShowing(8);
        auditoriums.add(auditorium);
    }

    private static void initShowings() {
        System.out.println("Initializing showings");
        Showing showing;

        showing = new Showing(1, getMovieById(1L), "13:00");
        showings.add(showing);

        showing = new Showing(2, getMovieById(4L), "16:00");
        showings.add(showing);

        showing = new Showing(3, getMovieById(3L), "18:00");
        showings.add(showing);

        showing = new Showing(4, getMovieById(1L), "17:30");
        showings.add(showing);

        showing = new Showing(5, getMovieById(4L), "19:15");
        showings.add(showing);

        showing = new Showing(6, getMovieById(2L), "10:00");
        showings.add(showing);

        showing = new Showing(7, getMovieById(2L), "12:45");
        showings.add(showing);

        showing = new Showing(8, getMovieById(3L), "23:00");
        showings.add(showing);
    }


    private static void initTheaters() {
        System.out.println("Initializing theaters");
        Theater theater;

        theater = new Theater(1L, "AMC Neshaminy 24", "660 Neshaminy Mall", "Bensalem", "PA", "19020", "(215) 396-8050", "https://www.amctheatres.com/movie-theatres/philadelphia/amc-neshaminy-24");
        theater.addAuditorium(1);
        theater.addAuditorium(2);
        theaters.add(theater);

        theater = new Theater(2L, "Regal UA Oxford Valley", "403 Middletown Blvd", "Langhorne", "PA", "19047", "(844) 462-7342", "https://www.regmovies.com › theatres › regal-ua-oxford-valley");
        theater.addAuditorium(3);
        theater.addAuditorium(4);
        theaters.add(theater);
    }

    private static void initAdvertisements() {
        System.out.println("Initializing advertisements");
        advertisements.add(new Advertisement("Drink Pepsi"));
        advertisements.add(new Advertisement("Buy Candy"));
        advertisements.add(new Advertisement("Shop at Target"));
        advertisements.add(new Advertisement("Watch NCIS"));
    }


    private static void initActors() {
        System.out.println("Initializing actors");

        Actor actor;
        actor = new Actor(1, "Tom", "Cruise", "1963-07-03");
        actors.add(actor);

        actor = new Actor(2, "Kelly", "McGillis", "1957-07-09");
        actors.add(actor);

        actor = new Actor(3, "Michael", "McKean", "1947-10-17");
        actors.add(actor);

        actor = new Actor(4, "Christopher", "Guest", "1948-02-05");
        actors.add(actor);

        actor = new Actor(5, "Jaimie Lee", "Curtis", "1958-11-22");
        actors.add(actor);

        actor = new Actor(6, "Donald", "Pleasence", "1919-10-05");
        actors.add(actor);

        actor = new Actor(7, "Kurt", "Russell", "1951-03-17");
        actors.add(actor);
    }

    private static void initMovies() {
        System.out.println("Initializing movies");

        Movie movie;

        movie = new Movie.Builder()
                .id(1L)
                .title("Top Gun")
                .description("Fighter pilot Maverick flies a jet.  Goose dies.")
                .releaseDate(LocalDate.of(1986, 5, 16))
                .runningTime(Duration.ofMinutes(110))
                .rating(Rating.PG)
                .genre(Genre.ACTION)
                .genre(Genre.DRAMA)
                .actor(1)
                .actor(2)
                .build();
        movies.add(movie);


        movie = new Movie.Builder()
                .id(2L)
                .title("This Is Spinal Tap")
                .description("Spinal Tap, is chronicled by film director Marty DiBergi")
                .releaseDate(LocalDate.of(1984, 3, 2))
                .runningTime(Duration.ofMinutes(84))
                .rating(Rating.R)
                .genre(Genre.COMEDY)
                .genre(Genre.DOCUMENTARY)
                .actor(3)
                .actor(4)
                .build();
        movies.add(movie);

        movie = new Movie.Builder()
                .id(3L)
                .title("Halloween")
                .description("Michael Meyers kills people")
                .releaseDate(LocalDate.of(1978, 10, 5))
                .runningTime(Duration.ofMinutes(91))
                .rating(Rating.R)
                .genre(Genre.HORROR)
                .actor(5)
                .actor(6)
                .build();
        movies.add(movie);

        movie = new Movie.Builder()
                .id(4L)
                .title("Escape from New York")
                .description("Snake Plissken rescues the president\"")
                .releaseDate(LocalDate.of(1981, 7, 10))
                .runningTime(Duration.ofMinutes(99))
                .rating(Rating.R)
                .genre(Genre.ACTION)
                .actor(6)
                .actor(7)
                .build();
        movies.add(movie);

    }

    public static List<Advertisement> getAllAdvertisements() {
        return advertisements;
    }

    public static List<Movie> getMovies() {
        return movies;
    }

    public static List<Actor> getActors() {
        return actors;
    }

    public static List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public static List<Theater> getTheaters() {
        return theaters;
    }

    public static List<Showing> getShowings() {
        return showings;
    }

    /**
     * Search a movie by a movie id
     * <p>
     * Allows the user to provide a primary key id of movie
     * <p>
     * Using the movie id, this method finds the movie
     * from a list of movies
     *
     * @param id primary key id of movies
     * @return the movie which was found
     * @author Phil O'Connell (pxo4@psu.edu )
     * @author Mahfuzur Rahman (mvr5854@psu.edu)
     */
    public static Movie getMovieById(long id) {
        for (Movie movie : movies) {
            if (movie.getPrimaryKey() == id) {
                return movie;
            }
        }
        return null;
    }

    public static List<Movie> searchMoviesByTitle(String text) {
        List<Movie> foundMovies = new ArrayList<>();
        text = text.toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(text)) {
                foundMovies.add(movie);
            }
        }
        return foundMovies;
    }

    /**
     * Search an actor by the actor id
     * <p>
     * This method allows the user to provide an actor id
     * <p>
     * Using the actor id, it finds the actor from a list of actors
     *
     * @param id primary key id of actors
     * @return the actor who was found
     * @author Phil O'Connell (pxo4@psu.edu )
     * @author Mahfuzur Rahman (mvr5854@psu.edu)
     */
    public static Actor getActorById(long id) {
        for (Actor actor : actors) {
            if (actor.getPrimaryKey() == id) {
                return actor;
            }
        }
        return null;
    }

    public static List<Actor> searchActorsByName(String text) {
        List<Actor> foundActors = new ArrayList<>();
        text = text.toLowerCase();
        for (Actor actor : actors) {
            if (actor.getFirstName().toLowerCase().contains(text)) {
                foundActors.add(actor);
            } else if (actor.getLastName().toLowerCase().contains(text)) {
                foundActors.add(actor);
            }
        }
        return foundActors;
    }

    public static Auditorium getAuditoriumById(long id) {
        for (Auditorium auditorium : auditoriums) {
            if (auditorium.getPrimaryKey() == id) {
                return auditorium;
            }
        }

        return null;
    }

    public static List<Theater> searchTheatersByName(String text) {
        List<Theater> foundTheaters = new ArrayList<>();
        for (Theater theater : theaters) {
            Pattern pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(theater.getName());
            boolean matchFound = matcher.find();
            if (matchFound) {
                foundTheaters.add(theater);
            }
        }
        return foundTheaters;
    }

    public static List<Theater> searchTheatersByZipcode(String text) {
        List<Theater> foundTheaters = new ArrayList<>();
        for (Theater theater : theaters) {
            if (theater.getZipcode().equals(text)) {
                foundTheaters.add(theater);
            }
        }
        return foundTheaters;
    }


    public static Showing getShowingById(long id) {
        for (Showing showing : showings) {
            if (showing.getPrimaryKey() == id) {
                return showing;
            }
        }
        return null;
    }
}
