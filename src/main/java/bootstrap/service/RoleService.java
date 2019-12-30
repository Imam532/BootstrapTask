package bootstrap.service;

import bootstrap.model.Role;

import java.sql.SQLException;

public interface RoleService {

    Role getRole(String role) throws SQLException;
}
