package Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "MasterServlet", value = "/Master-Servlet")
public class MasterServlet extends HttpServlet {
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
