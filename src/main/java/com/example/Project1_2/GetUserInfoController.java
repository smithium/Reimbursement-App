package com.example.Project1_2;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetUserInfoController {

    public static String getUserInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getSession().getAttribute("Username").toString();
        System.out.println("username:  " +username);

        res.getWriter().write(new ObjectMapper().writeValueAsString(username));

        return "";

    }
}


