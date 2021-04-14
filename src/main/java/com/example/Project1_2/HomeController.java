package com.example.Project1_2;

import javax.servlet.http.HttpServletRequest;

public class HomeController {
    public static String home(HttpServletRequest req) {

        Integer type =  (Integer) req.getSession().getAttribute("type");

        if(type == 2) {
            return "Greeting.html";
        }else{
            return "AdminGreeting.html";
        }
    }

    public static String gobackhome(HttpServletRequest req) {

        Integer type =  (Integer) req.getSession().getAttribute("type");

        if(type == 2) {
            return "Greeting.html";
        }else{
            return "AdminGreeting.html";
        }
    }
}
