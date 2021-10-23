package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleService {

    List<Role> allRoles();

    Role findRoleByName(String name);
}

