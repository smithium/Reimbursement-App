package dao;

import beans.User;
import exceptions.UserNameAlreadyExists;
import exceptions.invalidLogin;

import java.sql.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDao {

    private String url = "jdbc:postgresql://project1db.c8vjwn4ozwqs.us-east-2.rds.amazonaws.com:5432/companydb";
    private String username = "dbadmin";
    private String password = "password";
    public User getUser(int ID){

        User newUser = new User();
        try {
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            String sql = "SELECT * from users where user_id = ?";
            PreparedStatement s = conn.prepareStatement(sql);

            s.setInt(1,ID);
            ResultSet rs = s.executeQuery();


            if(rs.next()) {
                newUser.setUserId(rs.getInt("user_id"));
                newUser.setUserName(rs.getString("username"));
                newUser.setUserPassword(rs.getString("userpassword"));
                newUser.setFirstName(rs.getString("first_name"));
                newUser.setLastName(rs.getString("last_name"));
                newUser.setEmail(rs.getString("email"));
                int type = rs.getInt("user_role_id");

                switch (type){
                    case 1:
                        newUser.setUserRoleID(User.UserType.ADMIN);
                        break;
                    case 2:
                        newUser.setUserRoleID(User.UserType.EMPLOYEE);
                        break;

                }
            }else{
                throw new invalidLogin();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return newUser;
    }

    public User getUserByLogginInfo(String username, String password){

        User newUser = new User();
        try {
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            String sql = "SELECT * from users where username = ?";
            PreparedStatement s = conn.prepareStatement(sql);

            s.setString(1,username);
            ResultSet rs = s.executeQuery();

            if(rs.next()) {
                newUser.setUserId(rs.getInt("user_id"));
                newUser.setUserName(rs.getString("username"));
                newUser.setUserPassword(rs.getString("userpassword"));
                newUser.setFirstName(rs.getString("first_name"));
                newUser.setLastName(rs.getString("last_name"));
                newUser.setEmail(rs.getString("email"));
                int type = rs.getInt("user_role_id");

                switch (type){
                    case 1:
                        newUser.setUserRoleID(User.UserType.ADMIN);
                        break;
                    case 2:
                        newUser.setUserRoleID(User.UserType.EMPLOYEE);
                        break;

                }
            }else{
                throw new invalidLogin();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if(!newUser.getUserPassword().equals(password)){
            throw new invalidLogin();
        }
        return newUser;
    }

    public User insertUser(User u){

        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement ps = conn.prepareStatement("insert into users values (?,?,?,?,?,?,?)");

            ps.setInt(1, u.getUserId());
            ps.setString(2,u.getUserName());
            ps.setString(3,u.getUserPassword());
            ps.setString(4,u.getFirstName());
            ps.setString(5,u.getLastName());
            ps.setString(6,u.getEmail());

            switch (u.getUserRoleID()){
                case ADMIN:
                    ps.setInt(7,1);
                    break;
                case EMPLOYEE:
                    ps.setInt(7,2);
                    break;
            }

            PreparedStatement ps2 = conn.prepareStatement("select * from users where username = ?");

            ps2.setString(1,u.getUserName());
            ResultSet rs2 = ps2.executeQuery();
            if(rs2.next()){
                throw new UserNameAlreadyExists();
            }else {
                ps.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return u;
    }

    public List<User> getAllUsers(){

        List<User> returnList = new LinkedList<>();

        try{
        Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

        String sql = "SELECT * from users";
        Statement s = conn.createStatement();

        ResultSet rs = s.executeQuery(sql);

        while(rs.next()){
            User newUser = new User();
            newUser.setUserId(rs.getInt("user_id"));
            newUser.setUserName(rs.getString("username"));
            newUser.setUserPassword(rs.getString("userpassword"));
            newUser.setFirstName(rs.getString("first_name"));
            newUser.setLastName(rs.getString("last_name"));
            newUser.setEmail(rs.getString("email"));
            int type = rs.getInt("user_role_id");

            switch (type){
                case 1:
                    newUser.setUserRoleID(User.UserType.ADMIN);
                    break;
                case 2:
                    newUser.setUserRoleID(User.UserType.EMPLOYEE);
                    break;

            }
            returnList.add(newUser);
        }
    }catch (SQLException e){
        e.printStackTrace();
    }

        return returnList;
    }
}
