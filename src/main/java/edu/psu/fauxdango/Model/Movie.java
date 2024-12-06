package edu.psu.fauxdango.Model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie extends DataStoreObj {
    private long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Duration runningTime;
    private Rating rating;
    private Genre genre;
    private List<Genre> genres;
    private List<Actor> actors;

    private Movie(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.releaseDate = builder.releaseDate;
        this.runningTime = builder.runningTime;
        this.rating = builder.rating;
        this.genres = builder.genres;
        this.actors = builder.actors;
    }

    public long getPrimaryKey(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public Rating getRating() {
        return rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void addActor(long ID) {
        Actor actor = Datastore.getActorById(ID);
        actors.add(actor);
    }


    public String toString() {
        return String.format("%s (%s, %s) %s {%s min}", this.title, this.rating, this.releaseDate.getYear(), genres, runningTime.toMinutes());
    }
    public static class Builder
    {
        private long id;
        private String title;
        private String description;
        private LocalDate releaseDate;
        private Duration runningTime;
        private Rating rating;
        private List<Genre> genres = new ArrayList<>();
        private List<Actor> actors = new ArrayList<>();

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder releaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }
        public Builder runningTime(Duration runningTime) {
            this.runningTime = runningTime;
            return this;
        }
        public Builder rating(Rating rating) {
            this.rating = rating;
            return this;
        }
        public Builder genre(Genre genre) {
            genres.add(genre);
            return this;
        }

        public Builder actor(long ID){
            Actor actor = Datastore.getActorById(ID);
            actors.add(actor);
            return this;
        }

        public Movie build() {
            Movie movie =  new Movie(this);
            return movie;
        }
    }
}