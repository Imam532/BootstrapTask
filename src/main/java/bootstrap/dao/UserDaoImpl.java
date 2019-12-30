package bootstrap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import bootstrap.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    public List<User> getAllUser() throws SQLException {
        return entityManager.createQuery("From User").getResultList();
    }


    public void addUser(User user) throws SQLException {
        entityManager.persist(user);
    }


    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }


    public void updateUser(User user) {
        entityManager.merge(user);
    }


    public User getUserByName(String name) throws SQLException {
        try {
            Query query = entityManager.createQuery("From User where name= :name");
            query.setParameter("name", name);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public User getUserById(Long id) throws SQLException {
        return entityManager.find(User.class, id);
    }
}
