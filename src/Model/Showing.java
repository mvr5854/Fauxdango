package Model;

import java.time.LocalTime;

public class Showing extends DataStoreObj {
    private Movie movie;
    private LocalTime startTime;

    public Showing(long id, Movie movie, String startTime) {
        super(id);
        this.movie = movie;
        this.startTime = LocalTime.parse(startTime);
    }

    public String toString() {
        // https://www.tutorialspoint.com/javatime/javatime_localtime_plus.htm
        LocalTime endTime = startTime.plus(movie.getRunningTime());
        return String.format("%s {%s-%s}", movie.toString(), startTime, endTime);
    }
}
