package services;

import beans.User;
import dao.UserDao;

public class UserServices {

    public User login(String username, String password){
        UserDao thisDao = new UserDao();
        return(thisDao.getUserByLogginInfo(username,password));
    }

    public User register(String username, String password, String email, String firstname, String lastname){
            User newUser = new User();
            newUser.setUserId((int)(Math.random() * 10000 + 1));
            newUser.setUserName(username);
            newUser.setUserRoleID(User.UserType.EMPLOYEE);
            newUser.setUserPassword(password);
            newUser.setEmail(email);
            newUser.setFirstName(firstname);
            newUser.setLastName(lastname);
            UserDao udao = new UserDao();
            udao.insertUser(newUser);


        return newUser;
    }
}
