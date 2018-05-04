package system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDao {
    private List<User> users = new ArrayList<User>();

    public List<User> getUsers(){
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
