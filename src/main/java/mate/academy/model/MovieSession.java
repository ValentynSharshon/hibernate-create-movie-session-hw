package mate.academy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie_session")
public class MovieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id",
            referencedColumnName = "id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id",
            referencedColumnName = "id")
    private CinemaHall cinemaHall;

    private LocalDateTime sessionDateTime;

    public MovieSession() {
    }

    public MovieSession(Movie movie, CinemaHall cinemaHall, LocalDateTime sessionDateTime) {
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.sessionDateTime = sessionDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movies) {
        this.movie = movies;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public LocalDateTime getSessionDateTime() {
        return sessionDateTime;
    }

    public void setSessionDateTime(LocalDateTime sessionTime) {
        this.sessionDateTime = sessionTime;
    }

    @Override
    public String toString() {
        return "MovieSession{"
                + "id=" + id
                + ", movies=" + movie
                + ", cinemaHall=" + cinemaHall
                + ", sessionTime=" + sessionDateTime
                + '}';
    }
}
