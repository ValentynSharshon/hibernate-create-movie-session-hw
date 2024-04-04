package mate.academy.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.dao.MovieDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;
import mate.academy.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(movie);
            transaction.commit();
            return movie;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie " + movie, ex);
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception ex) {
            throw new DataProcessingException("Can't get a movie by id: " + id, ex);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM Movie", Movie.class).getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Can`t get all movies from DB", ex);
        }
    }
}
