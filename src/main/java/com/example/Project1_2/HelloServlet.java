package com.example.Project1_2;

import Servlets.RequestHelper;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = {"*.change", "/api/*"})
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        System.out.println("in doGet");
        req.getRequestDispatcher(RequestHelper.process(req)).forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        System.out.println("in doPost");
        req.getRequestDispatcher(RequestHelper.process(req)).forward(req,res);
    }
    public void destroy() {
    }
}