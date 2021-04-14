package Driver;

import beans.Reimbursement;
import beans.User;
import dao.ReimbursementDao;
import dao.UserDao;

import java.sql.Date;
import java.sql.Timestamp;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class main {
    public final static Logger logger = Logger.getLogger(main.class);
    public static void main(String[] args){
        UserDao dao = new UserDao();
        ReimbursementDao rdao = new ReimbursementDao();
        User newUser = dao.getUser(69);

        //System.out.println(newUser);

        /*
        User newUser2 = new User();

        newUser2.setUserId(9);
        newUser2.setUserName("username");
        newUser2.setUserPassword("password");
        newUser2.setFirstName("bill");
        newUser2.setLastName("france");
        newUser2.setEmail("fjasdkfsdajk@fff.com");
        newUser2.setUserRoleID(User.UserType.ADMIN);

        dao.insertUser(newUser2);



        //System.out.println(dao.getAllUsers());


        Reimbursement newReib = rdao.getReimbursement(1);
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        System.out.println(newReib);
        User user1 = new User(4,"meme", User.UserType.EMPLOYEE);
        User user2 = new User(2,"meme", User.UserType.ADMIN);

        dao.insertUser(user1);
        dao.insertUser(user2);
        Reimbursement addReib = new Reimbursement(20,500,ts,ts,"i needed a cow",user1,user2, Reimbursement.ReimbursementStatus.OPEN, Reimbursement.ReimbursementType.FOOD);
        rdao.insertReimbursement(addReib);

         */
        User LogginUser = dao.getUserByLogginInfo("smithium","password");

        System.out.println(rdao.getAllReimbursements());

    }
}
