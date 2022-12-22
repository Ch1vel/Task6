package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUser(String model, int series) {
        TypedQuery<User> q = sessionFactory.getCurrentSession().createQuery("select u from User u where u.car.model=:f and u.car.series=:s", User.class);
        q.setParameter("f", model);
        q.setParameter("s", series);
        return q.getSingleResult();
    }

    @Override
    public User getUserById(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    public User getUserOfHisCar(String model, int series) {
        return sessionFactory.getCurrentSession().get(User.class, new Car(model, series).getId());
    }

}
