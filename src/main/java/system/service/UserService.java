package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.UserDao;
import system.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List getUsers(){
        return userDao.getUsers();
    }

    public void addUser(User user) {
        userDao.getUsers().add(user);
    }

    public String check (User user) {
        String result = "";
        boolean userIsFound = false;
        if (userDao.getUsers().isEmpty()) {
            result = "there is no users";
        }
        else {
            List <User> users = new ArrayList<User>(userDao.getUsers());
            for (User usr : users) {
                if(usr.toString().toLowerCase().equals(user.toString().toLowerCase())){
                    result = "user is found";
                    userIsFound = true;
                }
            }
            if(!userIsFound) {
                result = "user isn't found";
            }

        }
        return result;
    }

}
