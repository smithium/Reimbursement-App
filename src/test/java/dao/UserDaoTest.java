package dao;

import beans.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    public static User u1;
    public static User u2;
    public static UserDao udao;

    @BeforeAll
    public static void setup(){
        u1 = new User();
        u2 = new User();
        udao = new UserDao();

        u2.setUserName("testUser");
        u2.setUserId(10001);
        u2.setUserPassword("testPassword");
        u2.setUserRoleID(User.UserType.EMPLOYEE);

        u1.setUserName("testUser2");
        u1.setUserId(10002);
        u1.setUserPassword("testPassword2");
        u1.setUserRoleID(User.UserType.EMPLOYEE);

    }
    @Test
    void insertAndGetUser() {
        udao.insertUser(u1);
        assertEquals(u1.toString(),udao.getUser(u1.getUserId()).toString());

    }

    @Test
    void getUserByLogginInfo() {

        udao.insertUser(u2);
        assertEquals(u2.toString(),udao.getUserByLogginInfo(u2.getUserName(),u2.getUserPassword()).toString());
    }

}
