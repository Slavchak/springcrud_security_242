package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("SELECT roles FROM Role roles", Role.class).getResultList();
    }

    @Override
    public Role findRoleByName(String name) {
        return entityManager.createQuery("SELECT rol FROM Role rol WHERE rol.name = : role", Role.class)
                .setParameter("role", name).getSingleResult();
    }
}
