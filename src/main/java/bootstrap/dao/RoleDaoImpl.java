package bootstrap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import bootstrap.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;

@Repository
public class RoleDaoImpl implements RoleDao{

    @Autowired
    private EntityManager entityManager;

    public Role getUserRole(String role) throws SQLException {
        Query query = entityManager.createQuery("FROM Role WHERE role= :role");
        query.setParameter("role", role);
        return(Role) query.getSingleResult();
    }

}
