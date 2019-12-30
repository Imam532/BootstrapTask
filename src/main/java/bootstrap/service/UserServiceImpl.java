package bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bootstrap.dao.UserDao;
import bootstrap.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDao.getUserByName(username);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    @Transactional
    public User addUser(User user) throws SQLException {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        userDao.addUser(user);
        return user;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUser();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public User updateUser(User user) throws SQLException {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        userDao.updateUser(user);
        return user;
    }

    @Override
    @Transactional
    public User getUserByName(String name) throws SQLException {
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public User getUserById(Long id) throws SQLException {
        return userDao.getUserById(id);
    }

}
