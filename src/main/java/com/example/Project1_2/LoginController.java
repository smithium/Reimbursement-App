package com.example.Project1_2;

import Driver.main;
import Driver.main.*;
import beans.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import dao.ReimbursementDao;
import dao.UserDao;
import exceptions.invalidLogin;
import org.apache.log4j.Level;
import sun.rmi.runtime.Log;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginController {

    public static String login(HttpServletRequest req){
        if(!req.getMethod().equals("POST")){
            return "index.html";
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        ReimbursementDao rdao = new ReimbursementDao();
        UserDao udao = new UserDao();
        User LogginUser = new User();
        try{
             LogginUser  = udao.getUserByLogginInfo(username,password);
        }catch (invalidLogin e){
            return "bad.html";
        }
        User.UserType role = LogginUser.getUserRoleID();

        if(role.equals(User.UserType.ADMIN)){
            req.getSession().setAttribute("type",1);
        }else{
            req.getSession().setAttribute("type",2);
        }


        Cookie c1 = new Cookie("username",username);


        ObjectMapper mapper = new ObjectMapper();

        try{
            String userString = mapper.writeValueAsString(LogginUser);
            System.out.println(userString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        req.getSession().setAttribute("Username",username);
        req.getSession().setAttribute("Password",password);

        String log = new String("user " + username + " has logged in");
        main.logger.setLevel(Level.ALL);
        main.logger.info(log);
        return "home.change";

    }
}
