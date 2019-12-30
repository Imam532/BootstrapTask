package bootstrap.dao;

import bootstrap.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAllUser() throws SQLException;

    void addUser(User user) throws SQLException;

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserByName(String name) throws SQLException;

    User getUserById(Long id) throws SQLException;
}
