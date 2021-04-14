package com.example.Project1_2;

import javax.servlet.http.HttpServletRequest;

public class LogOutController {

    public static String logout(HttpServletRequest req){

        System.out.println("in logout");
        req.getSession().invalidate();
        System.out.println("after invalidate");

        return "index.html";
    }
}
