package bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bootstrap.dao.RoleDao;
import bootstrap.model.Role;

import java.sql.SQLException;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl() {
    }

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Role getRole(String role) throws SQLException {
        return roleDao.getUserRole(role);
    }
}
