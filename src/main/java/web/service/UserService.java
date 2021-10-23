package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getById(Long id);

    void saveUser(User user);

    void deleteById(Long id);

    void update(User user);

    User getUserByName(String name);

}
