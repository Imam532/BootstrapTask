package bootstrap.dao;

import bootstrap.model.Role;

import java.sql.SQLException;

public interface RoleDao {
    public Role getUserRole(String role) throws SQLException;
}
