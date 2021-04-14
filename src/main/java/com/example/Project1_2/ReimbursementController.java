package com.example.Project1_2;

import beans.Reimbursement;
import beans.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ReimbursementDao;
import dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementController {
    public static String getReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getSession().getAttribute("Username").toString();
        String password = req.getSession().getAttribute("Password").toString();

        ReimbursementDao rdao = new ReimbursementDao();
        UserDao udao = new UserDao();

        User u = udao.getUserByLogginInfo(username, password);

        List<Reimbursement> rlist =  rdao.getReimbursementByUser(u);

        res.getWriter().write(new ObjectMapper().writeValueAsString(rlist));
        return "";

    }
}
